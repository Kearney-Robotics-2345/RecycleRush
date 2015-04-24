 package org.usfirst.frc.team2345.robot;

import java.awt.Image;

import org.usfirst.frc.team2345.robot.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	// all turn motors initialize
    public static Victor upLeftTurn = new Victor(7);
    public static Victor upRightTurn = new Victor(3);			
    public static Victor downLeftTurn = new Victor(5);
    public static Victor downRightTurn = new Victor(1);
    
    //all drive motors initialize 
    public static Talon upLeftDrive = new Talon(6);
    public static Talon upRightDrive = new Talon(2);
    public static Talon downLeftDrive = new Talon(4);
    public static Talon downRightDrive = new Talon(0);
    
    public static Encoder upLeftEnc = new Encoder(6, 7);
    public static Encoder upRightEnc = new Encoder(2, 3);
    public static Encoder downLeftEnc = new Encoder(4, 5);
    public static Encoder downRightEnc = new Encoder(0, 1);
    
    public static Victor elevator1 = new Victor(8);
    
    //FlipArm   --May become Talon/Spike?????
    public static Relay flipArm = new Relay(2, Relay.Direction.kBoth);
    
    //public static Victor swiffer = new Victor(12);
    
    public static DigitalInput elevatorSwitch1 = new DigitalInput(11);
    public static DigitalInput elevatorSwitch2 = new DigitalInput(10);
    
    public static DigitalInput uLSwitch = new DigitalInput(13);
    public static DigitalInput uRSwitch = new DigitalInput(14);
    public static DigitalInput dLSwitch = new DigitalInput(15);
    public static DigitalInput dRSwitch = new DigitalInput(16);
    //initializes brush
    //public static Relay swiffer  = new Relay(1 , Relay.Direction.kBoth);
    
    public static CameraServer usbCamera = new CameraServer();
    public static CameraServer ipCamera = new CameraServer();
    
    }
