import RPi.GPIO as GPIO
led0 = 24
led1 = 26
import smbus
import paho.mqtt.client as mqtt
import time
import random
import json

GPIO.setmode(GPIO.BOARD)
GPIO.setup(led0, GPIO.OUT,initial=0)
GPIO.setup(led1, GPIO.OUT,initial=0)
def on_connect(client, userdata, flags, rc):
    print("Connected with result code "+str(rc))
    client.subscribe("bar")
def on_message(client, userdata, msg):
    print(msg.topic+" "+str(msg.payload))
    data = json.loads(str(msg.payload))
    if data["id"]==0:
        if data["status"]=='on':
            GPIO.output(led0,GPIO.HIGH)
        else:
            GPIO.output(led0,GPIO.LOW)
    elif data["id"]==1:
        if data["status"]=='on':
            GPIO.output(led1,GPIO.HIGH)
        else:
            GPIO.output(led1,GPIO.LOW)

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
bus = smbus.SMBus(1)
arduinoAddr = 0x3
meduinoAddr = 0x11
client.connect("139.59.3.242", 1883, 60)
client.loop_start()
condition=True
while True :
	try:
            reponse2 = bus.read_byte(arduinoAddr)
	    client.publish("foo",'{\"id\": 0, \"temp\": '+ str(reponse2)               +', \"latitude\": 12.9702002, \"longitude\": 77.5502898}')
            client.publish("foo",'{\"id\": 1, \"temp\": '+ str(random.randint(0,400))    +', \"latitude\": 12.9702002, \"longitude\": 77.5502898}')            
            time.sleep(5)
        except (RuntimeError,IOError):
           print("Error while sending check wiring")
