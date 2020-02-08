package com.xebia.robot.aggregator;

import com.xebia.robot.model.Bot;
import com.xebia.robot.model.RobotOperation;
import com.xebia.robot.model.RobotScan;

public interface RobotAggregator {

	public Bot charge();
	
	public Bot action(RobotOperation robotOperation);
	
	public Bot scan(RobotScan robotScan);
}
