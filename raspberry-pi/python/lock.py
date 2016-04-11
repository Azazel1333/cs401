import RPi.GPIO as GPIO
import time

#remove warnings as it's likely the channel will already be in use
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

# define the pin(s) that are in use

pinList = [2]


# Set mode and output of pins

for i in pinList:
    GPIO.setup(i, GPIO.OUT)
    GPIO.output(i, GPIO.HIGH)

#return output to be interpreted by server then application
print '{"success":"1"}'

# Reset GPIO settings
GPIO.cleanup()
