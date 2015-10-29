package org.usfirst.frc.team2345.robot.commands;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2345.robot.*;

// Commented out code left for posterities' sake
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

    	// lift elevator while button three is pressed
    	double elevatorLeft;
    	
    	if (OI.schshtick.getRawButton(2) == true && OI.schshtick.getRawButton(1) == false) {
    		elevatorLeft = (RobotMap.elevatorSwitch1.get()) ? .85 : 0;
    	}
    	else if (OI.schshtick.getRawButton(1) == true && OI.schshtick.getRawButton(2) == false) {
    		elevatorLeft = (RobotMap.elevatorSwitch2.get()) ? -.85 : 0;
    	}
    	else {
    		elevatorLeft = 0;
    	}
    	
    	double elevatorRight;
    	
    	if (OI.schshtick.getRawButton(4) == true && OI.schshtick.getRawButton(3) == false) {
    		elevatorRight = (RobotMap.elevatorSwitch1.get()) ? .75 : 0;
    	}
    	else if (OI.schshtick.getRawButton(3) == true && OI.schshtick.getRawButton(4) == false) {
    		elevatorRight = (RobotMap.elevatorSwitch2.get()) ? -.75 : 0;
    	}
    	else {
    		elevatorRight = 0;
    	}
    	
    	

    	RobotMap.elevator1.set(elevatorLeft);
    	RobotMap.elevatorright.set(elevatorRight);
    	
    	/*if (stick.getRawButton(6) == true) {
    		//RobotMap.swiffer.set(Relay.Value. kReverse);
    	}*/
    	
    	// /*
    	//Arm that flips things (There is an easier way to do things, this is just quick code)
    	
    	if (OI.stick.getRawButton(5) == true && OI.stick.getRawButton(4) == false) {
    		//flipArmV = 1;
    		RobotMap.flipArm.set(Relay.Value.kOn);
    		RobotMap.flipArm.set(Relay.Value.kReverse);
    	}
    	else if (OI.stick.getRawButton(4) == true && OI.stick.getRawButton(5) == false) {
    		//flipArmV = -1;
    		RobotMap.flipArm.set(Relay.Value.kForward);
    	}
    	else {//if (OI.schshtick.getRawButton(4) == false || OI.schshtick.getRawButton(5) == false){
    		//flipArmV = 0;
    		RobotMap.flipArm.set(Relay.Value.kOff);
    	}
    	

    	//CAMERA CODE
    	//if (OI.stick.getRawButton(1) == true) {
    		//RobotMap.usbCamera.startAutomaticCapture("cam2");
    	//}
    	//if (OI.stick.getRawButton(2) == false) {
    		//RobotMap.ipCamera.startAutomaticCapture("cam1");
    	//}
    	
    	// */
    	
    	
    	/*if(stick.getRawButton(4) == true){
    		noodleBrush.set(1);
    	}else{
    		noodleBrush.set(0);
    	}*/
    	
    	//Lift 13in (Via limit switch)
    	/*if (stick.getRawButton(3) == true) {
    		boolean loopControl = false;
    		while (loopControl != true) {	
    			RobotMap.elevator1.set(0.5);
    			RobotMap.elevator2.set(0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl = true;
    		}
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
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
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
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
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
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
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
    	}
    	//allahu akbar
    	//Up Completely
    	if (schtick.getRawButton(3) == true) {
    		int loopControl = 0;
    		while (loopControl < 4) {	
    			RobotMap.elevator1.set(0.5);
    			RobotMap.elevator2.set(0.5);
    		if (RobotMap.elevatorSwtich1.get() == true)
    			loopControl += 1;
    		}
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
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
    		RobotMap.elevator1.set(0);
    		RobotMap.elevator2.set(0);
    		
    	}*/
    	//Rotate External Arm
    	/*while (OI.schshtick.getRawButton(4)) {
    		RobotMap.extArm.set(0.5);
    	}
    	RobotMap.extArm.set(0);
    	//Rotate External Arm the other way
    	while (OI.schshtick.getRawButton(5)) {
    		RobotMap.extArm.set(-0.5);
    	}
    	RobotMap.extArm.set(0);*/
    	/*
    	while (OI.schshtick.getZ() > 50) {
    		RobotMap.swiffer.set(Relay.Value.kForward);
    	}
    	RobotMap.swiffer.set(Relay.Value.kOff);
    	*/
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
