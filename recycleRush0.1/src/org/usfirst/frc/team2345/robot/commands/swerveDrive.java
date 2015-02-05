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
	final double mult = 1.15278;
	double uRT;
	double uLT;
	double dRT;
	double dLT;
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
    	
    	double upRightEncoder = upRightEnc.get() / mult;
    	double upLeftEncoder = upLeftEnc.get() / mult;
    	double downRightEncoder = downRightEnc.get() / mult;
    	double downLeftEncoder = downLeftEnc.get() / mult;
    	
    	double str = stick.getY(); // stick.getThrottle();(forward/reverse command, -1 to +1)
 		double fwd = stick.getX() * -1; // stick.getThrottle();(strafe right command, -1 to +1)
 		double rcw = schtick.getX(); // schtick.getThrottle();(rotate clockwise command, -1 to +1)
 		
 		//math for finding the degrees of all the wheels and their vectors
 		/*Jon again.*/
 		double A = str-rcw*(l/r);
 		double B = str+rcw*(l/r);
 		double C = fwd-rcw*(w/r);
 		double D = fwd+rcw*(w/r);
 		
 		double ws1 = Math.sqrt(B*B + C*C);
 		double ws2 = Math.sqrt(B*B + D*D);
 		double ws3 = Math.sqrt(A*A + D*D);
 		double ws4 = Math.sqrt(A*A + C*C);
 		double max = Math.max(ws1, Math.max(ws2, Math.max(ws3, ws4)));
 		
 		//sets all the angles and powers from the above to the wheels
 		// for the time being, this is fine, but in the future, I would suggest putting the relative positions of the wheels here instead of putting in generic numbers, because it is slightly unclear.
 		double wheelSpeedOne = (max>1) ? ws1/max : ws1;
 		double wheelSpeedTwo = (max>1) ? ws2/max : ws2;
 		double wheelSpeedThree = (max>1) ? ws3/max : ws3;
 		double wheelSpeedFour = (max>1) ? ws4/max : ws4;
 		double wheelAngleOne = ( C == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(C,B));
 		double wheelAngleTwo = ( D == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(D,B));
 		double wheelAngleThree = ( D == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(D,A));
 		double wheelAngleFour = ( C == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(C,A));
 		
 		/*upRightEncoder %= 360;
 		upLeftEncoder %= 360;
 		downLeftEncoder %= 360;
 		downRightEncoder %= 360;
 		*/
 		double wheelAngleOnePrime = 360-wheelAngleOne <= upRightEncoder /*WheelAngleOne*/ ? -(360 - wheelAngleOne) : wheelAngleOne;
 		double wheelAngleTwoPrime = 360-wheelAngleTwo <= upLeftEncoder /*WheelAngleTwo*/ ? -(360 - wheelAngleTwo) : wheelAngleTwo;
 		double wheelAngleThreePrime = 360-wheelAngleThree <= downRightEncoder /*WheelAngleThree*/ ? -(360 - wheelAngleThree) : wheelAngleThree;
 		double wheelAngleFourPrime = 360-wheelAngleFour <= downLeftEncoder /*WheelAngleFour*/ ? -(360 - wheelAngleFour) : wheelAngleFour;
 		
 		/*if (360-wheelAngleOne <= wheelAngleOne)
 			wheelAngleOnePrime = -(360 - wheelAngleOne);
 		
 		if (360-wheelAngleTwo <= wheelAngleTwo)
 			wheelAngleTwoPrime = -(360 - wheelAngleTwo);
 		
 		if (360-wheelAngleThree <= wheelAngleThree)
 			wheelAngleThreePrime = -(360 - wheelAngleThree);
 		
 		if (360-wheelAngleFour <= wheelAngleFour)
 			wheelAngleFourPrime = -(360 - wheelAngleFour);
 		*/
 		
 		
 		double absAngleOne = Math.abs(wheelAngleOnePrime - upRightEncoder) > 45 ? 1 : Math.abs(wheelAngleOnePrime - upRightEncoder) / 45;
 		double uRTi;
 		if (wheelAngleOnePrime - upRightEncoder > 0 )
 			uRTi = absAngleOne * -1;
 		else
 			uRTi = absAngleOne;
 			
 		double absAngleTwo = Math.abs(wheelAngleTwoPrime - upLeftEncoder) > 45 ? 1 : Math.abs(wheelAngleTwoPrime - upLeftEncoder) / 45;
 		double uLTi;
 		if (wheelAngleTwoPrime - upLeftEncoder > 0 )
 			uLTi = absAngleTwo * -1;
 		else
 			uLTi = absAngleTwo;
 		
 		double absAngleThree = Math.abs(wheelAngleThreePrime - downLeftEncoder) > 45 ? 1 : Math.abs(wheelAngleThreePrime - downLeftEncoder) / 45;
 		double dLTi;
 		if (wheelAngleThreePrime - downLeftEncoder > 0 )
 			dLTi = absAngleThree * -1;
 		else
 			dLTi = absAngleThree;
 		
 		double absAngleFour = Math.abs(wheelAngleFourPrime - downRightEncoder) > 45 ? 1 : Math.abs(wheelAngleFourPrime - downRightEncoder) / 45;
 		double dRTi;
 		if (wheelAngleFourPrime - downRightEncoder > 0 )
 			dRTi = absAngleFour * -1;
 		else
 			dRTi = absAngleFour;
 		
 		uRT = (wheelAngleOnePrime > 0) ? uRTi * -1 : uRTi;
 		uLT = (wheelAngleTwoPrime > 0) ? uLTi * -1 : uLTi;
 		dLT = (wheelAngleThreePrime > 0) ? dLTi * -1 : dLTi;
 		dRT = (wheelAngleFourPrime > 0) ? dRTi * -1 : dRTi;
 		
 		/*	
 		if (wheelAngleOne > 0)
 			uRT = (wheelAngleOne - upRightEncoder < 45) ? 1 : (wheelAngleOne - upRightEncoder) / 45;
 		else
 			uRT = (wheelAngleOne - upRightEncoder > 45) ? -1 : (wheelAngleOne - upRightEncoder) / 45;
 		
 		
 		if (wheelAngleTwo > 0)
 			uLT = (wheelAngleTwo - upLeftEncoder < 45) ? -1 : (wheelAngleTwo - upLeftEncoder) / 45;
 		else
 			uLT = (wheelAngleTwo - upLeftEncoder > 45) ? 1 : (wheelAngleTwo - upLeftEncoder) / 45;
 		
 		
 		if (wheelAngleThree > 0)
 			dLT = (wheelAngleThree - downLeftEncoder < 45) ? -1 : (wheelAngleThree - downLeftEncoder) / 45;
 		else
 			dLT = (wheelAngleThree - downLeftEncoder > 45) ? 1 : (wheelAngleThree - downLeftEncoder) / 45;
 		
 		
 		if (wheelAngleFour > 0)
 			dRT = (wheelAngleFour - downRightEncoder < 45) ? -1 : (wheelAngleFour - downRightEncoder) / 45;
 		else
 			dRT = (wheelAngleFour - downRightEncoder > 45) ? 1 : (wheelAngleFour - downRightEncoder) / 45;
 		
 		*/
 		
 		
 		/*
 		wheelAngleOne *= mult;
 		wheelAngleTwo *= mult;
 		wheelAngleThree *= mult;
 		wheelAngleFour *= mult;
 		*/
 		
 		/*double uRT = (wheelAngleOne - upRightEnc.get() > 45 * mult) ? -1 : -( wheelAngleOne - upRightEnc.get()) / (45 * mult);
 		double uLT = (wheelAngleTwo - upLeftEnc.get() > 45 * mult) ? -1 : -( wheelAngleTwo - upLeftEnc.get()) / (45 * mult);
 		double dLT = (wheelAngleThree - downLeftEnc.get() > 45 * mult) ? -1 : -( wheelAngleThree - downLeftEnc.get()) / (45 * mult);
 		double dRT = (wheelAngleFour - downRightEnc.get() > 45 * mult) ? -1 : -( wheelAngleFour - downRightEnc.get()) / (45 * mult);
 		*/
 		upRightTurn.set(uRT);				
		upLeftTurn.set(uLT);
		downLeftTurn.set(dLT);
		downRightTurn.set(dRT);
 		
 		
 		
 		
 		//pls check to make sure the variables above connect to the right motors below
 		//Do we really need this?
 		//|
 		//V
 		if (Math.abs(rcw) > 0.1) {
			wheelSpeedTwo *= -1;
			wheelSpeedFour *= -1;
		}
 			upLeftDrive.set(wheelSpeedTwo);
 	 		upRightDrive.set(wheelSpeedOne);
 	 		downLeftDrive.set(wheelSpeedThree);
 	 		downRightDrive.set(wheelSpeedFour);
 		
 	 	/*if (rcw != 0 && fwd !=0) {
 	 		if (rcw < -0.1) {
 	 			upRightDrive.set(wheelSpeedOne * 1.5);
 	 			downRightDrive.set(wheelSpeedFour * -1.5);
 	 			upLeftDrive.set(wheelSpeedTwo / -1.5);
 	 			
 	 			
 	 		} else if (rcw > 0.1) {
 	 			upLeftDrive.set(wheelSpeedTwo * 1.5);
 	 			downLeftDrive.set(wheelSpeedThree * -1.5);
 	 			upRightDrive.set(wheelSpeedOne / -1.5);
 	 		}
 	 	}*/
 		
 		SmartDashboard.putNumber("uL", (double) upLeftEnc.get());
 		SmartDashboard.putNumber("uR", (double) upRightEnc.get());
 		SmartDashboard.putNumber("dL", (double) downLeftEnc.get());
 		SmartDashboard.putNumber("dR", (double) downRightEnc.get());
 		SmartDashboard.putNumber("wS1", (double) wheelSpeedOne);
 		SmartDashboard.putNumber("wS2", (double) wheelSpeedTwo);
 		SmartDashboard.putNumber("wS3", (double) wheelSpeedThree);
 		SmartDashboard.putNumber("wS4", (double) wheelSpeedFour);
 		SmartDashboard.putNumber("wA1", (double) wheelAngleOne);
 		SmartDashboard.putNumber("wA2", (double) wheelAngleTwo);
 		SmartDashboard.putNumber("wA3", (double) wheelAngleThree);
 		SmartDashboard.putNumber("wA4", (double) wheelAngleFour);
 		
 		//this code essentially say that if enc is greater than, go one way, if less go the other, if equal do nothing
 		/* Quick question from Jon: where are the limits for the encoder?
 		 * I ask because if the encoder determines that the range is -180 to 180, with both -180 and 180 being slightly different sides of complete reverse,
 		 * then what if the wheel is at the -180 degree mark and you tell it to move slightly more towards the positive 180 degree mark?
 		 * Will the wheels stop and try to go all the way around to the positive 180 by taking the long way around (-180 to 0 to 180)?  Or does it already know to avoid this?*/
 		

 		
 
 		
 		/*double uRTfinal = Math.round(uRT * 1000) / 1000;
 		double uLTfinal = Math.round(uLT * 1000) / 1000;
 		double dLTfinal = Math.round(dLT * 1000) / 1000;
 		double dRTfinal = Math.round(dRT * 1000) / 1000;*/
 		
 		
 		
 			
 			
 			
 		/*	
 		if (schtick.getRawButton(7)) {
 			upRightTurn.set(1);
 			upLeftTurn.set(1);
 			downLeftTurn.set(1);
 			downRightTurn.set(1);
 			boolean uLPressed = false;
 			boolean uRPressed = false;
 			boolean dLPressed = false;
 			boolean dRPressed = false;
 			boolean isComplete = false;
 			while (isComplete != true) {
 				if (RobotMap.uLSwitch.get() == true) {
 					uLPressed = true;
 					upLeftTurn.set(0);
 					upLeftEnc.reset();
 				}
 				if (RobotMap.uRSwitch.get() == true) {
 					uRPressed = true;
 					upRightTurn.set(0);
 					upRightEnc.reset();
 				}
 				if (RobotMap.dLSwitch.get() == true) {
 					dLPressed = true;
 					downLeftTurn.set(0);
 					downLeftEnc.reset();
 				}
 				if (RobotMap.dRSwitch.get() == true) {
 					dRPressed = true;
 					downRightTurn.set(0);
 					downRightEnc.reset();
 				}
 				if (uLPressed == true && uRPressed == true && dLPressed == true && dRPressed == true) {
 					isComplete = true;
 				}
 			}
 		}*/
 			
 		
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 	
 		/*if (wheelAngleOne < upRightEnc.get() + diff) {
 			upRightTurn.set(0.5);
 		
 			}else if(wheelAngleOne > upRightEnc.get() - diff){
 				upRightTurn.set(-0.5);
 			}else{
 				upRightTurn.set(0);}
 		
 		if (wheelAngleTwo < upLeftEnc.get() + diff) {
 			upLeftTurn.set(0.5);
 		
 			}else if(wheelAngleTwo > upLeftEnc.get() - diff){
 				upLeftTurn.set(-0.5);
 			}else{
 				upLeftTurn.set(0);}
 		
 		if (wheelAngleThree < downLeftEnc.get() + diff) {
 			downLeftTurn.set(0.5);
 		
 			}else if(wheelAngleThree > downLeftEnc.get() - diff){
 				downLeftTurn.set(-0.5);
 			}else{
 				downLeftTurn.set(0);}
 		
 		if (wheelAngleFour < downRightEnc.get() + diff) {
 			downRightTurn.set(0.5);
 		
 			}else if(wheelAngleFour > downRightEnc.get() - diff){
 				downRightTurn.set(-0.5);
 			}else{
 				downRightTurn.set(0);}*/
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
