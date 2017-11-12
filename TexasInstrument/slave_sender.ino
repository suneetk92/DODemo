// Wire Slave Sender
// by Nicholas Zambetti <http://www.zambetti.com>

// Demonstrates use of the Wire library
// Sends data as an I2C/TWI slave device
// Refer to the "Wire Master Reader" example for use with this

// Created 29 March 2006

// This example code is in the public domain.


#include <Wire.h>
int sensorPin = A0;  
int sensorValue = 0;
void setup()
{
   Serial.begin(115200);
  Wire.begin(3);                // join i2c bus with address #2
  Wire.onRequest(requestEvent); // register event
  Wire.setModule(1);
}

void loop()
{
  // Wire.beginTransmission(3);
  
  delay(100);
  //Wire.write("b");
  //Wire.endTransmission();  
}

// function that executes whenever data is requested by master
// this function is registered as an event, see setup()
void requestEvent()
{
    Serial.write("send "+sensorPin);
  sensorValue = analogRead(sensorPin);    
  Wire.write(sensorValue); // respond with message of 6 bytes
                       // as expected by master
}
