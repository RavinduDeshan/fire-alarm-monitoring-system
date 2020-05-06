# fire-alarm-monitoring-system

# How To Install

1. Download the Project

2. Open the Terminal and go in to the folder ```\FireAlarmSystem\FireAlarmSystemWebClient```

3. To Proceed you should have intalled Node. Check node Version By typing 
```
node -- version
npm --version
```
in the Command Prompt

4. Then type ```npm install``` to install node Modules

5. All the dependencies in the client folder, pakage.json will be downloaded

6. Install NetBeans IDE(Prefferred), Spring Tool Suite V4(Preffered) or Eclipse, MySQL

7. Open RESTAPI  in \FireAlarmSystem\REST API through Spring Tool Suite or Eclipse IDE.

8. Run the REST API, If Port No is Busy Assign a different one,
		If changed port No: Change the post no of all classes in ```"\FireAlarmSystem\FireAlarmSystemDesktopClient\src\firealarmsystem"``` 
		and ```"\FireAlarmSystem\FireAlarmSystemDesktopClient\src\RMIServer"```

9. Go to Webclient and type npm start in the Terminal

10. Then the Fire alarm Monitoring System Application will be deployed in ```http://localhost:3000/```

11. Open the desktop client in ```"\FireAlarmSystem\FireAlarmSystemDesktopClient"``` from IDE [Eclipse, NetBeans] Make sure your IDE Support Java-Swing and run the application.

12. Run RMI Server in ```"\FireAlarmSystem\FireAlarmSystemDesktopClient\src\RMIServer\Server.java"```

13. To Log on To Dashboard Run Client Login in ```"\FireAlarmSystem\FireAlarmSystemDesktopClient\src\firealarmsystem\ClientLogin.java"```

14. To Run Sensor Clients Run ```"\FireAlarmSystem\SensorClient\src\Layouts\SensorClientActivator"``` and activate Sensors in the System

15. Now everything is alright! You are good to go

   Happy Coding !!!
   
## Further More

Watch this video for more instructions to install, configure the sytem and to see the features of the working system.

https://youtu.be/XMyvfr3NRwI

<iframe width="560" height="315" src="https://www.youtube.com/embed/IDEHhPREOBk" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>


