package org.usfirst.frc.team2345.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2345.robot.commands.ExampleCommand;
import org.usfirst.frc.team2345.robot.commands.autonomousIndependent;
import org.usfirst.frc.team2345.robot.commands.swerveDrive;
import org.usfirst.frc.team2345.robot.commands.teleopFunctions;
import org.usfirst.frc.team2345.robot.commands.zeroEncoders;
import org.usfirst.frc.team2345.robot.commands.cameraCommand;
import org.usfirst.frc.team2345.robot.subsystems.ExampleSubsystem;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot { 

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Subsystem driveSystem = new org.usfirst.frc.team2345.robot.subsystems.driveSystem();
	public static OI oi;

    Command autonomousCommand; 
    Command driveCommand;
    Command testCommand;
    Command teleopFunctions;
    Command cameraCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
        // instantiate the command used for the autonomous period
        autonomousCommand = new autonomousIndependent();
        //
        driveCommand = new swerveDrive();
        teleopFunctions = new teleopFunctions();
        cameraCommand = new cameraCommand();
        RobotMap.usbCamera.setQuality(37);
        RobotMap.usbCamera.startAutomaticCapture("cam1");

        
       // CameraServer camera = CameraServer.getInstance();
       // camera.setQuality(50);
       // camera.startAutomaticCapture("cam1");
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	driveCommand.cancel();
    	teleopFunctions.cancel();
    	/*if (autonomousCommand != null)*/ 
    	autonomousCommand.start();
    	cameraCommand.cancel();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putData(Scheduler.getInstance());
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        /*if (autonomousCommand != null)*/ autonomousCommand.cancel();
        /*if (driveCommand != null)*/ driveCommand.start();
        /*if (teleopFunctions != null)*/ teleopFunctions.start();
        cameraCommand.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putData(Scheduler.getInstance());
        //OI.zeroEncoder.whenPressed(new zeroEncoders());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
