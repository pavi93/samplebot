# samplebot

To Run the Application - 

1) Download robot-0.0.1-SNAPSHOT 
2) Run the following in command prompt

	java -jar robot-0.0.1-SNAPSHOT.jar
	
3)App will start in port 8082 - To access application

	http://localhost:8082/bot/home
	
4)To access swagger-ui to test API 

	http://localhost:8082/swagger-ui.html#/robot-controller
	
NOTE - Before Running each test case on /action api, invoke /charge api to reset battery to 100,as application is designed to maintain state until page reload


Language & Frameworks Used
1)Java
2)Springboot
3)Angular JS
4)Bootstrap CSS