package org.usfirst.frc.team2345.robot.subsystems;

import org.usfirst.frc.team2345.robot.commands.swerveDrive;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class driveSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new swerveDrive());
    }
}

