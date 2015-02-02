/*
 * This code is not very clear as to what it does.
 * Don't be afraid to over comment your code.  It really does help! 
 */
package org.usfirst.frc.team2345.robot.commands;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2345.robot.OI.*;
import org.usfirst.frc.team2345.robot.RobotMap.*;
import org.usfirst.frc.team2345.robot.Robot;

/**
 *
 */
public class ExampleCommand extends Command {
	Talon upLeftDrive = org.usfirst.frc.team2345.robot.RobotMap.upLeftDrive;
 	Talon upRightDrive = org.usfirst.frc.team2345.robot.RobotMap.upRightDrive;
 	Talon downLeftDrive = org.usfirst.frc.team2345.robot.RobotMap.downLeftDrive;
 	Talon downRightDrive = org.usfirst.frc.team2345.robot.RobotMap.downRightDrive;
 	
 	Victor upLeftTurn = org.usfirst.frc.team2345.robot.RobotMap.upLeftTurn;
 	Victor upRightTurn = org.usfirst.frc.team2345.robot.RobotMap.upRightTurn;
 	Victor downLeftTurn = org.usfirst.frc.team2345.robot.RobotMap.downLeftTurn;
 	Victor downRightTurn = org.usfirst.frc.team2345.robot.RobotMap.downRightTurn;
    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.exampleSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//THIS IS AUTONOMOUS DEBUGGING STUFF
    	double speed = 0.1;
    	upLeftDrive.set(speed);
    	upRightDrive.set(speed);
    	downRightDrive.set(speed);
    	downLeftDrive.set(speed);
    	Timer.delay(5);
    	
    	upLeftDrive.set(0);
    	upRightDrive.set(0);
    	downRightDrive.set(0);
    	downLeftDrive.set(0);
    	Timer.delay(5);
    	
    	upLeftDrive.set(-speed);
    	upRightDrive.set(-speed);
    	downRightDrive.set(-speed);
    	downLeftDrive.set(-speed);
    	Timer.delay(5);
    	
    	upLeftDrive.set(0);
    	upRightDrive.set(0);
    	downRightDrive.set(0);
    	downLeftDrive.set(0);
    	Timer.delay(5);
    	
    	upLeftTurn.set(speed);
    	upRightTurn.set(speed);
    	downRightTurn.set(speed);
    	downLeftTurn.set(speed);
    	Timer.delay(5);
    	
    	upLeftTurn.set(0);
    	upRightTurn.set(0);
    	downRightTurn.set(0);
    	downLeftTurn.set(0);
    	Timer.delay(5);
    	
    	upLeftTurn.set(-speed);
    	upRightTurn.set(-speed);
    	downRightTurn.set(-speed);
    	downLeftTurn.set(-speed);
    	Timer.delay(5);
    	
    	upLeftTurn.set(0);
    	upRightTurn.set(0);
    	downRightTurn.set(0);
    	downLeftTurn.set(0);
    	Timer.delay(5);
    	
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
