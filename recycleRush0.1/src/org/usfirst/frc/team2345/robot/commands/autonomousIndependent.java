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
    	//Timer.delay(10);
    	
    	/*
    	//move forward slightly and pick up the yellow tote
    	
    	RobotMap.upLeftDrive.set(-.05);
    	RobotMap.upRightDrive.set(-.05);
    	RobotMap.downLeftDrive.set(-.05);
    	RobotMap.downRightDrive.set(-.05);
    	RobotMap.elevator1.set(.7);
    	Timer.delay(1);
    	RobotMap.elevator1.set(0);
    	
    	//code from teleop to turn the wheels
    	isComplete = false;
    	double mult = 1.15278;
    	//while (!isComplete) {
    		double upRightEncoder = (upRightEnc.get() / mult);
	     	double upLeftEncoder = (upLeftEnc.get() / mult);
	     	double downRightEncoder = (downRightEnc.get() / mult);
	     	double downLeftEncoder = (downLeftEnc.get() / mult);
	     
	     	double uRE = (upRightEncoder < 0) ? 360 - Math.abs(upRightEncoder % 360) : upRightEncoder % 360;
	     	double uLE = (upLeftEncoder < 0) ? 360 - Math.abs(upLeftEncoder % 360) : upLeftEncoder % 360; //upLeftEncoder%= 360;
	     	double dLE = (downLeftEncoder < 0) ? 360 - Math.abs(downLeftEncoder % 360) : downLeftEncoder % 360; //downLeftEncoder %= 360;
	     	double dRE = (downRightEncoder < 0) ? 360 - Math.abs(downRightEncoder % 360) : downRightEncoder % 360; //downRightEncoder %= 360;
	     	
	        double wA1 = 360 - Math.abs(-90);
	        double wA2 = 360 - Math.abs(-90);
	        double wA3 = 360 - Math.abs(-90);
	        double wA4 = 360 - Math.abs(-90);
	        
	     	//double uRT = (Math.abs(uRE - wA1) > 60) ? 1 : (Math.abs(uRE - wA1)) / 60;
	        //double uLT = (Math.abs(uLE - wA2) > 60) ? 1 : (Math.abs(uLE - wA2)) / 60;
	        //double dLT = (Math.abs(dLE - wA3) > 60) ? 1 : (Math.abs(dLE - wA3)) / 60;
	        //double dRT = (Math.abs(dRE - wA4) > 60) ? 1 : (Math.abs(dRE - wA4)) / 60;
	    	*/
	       /* RobotMap.upLeftTurn.set(0.3);
	        RobotMap.upRightTurn.set(0.3);
	        RobotMap.downLeftTurn.set(0.3);
	        RobotMap.downRightTurn.set(0.3);
	        Timer.delay(0.2);
	        RobotMap.upLeftTurn.set(0);
	        RobotMap.upRightTurn.set(0);
	        RobotMap.downLeftTurn.set(0);
	        RobotMap.downRightTurn.set(0);
	        */
	        
	        
	    	/*if (uLE > 270){
	    		RobotMap.upLeftTurn.set(uLT);
	    	} else {
	    		RobotMap.upLeftTurn.set(0);
	    	}
	    	
	    	if (uRE > 270){
	    		RobotMap.upRightTurn.set(uRT);
	    	} else {
	    		RobotMap.upRightTurn.set(0);
	    	}    	
	    	
	    	if (dLE > 270){
	    		RobotMap.downLeftTurn.set(dLT);
	    	}else{
	    		RobotMap.downLeftTurn.set(0);
	    	}
	    	
	    	if (dRE > 270){
	    		RobotMap.downRightTurn.set(dRT);
	    	}else{
	    		RobotMap.downRightTurn.set(0);
	    	}*/
	    	//JAVA IS AWESOME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	    	//if (260 < uLE && uLE < 270 && 260 < uRE && uRE < 270 && dLE < 260 && dLE < 270 && dRE < 260 && dRE < 270) { isComplete = true; }//!
	    	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	//}
	    	/*
	    RobotMap.upLeftDrive.set(0.3);
	    RobotMap.downLeftDrive.set(0.3);
	    RobotMap.upRightDrive.set(-0.3);
	    RobotMap.downRightDrive.set(-0.3);
	    	
	    	
    	Timer.delay(1);
    	
    	//drive forward, because the wheels are turned, this is strafe
    	RobotMap.upLeftDrive.set(-.25);
    	RobotMap.upRightDrive.set(-.25);
    	RobotMap.downLeftDrive.set(-.25);
    	RobotMap.downRightDrive.set(-.25);    	
    	Timer.delay(8);
    	
    	//extra time
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0);    	
    	Timer.delay(4);
    	
    	
    	////END REAL CODE
    	*/
    	
    }

    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
    	
    	/*Talon upRightDrive = RobotMap.upRightDrive;
    	Talon upLeftDrive = RobotMap.upLeftDrive;
    	Talon downLeftDrive = RobotMap.downLeftDrive;
    	Talon downRightDrive = RobotMap.downRightDrive;
    	
    	Victor upLeftTurn = RobotMap.upLeftTurn;
        Victor upRightTurn = RobotMap.upRightTurn;
        Victor downLeftTurn = RobotMap.downLeftTurn;
        Victor downRightTurn = RobotMap.downRightTurn;
        
        Encoder upRightEnc = RobotMap.upRightEnc;
        Encoder upLeftEnc = RobotMap.upLeftEnc;
        Encoder downRightEnc = RobotMap.downRightEnc;
        Encoder downLeftEnc = RobotMap.downLeftEnc;
        
        Victor elevator1 = RobotMap.elevator1;
        
        DigitalInput elevatorSwitch1 = RobotMap.elevatorSwitch1;
        
        //Pick up Trashcan
        double elev  = (elevatorSwitch1.get() == true) ? 0 : .70;
        elevator1.set(elev);
        Timer.delay(.5);
        // /
        //Drive Forward
        upRightDrive.set(.7);
        upLeftDrive.set(.7);
        downLeftDrive.set(.7);
        downRightDrive.set(.7);
        Timer.delay(1);
        /*
        //Pick up Tote
        double elev2  = (elevatorSwitch1.get() == true) ? 0 : .70;
        elevator1.set(elev2);
        Timer.delay(.5);
        
        //Turn wheels Left?
        double uRT = (270 < upRightEnc.get() || 90 > upRightEnc.get()) ? 0 : -1;
        upRightTurn.set(uRT);
        
        double uLT = (270 < upLeftEnc.get() || 90 > upLeftEnc.get()) ? 0 : -1;
        upLeftTurn.set(uLT);
    	
        double dLT = (270 < downLeftEnc.get() || 90 > downLeftEnc.get()) ? 0 : -1;
        downLeftTurn.set(dLT);
        
        double dRT = (270 < downRightEnc.get() || 90 > downRightEnc.get()) ? 0 : -1;
        downRightTurn.set(dRT);
        Timer.delay(.5);
        
        //Strafew Left?
        upRightDrive.set(.7);
        upLeftDrive.set(.7);
        downLeftDrive.set(.7);
        downRightDrive.set(.7);
        Timer.delay(5);
        
        //Lower Elevator
        double elev3  = -.70;
        elevator1.set(elev3);
        Timer.delay(2);
        
    	*/
    	
    	
    	/*
    	//Lift elevator
    	RobotMap.elevator1.set(1);
    	Timer.delay(1);
    	RobotMap.elevator1.set(0);
    	
    	
    	//Move Forward
    	RobotMap.upLeftDrive.set(-.75);
    	RobotMap.upRightDrive.set(-.75);
    	RobotMap.downLeftDrive.set(-.75);
    	RobotMap.downRightDrive.set(-.75);    	
    	Timer.delay(0.5);
    	
    	//Stop
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0); 
    	Timer.delay(.5);
    	
    	//Run Elevator
    	RobotMap.elevator1.set(1);
    	//RobotMap.elevator2.set(1);
    	Timer.delay(1);
    	RobotMap.elevator1.set(0);
    	//Move Forward
    	RobotMap.upLeftDrive.set(-.75);
    	RobotMap.upRightDrive.set(-.75);
    	RobotMap.downLeftDrive.set(-.75);
    	RobotMap.downRightDrive.set(-.75);    	
    	Timer.delay(0.5);
    	
    	//Stop
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0); 
    	Timer.delay(1);
    	
    	//Run Elevator
    	RobotMap.elevator1.set(1);
    	//RobotMap.elevator2.set(1);
    	Timer.delay(1);    	
    	RobotMap.elevator1.set(0);
    	//Turn Left
    	//??until [ RobotMap.upLeftEncoder.get() = 90 ];
    	RobotMap.upLeftTurn.set(.75);
    	RobotMap.upRightTurn.set(.75);
    	RobotMap.downLeftTurn.set(.75);
    	RobotMap.downRightTurn.set(.75);
    	Timer.delay(1.5);
    	
    	//Move Left
    	RobotMap.upLeftDrive.set(.75);
    	RobotMap.upRightDrive.set(.75);
    	RobotMap.downLeftDrive.set(.75);
    	RobotMap.downRightDrive.set(.75);    	
    	Timer.delay(2);
    	
    	//Stop
    	RobotMap.upLeftDrive.set(0);
    	RobotMap.upRightDrive.set(0);
    	RobotMap.downLeftDrive.set(0);
    	RobotMap.downRightDrive.set(0); 
    	Timer.delay(1);
    	*/
    	
    	
    	/*
    	 //Start autonomous code
    	 
    	drive(0,0.5,0);
    	//Times are temporary?
    	Timer.delay(0.5);
    	drive(0,0,0);
    	//Lift trash can ... may have to change loopControl value
    	int loopControl1 = 0;
		while (loopControl1 < 2) {	
			RobotMap.elevator1.set(0.5);
			RobotMap.elevator2.set(0.5);
		if (RobotMap.elevatorSwtich1.get() == true)
			loopControl1 += 1;
		}
		RobotMap.elevator1.set(0);
		RobotMap.elevator2.set(0);
    	drive(0,0.5,0);
    	Timer.delay(0.5);
    	drive(0,0,0);
    	//6 Februrary 2015, swerveDrive.java, Kearney Robotics 2345, 6 Februrary 2015.
    	//Go home MLA you drunk
    	boolean loopControl2 = false;
		while (loopControl2 != true) {	
			RobotMap.elevator1.set(0.5);
			RobotMap.elevator2.set(0.5);
		if (RobotMap.elevatorSwtich1.get() == true)
			loopControl2 = true;
		}
		RobotMap.elevator1.set(0);
		RobotMap.elevator2.set(0);
    	drive(-0.5,0,0);
    	//May have to change timers
    	Timer.delay(5);
    	drive(0,0,0);
    	int loopControl3 = 0;
    	//May have to change loopControl3
		while (loopControl3 < 4) {	
			RobotMap.elevator1.set(-0.5);
			RobotMap.elevator2.set(-0.5);
		if (RobotMap.elevatorSwtich1.get() == true)
			loopControl3 += 1;
		}
		RobotMap.elevator1.set(0);
		RobotMap.elevator2.set(0);
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
   
    double uRT;
    double uLT;
    double dRT;
    double dLT;
    double uRTDir;
    double uLTDir;
    double dRTDir;
    double dLTDir;
    double mult = 1.15278;
    
    protected void drive(double str, double fwd, double rcw) {
    	 double upRightEncoder = RobotMap.upRightEnc.get() / mult;
     	double upLeftEncoder = RobotMap.upLeftEnc.get() / mult;
     	double downRightEncoder = RobotMap.downRightEnc.get() / mult;
     	double downLeftEncoder = RobotMap.downLeftEnc.get() / mult;
     	final int l = 17;//19; //(wheelbase, inches)
        final int w = 31;//32; //(trackwidth, inches)
        double r = Math.sqrt(l * l + w * w);
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
         
        
         
         // I added a 0.7 to slow down the robot, as it was discovered that at full power, the robot would tip over.
         RobotMap.upLeftDrive.set(wheelSpeedTwo * 0.7);
         RobotMap.upRightDrive.set(wheelSpeedOne * 0.7);
         RobotMap.downLeftDrive.set(wheelSpeedThree * 0.7);
         RobotMap.downRightDrive.set(wheelSpeedFour * 0.7);

         SmartDashboard.putNumber("uL", (double) RobotMap.upLeftEnc.get());
         SmartDashboard.putNumber("uR", (double) RobotMap.upRightEnc.get());
         SmartDashboard.putNumber("dL", (double) RobotMap.downLeftEnc.get());
         SmartDashboard.putNumber("dR", (double) RobotMap.downRightEnc.get());
         SmartDashboard.putNumber("wS1", (double) wheelSpeedOne);
         SmartDashboard.putNumber("wS2", (double) wheelSpeedTwo);
         SmartDashboard.putNumber("wS3", (double) wheelSpeedThree);
         SmartDashboard.putNumber("wS4", (double) wheelSpeedFour);
         SmartDashboard.putNumber("wA1", (double) wheelAngleOne);
         SmartDashboard.putNumber("wA2", (double) wheelAngleTwo);
         SmartDashboard.putNumber("wA3", (double) wheelAngleThree);
         SmartDashboard.putNumber("wA4", (double) wheelAngleFour);

         double uRT = (wheelAngleOne - upRightEncoder > 45) ? -1 : -(wheelAngleOne - upRightEncoder) / 45;
         double uLT = (wheelAngleTwo - upLeftEncoder > 45) ? -1 : -(wheelAngleTwo - upLeftEncoder) / 45;
         double dLT = (wheelAngleThree - downLeftEncoder > 45) ? -1 : -(wheelAngleThree - downLeftEncoder) / 45;
         double dRT = (wheelAngleFour - downRightEncoder > 45) ? -1 : -(wheelAngleFour - downRightEncoder) / 45;
 	 

         RobotMap.upRightTurn.set(uRT);
         RobotMap.upLeftTurn.set(uLT);
         RobotMap.downLeftTurn.set(dLT);
         RobotMap.downRightTurn.set(dRT);
	*/
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
