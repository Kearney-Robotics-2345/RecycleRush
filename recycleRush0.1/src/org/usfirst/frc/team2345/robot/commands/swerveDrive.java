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
   
    
    public swerveDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    double directionUR;
    double directionUL;
    double directionDR;
    double directionDL;
    
    protected void execute() {
    	//Getting Joystick Inputs
    	double strX = OI.stick.getX();
    	double strY = OI.stick.getY();
    	double rota = OI.schtick.getX();
  
    	
    	//Now determine the "triangle" created by both X and Y
    	double A = Math.abs(strX);
    	double B = Math.abs(strY);
 		
    	double theta = Math.toDegrees(Math.atan2(strY,strX));
    	
    	theta += 90;
    	
    	final double encoderToDegreeFactor = 1.15278;
    	
    	double UREnc = RobotMap.upRightEnc.get() * encoderToDegreeFactor;
    	double ULEnc = RobotMap.upLeftEnc.get() * encoderToDegreeFactor;
    	double DREnc = RobotMap.downRightEnc.get() * encoderToDegreeFactor;
    	double DLEnc = RobotMap.downLeftEnc.get() * encoderToDegreeFactor;
    	//This code determines which direction steer motors have to go to reach target
    	if (theta - UREnc < 4 && theta - UREnc > -4) {
    		directionUR = 0;
    	} else if (theta > UREnc) {
    		directionUR = 1;
    	} else if (theta < UREnc) {
    		directionUR = -1;
    	} else {
    		//Prevent nasty math from messing up robot
    		directionUR = 0;
    	}
    	//Repeat for other motors
    	if (Math.abs(theta - ULEnc) <= 0.1 && Math.abs(theta - ULEnc) >= -0.1) {
    		directionUL = 0;
    	} else if (theta > ULEnc) {
    		directionUL = 1;
    	} else if (theta < ULEnc) {
    		directionUL = -1;
    	} else {
    		directionUL = 0;
    	}
    	
    	if (Math.abs(theta - DREnc) <= 0.1 && Math.abs(theta - DREnc) >= -0.1) {
    		directionDR = 0;
    	} else if (theta > UREnc) {
    		directionDR = 1;
    	} else if (theta < UREnc) {
    		directionDR = -1;
    	} else {
    		directionDR = 0;
    	}
    	
    	if (Math.abs(theta - DLEnc) <= 0.1 && Math.abs(theta - DLEnc) >= -0.1) {
    		directionDL = 0;
    	} else if (theta > DLEnc) {
    		directionDL = 1;
    	} else if (theta < DLEnc) {
    		directionDL = -1;
    	} else {
    		directionDL = 0;
    	}
    	//Change the positive theta to a corresponding value
    	double turnAngleUR = theta; //* directionUR;
    	double turnAngleUL = theta * directionUL;
    	double turnAngleDR = theta * directionDR;
    	double turnAngleDL = theta * directionDL;
    	//Initialize the variables for turn motor speeds
    	double turnCommandUR;
    	double turnCommandUL;
    	double turnCommandDR;
    	double turnCommandDL;
    	//Determine the motors speeds...speed is decreased as the current angle approaches target
    	if ( turnAngleUR > 0) {
    		turnCommandUR = -0.5;
    		//turnCommandUR = (Math.abs(turnAngleUR - UREnc) > 60) ? -0.5 :(Math.abs(theta - UREnc) / 60) * -1; //* directionUR);
    	} else if( turnAngleUR < 0) {
    		turnCommandUR = 0.5; //turnCommandUR = (Math.abs(turnAngleUR - UREnc) > 60) ? 0.5 :(Math.abs(theta - UREnc) / 60) * 1;// * directionUR);
    	} else {
    		//Prevent nasty math from screwing up the robot ie: it prevents robot from moving
    		turnCommandUR = 0;
    	}
    	//Repeat for other motors
    	if ( turnAngleUL < 0) {
    		turnCommandUL = (Math.abs(turnAngleUL - ULEnc) > 45) ? -1 :(Math.abs(theta - ULEnc) * directionUL);
    	} else if( turnAngleUL > 0) {
    		turnCommandUL = (Math.abs(turnAngleUL - ULEnc) > 45) ? 1 :(Math.abs(theta - ULEnc) * directionUL);
    	} else {
    		turnCommandUL = 0;
    	}
    	
    	if ( turnAngleDR < 0) {
    		turnCommandDR = (Math.abs(turnAngleDR - DREnc) > 45) ? -1 :(Math.abs(theta - DREnc) * directionDR);
    	} else if( turnAngleDR > 0) {
    		turnCommandDR = (Math.abs(turnAngleDR - DREnc) > 45) ? 1 :(Math.abs(theta - DREnc) * directionDR);
    	} else {
    		turnCommandDR = 0;
    	}
    	
    	if ( turnAngleDL < 0) {
    		turnCommandDL = (Math.abs(turnAngleDL - DLEnc) > 45) ? -1 :(Math.abs(theta - DLEnc) * directionDL);
    	} else if( turnAngleDL > 0) {
    		turnCommandDL = (Math.abs(turnAngleDL - DLEnc) > 45) ? 1 :(Math.abs(theta - DLEnc) * directionDL);
    	} else {
    		turnCommandDL = 0;
    	}
    	//Set motors to calculated values
    	RobotMap.upRightTurn.set(turnCommandUR);
    	//RobotMap.upLeftTurn.set(turnCommandUL);
    	//RobotMap.downRightTurn.set(turnCommandDR);
    	//RobotMap.downLeftTurn.set(turnCommandDL);
    	
    	SmartDashboard.putNumber("wA1", turnAngleUR);
    	SmartDashboard.putNumber("wA2", turnAngleUL);
    	SmartDashboard.putNumber("wA3", turnAngleDR);
    	SmartDashboard.putNumber("wA4", turnAngleDL);
    	SmartDashboard.putNumber("wA1", turnAngleUR);
    	SmartDashboard.putNumber("uR", turnAngleUR);
    	SmartDashboard.putNumber("dR", turnAngleDR);
    	SmartDashboard.putNumber("uL", turnAngleUL);
    	SmartDashboard.putNumber("dL", turnAngleUR);
    	SmartDashboard.putNumber("uRC", turnCommandUR);
    	SmartDashboard.putNumber("dRC", turnCommandDR);
    	SmartDashboard.putNumber("uLC", turnCommandUL);
    	SmartDashboard.putNumber("dLC", turnCommandUR);


    	

    	
    	
    	//Now set up drive
    	
    	double C = Math.sqrt((A*A)+(B*B));
    	
    	//leftDrive and rightDrive are the specific motor vaulues for the left and right sides of the robot respectively
    	double leftDrive;
    	double rightDrive;
    	//Now account for rotating robot
    	if (rota < 0) {
    		//Possibly rota/2
    		leftDrive = C - rota;
    		rightDrive = C + rota;
    	} else if (rota > 0) {
    		leftDrive = C + rota;
    		rightDrive = C - rota;
    	} else if (rota == 0) {
    		leftDrive = C;
    		rightDrive = C;
    	} else {
    		leftDrive = 0;
    		rightDrive = 0;
    	}
    	//Set drive motor speeds
    	//RobotMap.upRightDrive.set(rightDrive);
    	//RobotMap.downRightDrive.set(rightDrive);
    	//RobotMap.upLeftDrive.set(leftDrive);
    	//RobotMap.downLeftDrive.set(leftDrive);
    	//#Win #SWEG
    	
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
