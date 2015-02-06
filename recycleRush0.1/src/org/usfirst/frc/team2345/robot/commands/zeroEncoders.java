package org.usfirst.frc.team2345.robot.commands;

import org.usfirst.frc.team2345.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class zeroEncoders extends Command {
	public boolean isComplete;
	boolean uLPressed;
	boolean uRPressed;
	boolean dLPressed;
	boolean dRPressed;
    public zeroEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.upRightTurn.set(1);
		RobotMap.upLeftTurn.set(1);
		RobotMap.downLeftTurn.set(1);
		RobotMap.downRightTurn.set(1);
		uLPressed = false;
		uRPressed = false;
		dLPressed = false;
		dRPressed = false;
		isComplete = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
			while (isComplete != true) {
				if (RobotMap.uLSwitch.get() == true) {
					uLPressed = true;
					RobotMap.upLeftTurn.set(0);
					RobotMap.upLeftEnc.reset();
				}
				if (RobotMap.uRSwitch.get() == true) {
					uRPressed = true;
					RobotMap.upRightTurn.set(0);
					RobotMap.upRightEnc.reset();
				}
				if (RobotMap.dLSwitch.get() == true) {
					dLPressed = true;
					RobotMap.downLeftTurn.set(0);
					RobotMap.downLeftEnc.reset();
				}
				if (RobotMap.dRSwitch.get() == true) {
					dRPressed = true;
					RobotMap.downRightTurn.set(0);
					RobotMap.downRightEnc.reset();
				}
				if (uLPressed == true && uRPressed == true && dLPressed == true && dRPressed == true) {
					isComplete = true;
					/*RobotMap.upLeftTurn.set(0);
					RobotMap.upRightTurn.set(0);
					RobotMap.downLeftTurn.set(0);
					RobotMap.downRightTurn.set(0);*/
				}
			}
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean finished = false;
    	if (isComplete == true)
        	finished = true;
    	return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
