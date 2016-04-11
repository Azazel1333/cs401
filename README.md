# Project overview

The purpose of this repository is to establish connection between a mobile app, a LAMP server, and a raspberry PI in such a way that you will be able to initiate requests VIA your android app, process the requests on your server and as required you can initiate code on an embedded device located at another remote location.

Purpose of each "node" / location:

1. Raspberry PI - Execute low level code on an embedded system. This will provide a great opporutunity to learn the basis of managing the GPIO pins on a raspberry PI while simultaneously creating a system that has real world applications.

2. LAMP Server - Provides access to a database and proper output when executing commands to be read by your android application. Upon completion I'll be able to build an app that interconnects with a server for which to manage the data and execute the scripts of the given application. More specific to the project, having the LAMP server execute as much of the request as possible before passing it on the the PI is essentially reducing the load and the embedded system. Will also prove to be a key compenent on user authentication between communications sent from the android application to the PI.

3. Android APP - Everyone wants to control their devices remotely and for the purposes of the Door Strike project I felt that this was without question a necessity. Being able to unlock your door from absolutely anywhere in the world provided you have an internet connection can most definitely have its perks.

## File structure of the repository

To provide as much simplicity as possible I will seperate the source code for each application within it's own directory. More specifically the directories will be mapped as shown below:

  android-app -> code for android application
  
  android-requests -> php code for lamp server
  
  raspberry-pi -> python code that is executed by lamp server


## Requirements for code execution

### Android Studio:

You can download Android Studio version 2.0 here: http://developer.android.com/sdk/index.html

### Raspberry PI (SSH Enabled with Python):

You can download the latest version of raspian here (this project was using default settings using the "Jessie" distribution): https://www.raspberrypi.org/downloads/raspbian/ 

### LAMP Server: 

You will most likely require a dedicated server or a server where you have full permissions to execute SSH.



