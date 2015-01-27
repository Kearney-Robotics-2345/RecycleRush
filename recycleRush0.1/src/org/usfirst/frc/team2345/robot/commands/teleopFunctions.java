package org.usfirst.frc.team2345.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2345.robot.*;

/**
 *
 */
public class teleopFunctions extends Command {
	Joystick stick = OI.stick;
	Joystick schtick = OI.schtick;
    public teleopFunctions() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Lift 13in (Via limit switch)
    	if (stick.getRawButton(3) == true) {
    		boolean loopControl = false;
    		while (loopControl != true) {	
    			RobotMap.elevator1.set(0.5);
    			RobotMap.elevator2.set(0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl = true;
    		}
    	}
    	//Down 13in
    	if (stick.getRawButton(2) == true) {
    		boolean loopControl = false;
    		while (loopControl != true) {	
    			RobotMap.elevator1.set(-0.5);
    			RobotMap.elevator2.set(-0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl = true;
    		}
    	}
    	//Up 26in
    	if (stick.getRawButton(4) == true) {
    		int loopControl = 0;
    		while (loopControl < 2) {	
    			RobotMap.elevator1.set(0.5);
    			RobotMap.elevator2.set(0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl += 1;
    		}
    	}
    	//Down 26in
    	if (stick.getRawButton(5) == true) {
    		int loopControl = 0;
    		while (loopControl < 2) {	
    			RobotMap.elevator1.set(-0.5);
    			RobotMap.elevator2.set(-0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl += 1;
    		}
    	}
    	//Up Completely
    	if (schtick.getRawButton(3) == true) {
    		int loopControl = 0;
    		while (loopControl < 4) {	
    			RobotMap.elevator1.set(0.5);
    			RobotMap.elevator2.set(0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl += 1;
    		}
    	}
    	//Down Completely
    	if (schtick.getRawButton(2) == true) {
    		int loopControl = 0;
    		while (loopControl < 4) {	
    			RobotMap.elevator1.set(-0.5);
    			RobotMap.elevator2.set(-0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl += 1;
    		}
    	}
    	//Rotate External Arm
    	while (schtick.getRawButton(4)) {
    		RobotMap.extArm.set(0.5);
    	}
    	//Rotate External Arm the other way
    	while (schtick.getRawButton(5)) {
    		RobotMap.extArm.set(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
