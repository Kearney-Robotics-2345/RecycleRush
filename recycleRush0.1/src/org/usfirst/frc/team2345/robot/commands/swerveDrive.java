package org.usfirst.frc.team2345.robot.commands;


import org.usfirst.frc.team2345.robot.OI;
import org.usfirst.frc.team2345.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2345.robot.RobotMap;

/**
 *
 */
//Test Comment
public class swerveDrive extends Command {
	boolean commandStatus = false;
	//enabling all the encoders
	Talon upLeftDrive = RobotMap.upLeftDrive;
	Talon upRightDrive = RobotMap.upRightDrive;
	Talon downLeftDrive = RobotMap.downLeftDrive;
	Talon downRightDrive = RobotMap.downRightDrive;
	
	Victor upLeftTurn = RobotMap.upLeftTurn;
	Victor upRightTurn = RobotMap.upRightTurn;
	Victor downLeftTurn = RobotMap.downLeftTurn;
	Victor downRightTurn = RobotMap.downRightTurn;
	
    //all joystick code
    Joystick stick = OI.stick;
    Joystick schtick = OI.schtick;
    
    Encoder upRightEnc = RobotMap.upRightEnc;
    Encoder upLeftEnc = RobotMap.upLeftEnc;
    Encoder downRightEnc = RobotMap.downRightEnc;
    Encoder downLeftEnc = RobotMap.downLeftEnc;
    
    
    
    //all turn motors initialize
    /*Victor upLeftTurn = new Victor(4);
    Victor upRightTurn = new Victor(3);
    Victor downLeftTurn = new Victor(5);
    Victor downRightTurn = new Victor(7);
    
    //all drive motors initialize
    Talon upLeftDrive = new Talon(6);
    Talon upRightDrive = new Talon(2);
    Talon downLeftDrive = new Talon(1);
    Talon downRightDrive = new Talon(0);*/
    static int l = 31;//19; //(wheelbase, inches)
	static int w = 19;//32; //(trackwidth, inches)
	double r = Math.sqrt(l*l + w*w);
    public swerveDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Getting Joystick Inputs
    	double strX = OI.stick.getX();
    	double strY = OI.stick.getY();
    	double rota = OI.schtick.getX();
    	
    	//This determines whether the X-Axis is negativ or not
    	double dirX = (strX > 0) ? 1 : -1;
    	double dirY = (strY > 0) ? 1 : -1;
    	
    	//Now determine the "triangle" created by both X and Y
    	double A = Math.abs(strX);
    	double B = Math.abs(strY);
 		
    	double theta = (strX != 0 && strY != 0) ? Math.atan(B/A) : 90;
    	double stickAngle = 90-theta;
    	
    	double targetURAngle = stickAngle * dirX;
    	
    	//Now get encoder values in degrees
    	final double encoderToDegreeFactor = 1.15278;
    	double currentURAngle = RobotMap.upRightEnc.get() * encoderToDegreeFactor;
    	
    	
    	//Set turn motors to corresponding values
    	double URTPre = (targetURAngle != 0) ? 0.5 : 0;
    	//double URT = (targetUrAngle < currentURAngle) ? URTPre * -1 : URTPre;
    	RobotMap.upRightTurn.set(URTPre);
 			commandStatus = true;
		}
    	
    
    

    // Make this return true when this Command no longer needs to run execute()

    protected boolean isFinished() {
        return commandStatus;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    protected void imageProccessing() {
    	
    }
}
