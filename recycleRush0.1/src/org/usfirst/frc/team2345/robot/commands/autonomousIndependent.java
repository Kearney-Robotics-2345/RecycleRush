package org.usfirst.frc.team2345.robot.commands;

import org.usfirst.frc.team2345.robot.OI;
import org.usfirst.frc.team2345.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autonomousIndependent extends Command {

    public autonomousIndependent() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	////REAL CODE
    	RobotMap.upLeftTurn.set(0);
    	RobotMap.upRightTurn.set(0);
    	RobotMap.downRightTurn.set(0);
    	RobotMap.downLeftTurn.set(0);
    	
    	RobotMap.elevator1.set(.7);
    	Timer.delay(2);
    	RobotMap.elevator1.set(0);
    	
    	//Move Back
    	RobotMap.upLeftDrive.set(-.5);
    	RobotMap.upRightDrive.set(-.5);
    	RobotMap.downLeftDrive.set(-.5);
    	RobotMap.downRightDrive.set(-.5);    	
    	Timer.delay(2.75); 
    	
    	//Stop
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0);    	
    	
    }

    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
    }

    //What are these for?
    //boolean potatoFailSafe = false;
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		RobotMap.upLeftTurn.set(0);
		RobotMap.upRightTurn.set(0);
		RobotMap.downLeftTurn.set(0);
		RobotMap.downRightTurn.set(0);
		RobotMap.downLeftDrive.set(0);
		RobotMap.upLeftDrive.set(0);
		RobotMap.downRightDrive.set(0);
		RobotMap.upRightDrive.set(0);
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		//potatoFailSafe = true;
	}
}
