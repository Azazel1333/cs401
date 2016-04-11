import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

# define the pin(s) that are in use

pinList = [2]


# Set mode and output of pins

for i in pinList:
    GPIO.setup(i, GPIO.OUT)
    GPIO.output(i, GPIO.HIGH)

# duration to keep door unlocked

#SleepTimeL = 5.0

GPIO.output(2, GPIO.HIGH)
GPIO.output(2, GPIO.LOW)
#print 'unlocked \n'
#time.sleep(SleepTimeL);



#return output to be interpreted by server then application
print '{"success":"1"}'

# Reset GPIO settings
#GPIO.cleanup()
