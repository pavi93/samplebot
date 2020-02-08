package com.xebia.robot.aggregator.impl;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xebia.robot.constants.RobotConstants;
import com.xebia.robot.model.Bot;
import com.xebia.robot.model.RobotOperation;
import com.xebia.robot.model.RobotScan;

public class RobotAggregatorImplTest {

	
	RobotAggregatorImpl robotAggregatorImpl ;
	Bot bot;
	RobotScan robotScan;
	RobotOperation robotOperation;
	
	@BeforeEach
	public void initEach(){
		bot = new Bot(100,"");
		robotAggregatorImpl = new RobotAggregatorImpl(bot);
		robotScan =  new RobotScan();
		robotOperation = new RobotOperation();
	}
	
	//Test Case 1 
	@Test
	public void testAction() {

		 bot = getBotValue(3.5f,0);
		
		assertEquals(30, bot.getBattery());
	}
	
	//Test Case 2 
	@Test
	public void testActionWithLoad() {

		 bot = getBotValue(2,3);
		
		assertEquals(54, bot.getBattery());
	}
	
	
	//Test Case 3
	@Test
	public void testActionWithMoreLoad() {

		 bot = getBotValue(0,11);
		
		assertEquals(RobotConstants.OVER_LOAD, bot.getMessage());
	}
	
	@Test
	public void testScan() {

		robotScan.setPrice("44");
		Bot bot = robotAggregatorImpl.scan(robotScan);
		assertEquals("44", bot.getMessage());
	}
	
	@Test
	public void testScanFailure() {

		robotScan.setPrice("");
		Bot bot = robotAggregatorImpl.scan(robotScan);
		assertEquals(RobotConstants.SCAN_FAILURE, bot.getMessage());
	}
	
	@Test
	public void testScanFailureNull() {

		robotScan.setPrice(null);
		Bot bot = robotAggregatorImpl.scan(robotScan);
		assertEquals(RobotConstants.SCAN_FAILURE, bot.getMessage());
	}
	
	@Test
	public void testScanFailureError() {

		robotScan.setPrice(null);
		RobotOperation robotOperation = new RobotOperation();
		robotOperation.setDistance(6);;
		Bot bot = robotAggregatorImpl.action(robotOperation);
		 bot = robotAggregatorImpl.scan(robotScan);
		assertEquals(RobotConstants.POWER_OFF, bot.getMessage());
	}
	

	
	@Test
	public void testActionPowerOff() {

		 bot = getBotValue(6,3);
		
		assertEquals(0, bot.getBattery());
		assertEquals(RobotConstants.POWER_OFF, bot.getMessage());
	}
	
	@Test
	public void testActionWithLoad1() {

		 bot = getBotValue(3,5);
		
		assertEquals(30, bot.getBattery());
	}
	
	
	@Test
	public void testActionWithLoad2() {

		 bot = getBotValue(3,6.5f);
		
		assertEquals(27, bot.getBattery());
	}
	
	
	public Bot getBotValue(float distance,float load) {
		robotOperation.setDistance(distance);
		robotOperation.setLoad(load);
		return robotAggregatorImpl.action(robotOperation);
	}
	
}
