package com.xebia.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.robot.aggregator.RobotAggregator;
import com.xebia.robot.constants.RobotConstants;
import com.xebia.robot.model.Bot;
import com.xebia.robot.model.RobotOperation;
import com.xebia.robot.model.RobotScan;

@RestController
@RequestMapping("robot")
public class RobotController {

	private Bot bot;
	private RobotAggregator robotAggregator;

	@Autowired
	public RobotController(Bot bot, RobotAggregator robotAggregator) {
		this.bot = bot;
		this.robotAggregator = robotAggregator;
	}

	@GetMapping("/charge")
	public ResponseEntity<Bot> botCharge() {
		try {
			return new ResponseEntity<Bot>(robotAggregator.charge(), HttpStatus.OK);
		} catch (Exception e) {
			bot.setMessage(RobotConstants.BOT_ERROR);
			return new ResponseEntity<Bot>(bot, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/action")
	public ResponseEntity<Bot> botAction(@RequestBody RobotOperation robotOperation) {
		try {
			return new ResponseEntity<Bot>(robotAggregator.action(robotOperation), HttpStatus.OK);
		} catch (Exception e) {
			bot.setMessage(RobotConstants.BOT_ERROR);
			return new ResponseEntity<Bot>(bot, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//Assuming result from Scan API is passed as price in botScan API
	@PostMapping("/scan")
	public ResponseEntity<Bot> botScan(@RequestBody RobotScan robotScan) {

		try {
			return new ResponseEntity<Bot>(robotAggregator.scan(robotScan), HttpStatus.OK);
		} catch (Exception e) {
			bot.setMessage(RobotConstants.BOT_ERROR);
			return new ResponseEntity<Bot>(bot, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
