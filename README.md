# IoT Stack for Farming stack

Here in the repo we have 4 difference project
1. RaspberryPi Python
    This will connect the Texas Instrument Data to RabbitMQ MQTT. Raspberry Pi will act as a Data receiver and sender.
    To run the application paste the python_node.py file to the pi and run python python_node.py
2. Texas Instrument
    This will collect the data from the sensors and send the data to the Raspberry Pi
3. Java Application - src
    This component is based on java springboot. This will listen to the MQTT messages and provide it to the enduser as a REST API
4. Android - Unity + Vuforia
    This component have the apk file which will scan the VuMarks and display the data accordingly.
    
