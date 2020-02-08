package com.xebia.robot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.xebia.robot.model.Bot;

@SpringBootApplication
public class RobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotApplication.class, args);
	}
	
	@Bean
	public Bot initalizeBot(@Value("${robot.battery}") Integer battery,@Value("${robot.message}") String message) {
		return new Bot(battery,message);
		
	}

}
