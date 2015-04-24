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

/* Jon:
 * I am writing in commented pseudo code for the elevator.  I tried to do this in a copy,
 * but I could not create the copy to begin with.
 * I am commenting this code because my knowledgew of Java is minimal, and I do not want to risk 
 * breaking the robot or breaking code that we know works.
 * 
 * (by the way, the code is reversing left and right and clockwise and counterclockwise)
 * 
 * My three comments are on lines 55-58, 93-98, and 179-187
 * as well as ***-*** in the RobotMap.java section
 * 
 * please look over these sections and verify that they are syntactically correct for the code.
 * */

public class swerveDrive extends Command {
	double uRT;
    double uLT;
    double dRT;
    double dLT;
    double uRTDir;
    double uLTDir;
    double dRTDir;
    double dLTDir;
    // multiplier ratio of encoder ticks to degrees
    double mult = 1.15278;
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
    /* Jon:
    *
    * Victor elevator = RobotMap.elevator1;
    // end*/

    //all joystick code
    Joystick stick = OI.stick;
    Joystick schtick = OI.schtick;

    Encoder upRightEnc = RobotMap.upRightEnc;
    Encoder upLeftEnc = RobotMap.upLeftEnc;
    Encoder downRightEnc = RobotMap.downRightEnc;
    Encoder downLeftEnc = RobotMap.downLeftEnc;
 
    static int l = 17;//19; //(wheelbase, inches)
    
    static int w = 31;//32; //(trackwidth, inches)
    double r = Math.sqrt(l * l + w * w);

