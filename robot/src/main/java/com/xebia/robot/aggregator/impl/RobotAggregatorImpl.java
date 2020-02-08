package com.xebia.robot.aggregator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.xebia.robot.aggregator.RobotAggregator;
import com.xebia.robot.constants.RobotConstants;
import com.xebia.robot.model.Bot;
import com.xebia.robot.model.RobotOperation;
import com.xebia.robot.model.RobotScan;

@Component
public class RobotAggregatorImpl implements RobotAggregator {
	
	Bot bot;
	
	@Autowired
	public RobotAggregatorImpl(Bot bot) {
		this.bot = bot;
	}

	@Override
	public Bot charge() {

		bot.setBattery(RobotConstants.BATTERY);
		bot.setMessage(RobotConstants.FULL_CHARGE);
		return bot;
	}

	@Override
	public Bot action(RobotOperation robotOperation) {

		try {
			bot.setMessage(RobotConstants.EMPTY);
			
			int batteryRemaining = bot.getBattery();
			if (robotOperation.getLoad() > 10) {
				bot.setMessage(RobotConstants.OVER_LOAD);
				return bot;
			} else if (robotOperation.getLoad() > 0)
				batteryRemaining = (int) (batteryRemaining - (robotOperation.getLoad() * 2));
		
			if (robotOperation.getDistance() > 0)
				batteryRemaining = (int) (batteryRemaining - (robotOperation.getDistance() * 20));

			bot.setBattery(batteryRemaining);

			if (batteryRemaining < 0) {
				bot.setBattery(RobotConstants.ZERO);
				bot.setMessage(RobotConstants.POWER_OFF);
			}
			else if (batteryRemaining < 15)
				bot.setMessage(RobotConstants.LOW_CHARGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bot;
	}

	@Override
	public Bot scan(RobotScan robotScan) {
		if(bot.getMessage().equalsIgnoreCase(RobotConstants.POWER_OFF))
			return bot;
		if (robotScan.getPrice() == null || robotScan.getPrice().equalsIgnoreCase(RobotConstants.EMPTY))
			bot.setMessage(RobotConstants.SCAN_FAILURE);
		else
			bot.setMessage(robotScan.getPrice());
		
		return bot;
	}
	
}