    public swerveDrive() {
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if (auto = true) {
        
    	// used to change encoder ticks to degrees
    	double upRightEncoder = (upRightEnc.get() / mult);
    	double upLeftEncoder = (upLeftEnc.get() / mult);
    	double downRightEncoder = (downRightEnc.get() / mult);
    	double downLeftEncoder = (downLeftEnc.get() / mult);
    
    	//fixes the problem of negative ticks on the encoder converting to inverted degrees(-1* translates to 1* opposed to 359* before
    	double uRE = (upRightEncoder < 0) ? 360 - Math.abs(upRightEncoder % 360) : upRightEncoder % 360;
    	double uLE = (upLeftEncoder < 0) ? 360 - Math.abs(upLeftEncoder % 360) : upLeftEncoder % 360; //upLeftEncoder%= 360;
    	double dLE = (downLeftEncoder < 0) ? 360 - Math.abs(downLeftEncoder % 360) : downLeftEncoder % 360; //downLeftEncoder %= 360;
    	double dRE = (downRightEncoder < 0) ? 360 - Math.abs(downRightEncoder % 360) : downRightEncoder % 360; //downRightEncoder %= 360;
    	
    	
    	
    	double str = stick.getX() * Math.abs(stick.getX()) * -1; // stick.getThrottle();(forward/reverse command, -1 to +1)
        double fwd = stick.getY() * Math.abs(stick.getY()); // stick.getThrottle();(strafe right command, -1 to +1)
        double rcw = schtick.getX() * Math.abs(schtick.getX()) * -1 ; // schtick.getThrottle();(rotate clockwise command, -1 to +1)


        //for any questions on code in lines 106-126, refer to Ether on cheif delphi
        //math for finding the degrees of all the wheels and their vectors
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
        double wheelAngleOne = ( C == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(B,C));
        double wheelAngleTwo = ( D == 0 && B == 0) ? 0 : Math.toDegrees(Math.atan2(B,D));
        double wheelAngleThree = ( D == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(A,D));
        double wheelAngleFour = ( C == 0 && A == 0) ? 0 : Math.toDegrees(Math.atan2(A,C));
        
        
        //this takes the difference of the wanted angle and current angle to see the shorter
        //distance, then it modifies motor value based on angle
        double wA1 = (wheelAngleOne < 0) ? 360 - Math.abs(wheelAngleOne) : wheelAngleOne;
        double uRTdr;
        
        if(Math.abs(uRE-wA1) < 1) {
        	uRTdr = 0;
        }else if (Math.abs(uRE - wA1) <= 180){			// if absoulte of (current angle - wanted angle) is less than or equal to 180
        	uRTdr = ((uRE -wA1) < 0) ? -1 : 1;		//Direction = (if (current angle - wanted angle) is less than 0) true: - False: +
        }else if(Math.abs(uRE - wA1) > 180){		
        	uRTdr = ((uRE -wA1) < 0) ? 1 : -1;
        //
        }else if(Math.abs(uRE - wA1) == 180) {
        	uRTdr = 1;
        //
        }else{
        	uRTdr = 0;
        }
        //these section are just repeats of the above section for the other wheels
        double wA2 = (wheelAngleTwo < 0) ? 360 - Math.abs(wheelAngleTwo) : wheelAngleTwo;
        double uLTdr;
        
        if (Math.abs(uLE - wA2) <= 180){			
        	uLTdr = ((uLE -wA2) < 0) ? -1 : 1;
        }else if(Math.abs(uLE - wA2) > 180){
        	uLTdr = ((uLE -wA2) < 0) ? 1 : -1;
        //
        }else if(Math.abs(uLE - wA2) == 180) {
        	uLTdr = 1;
        //  
        }else{
        	uLTdr = 0;
        }
        
        double wA3 = (wheelAngleThree < 0) ? 360 - Math.abs(wheelAngleThree) : wheelAngleThree;
        double dLTdr;
        
        if (Math.abs(dLE - wA3) <= 180){
        	dLTdr = ((dLE -wA3) < 0) ? -1 : 1;
        }else if(Math.abs(dLE - wA3) > 180){
        	dLTdr = ((dLE - wA3) < 0) ? 1 : -1;
        //
        }else if(Math.abs(dLE - wA3) == 180) {
        	dLTdr = 1;
        //
        }else{
        	dLTdr = 0;
        }
        
        double wA4 = (wheelAngleFour < 0) ? 360 - Math.abs(wheelAngleFour) : wheelAngleFour;
        double dRTdr;
        
        if (Math.abs(dRE - wA4) <= 180){
        	dRTdr = ((dRE -wA4) < 0) ? -1 : 1;
        }else if(Math.abs(dRE - wA4) > 180){
        	dRTdr = ((dRE -wA4) < 0) ? 1 : -1;
        //
        }else if(Math.abs(dRE - wA4) == 180) {
        	dRTdr = 1;
        //
        }else{
        	dRTdr = 0;
        }
        
        double rampmod = OI.stick.getRawButton(3) ? .5 : 1;
        double rampmodX = OI.schtick.getRawButton(3) ? .25 : 1;
         	
        

        
        // I added a 0.7 to slow down the robot, as it was discovered that at full power, the robot would tip over.
        upLeftDrive.set(wheelSpeedTwo * 0.7 * rampmod * rampmodX); 
        upRightDrive.set(wheelSpeedOne * 0.7 * rampmod * rampmodX);
        downLeftDrive.set(wheelSpeedThree * 0.7 * rampmod * rampmodX);
        downRightDrive.set(wheelSpeedFour * 0.7 * rampmod * rampmodX);

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
        
        //Test that Mod is working
        SmartDashboard.putNumber("uLe", (double) upLeftEncoder);
        SmartDashboard.putNumber("uRe", (double) upRightEncoder);
        SmartDashboard.putNumber("dLe", (double) downLeftEncoder);
        SmartDashboard.putNumber("dRe", (double) downRightEncoder);
        
        //exponetial decrease of motor input based on distance from wanted degree
        double uRT = (Math.abs(uRE - wA1) > 80) ? 0.7 : (Math.abs(uRE - wA1)) / 70;
        double uLT = (Math.abs(uLE - wA2) > 80) ? 0.7 : (Math.abs(uLE - wA2)) / 70;
        double dLT = (Math.abs(dLE - wA3) > 80) ? 0.7 : (Math.abs(dLE - wA3)) / 70;
        double dRT = (Math.abs(dRE - wA4) > 80) ? 0.7 : (Math.abs(dRE - wA4)) / 70;
             
        //double uREm = (upRightEncoder < 0) ? -1 : 1;
        //double uLEm = (upRightEncoder < 0) ? -1 : 1;
        //double dLEm = (upRightEncoder < 0) ? -1 : 1;
        //double dREm = (upRightEncoder < 0) ? -1 : 1;

        //takes motor power and mods from lesser angle code
        upRightTurn.set(uRT * uRTdr);
        upLeftTurn.set(uLT * uLTdr);
        downLeftTurn.set(dLT * dLTdr);
        downRightTurn.set(dRT * dRTdr);
        
        //DEBUGGING (yay)
        double uRTC = uRT * uRTdr;
        double uLTC = uLT * uLTdr;
        double dLTC = dLT * dLTdr;
        double dRTC = dRT * dRTdr;
        
        SmartDashboard.putNumber("uRTC", (double) uRTC);
        SmartDashboard.putNumber("uLTC", (double) uLTC);
        SmartDashboard.putNumber("dLTC", (double) dLTC);
        SmartDashboard.putNumber("dRTC", (double) dRTC);
        
    	//}
        /* Jon:
        *
        * //retrieve button values 2 and 3 on the stick controller
        * boolean up = stick.get3();
        * boolean down = stick.get2();
        // end */  
        
        /*
         * if (rcw != 0 && fwd != 0) {
            if (rcw < -0.1) {
                upRightDrive.set(wheelSpeedOne * 1.5);
                downRightDrive.set(wheelSpeedFour * 1.5);
                upLeftDrive.set(wheelSpeedTwo / -1.5);

            } else if (rcw > 0.1) {
                upLeftDrive.set(wheelSpeedTwo * 1.5);
                downLeftDrive.set(wheelSpeedThree * 1.5);
                upRightDrive.set(wheelSpeedOne / -1.5);
        
        /*if (Math.abs(upRightEncoder - wheelAngleOne) <= 180) {
        uRTDir = -Math.signum(upRightEncoder - wheelAngleOne);
    } else {
        uRTDir = Math.signum(upRightEncoder - wheelAngleOne);
    }
    
    if (Math.abs(upLeftEncoder - wheelAngleTwo) <= 180) {
        uLTDir = -Math.signum(upLeftEncoder - wheelAngleTwo);
    } else {
        uLTDir = Math.signum(upLeftEncoder - wheelAngleTwo);
    }
    
    if (Math.abs(downRightEncoder - wheelAngleFour) <= 180) {
        dRTDir = -Math.signum(downRightEncoder - wheelAngleFour);
    } else {
        dRTDir = Math.signum(downRightEncoder - wheelAngleFour);
    }
    
    if (Math.abs(downLeftEncoder - wheelAngleThree) <= 180) {
        dLTDir = -Math.signum(downLeftEncoder - wheelAngleThree);
    } else {
        dLTDir = Math.signum(downLeftEncoder - wheelAngleThree);
    }
    
    if (uRTDir == 1) {
        uRT = (wheelAngleOne - upRightEncoder > 45) ? 1 : (wheelAngleOne - upRightEncoder) / 45;
    } else if (uRTDir == -1) {
        uRT = (wheelAngleOne - upRightEncoder > 45) ? -1 : -(wheelAngleOne - upRightEncoder) / 45;
    } else {
        uRT = 0;
    }
    
    if (uLTDir == 1) {
        uLT = (wheelAngleTwo - upLeftEncoder > 45) ? 1 : (wheelAngleTwo - upLeftEncoder) / 45;
    } else if (uLTDir == -1) {
        uLT = (wheelAngleTwo - upLeftEncoder > 45) ? -1 : -(wheelAngleTwo - upLeftEncoder) / 45;
    } else {
        uLT = 0;
    }
    
    if (dRTDir == 1) {
        dRT = (wheelAngleFour - downRightEncoder > 45) ? 1 : (wheelAngleFour - downRightEncoder) / 45;
    } else if (dRTDir == -1) {
        dRT = (wheelAngleFour - downRightEncoder > 45) ? -1 : -(wheelAngleFour - downRightEncoder) / 45;
    } else {
        dRT = 0;
    }
    
    if (dLTDir == 1) {
        dLT = (wheelAngleThree - downLeftEncoder > 45) ? 1 : (wheelAngleThree - downLeftEncoder) / 45;
    } else if (dLTDir == -1) {
        dLT = (wheelAngleThree - downLeftEncoder > 45) ? -1 : -(wheelAngleThree - downLeftEncoder) / 45;
    } else {
        dLT = 0;
    }*/
    
    /* Jon:
     * start
     *
     * if (up == true) {
     * 		elevator = 1; // 1 is considered to be the value for "up".
     * 		}
     * else if (down == true) {
     * 		elevator = -1; // =1 is considered to be the value for "down".
     * 		}
     * // end */
        
        //double uLT = (Math.abs(wheelAngleTwo - upLeftEncoder) > 45) ? 1 : (Math.abs(wheelAngleTwo - upLeftEncoder)) / 45;
        //double uRT = (wheelAngleOne - upRightEncoder > 45) ? -1 : -(wheelAngleOne - upRightEncoder) / 45;
        //double uLT = (Math.abs(wheelAngleTwo - upLeftEncoder) > 45) ? 1 : (Math.abs(wheelAngleTwo - upLeftEncoder)) / 45;
        //double uRT = (wheelAngleOne - upRightEncoder > 45) ? -1 : -(wheelAngleOne - upRightEncoder) / 45;
        //double uLT = (wheelAngleTwo - upLeftEncoder > 45) ? -1 : -(wheelAngleTwo - upLeftEncoder) / 45;
        //double dLT = (wheelAngleThree - downLeftEncoder > 45) ? -1 : -(wheelAngleThree - downLeftEncoder) / 45;
        //double dRT = (wheelAngleFour - downRightEncoder > 45) ? -1 : -(wheelAngleFour - downRightEncoder) / 45;
        
        
   //double uREm = (upRightEncoder < 0) ? -1 : 1;
   //double uLEm = (upRightEncoder < 0) ? -1 : 1;
   //double dLEm = (upRightEncoder < 0) ? -1 : 1;
   //double dREm = (upRightEncoder < 0) ? -1 : 1;
        
        //Serena-Turn Angle (Doesnt Work)
        //double uRTtest =((wheelAngleOne - upRightEncoder) > (upRightEncoder - wheelAngleOne)) ? ((wheelAngleOne - upRightEncoder) > 45) ? -1 : -(Math.abs(wheelAngleOne - upRightEncoder) / 45)) : (Math.abs(upRightEncoder - wheelAngleOne) > 45) ? 1 : (Math.abs(upRightEncoder - wheelAngleOne) / 45);

        

        commandStatus = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return commandStatus;
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0);
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.upLeftTurn.set(0);
    	RobotMap.upRightTurn.set(0);
    	RobotMap.downLeftTurn.set(0);
    	RobotMap.downRightTurn.set(0);
    	*/
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
