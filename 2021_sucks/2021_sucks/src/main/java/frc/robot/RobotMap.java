package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;


public class RobotMap {
    
    public static final int LEFT_MOTOR = 1;
    public static final int RIGHT_MOTOR = 2;

    public static WPI_TalonFX leftMotor = new WPI_TalonFX(LEFT_MOTOR);
    public static WPI_TalonFX rightMotor = new WPI_TalonFX(RIGHT_MOTOR);

    public static WPI_TalonSRX leftBackDriveMotor = new WPI_TalonSRX(LEFT_MOTOR);
    public static WPI_TalonSRX rightBackDriveMotor = new WPI_TalonSRX(RIGHT_MOTOR);
    public static WPI_TalonSRX leftFrontDriveMotor = new WPI_TalonSRX(LEFT_MOTOR);
    public static WPI_TalonSRX rightFrontDriveMotor = new WPI_TalonSRX(RIGHT_MOTOR);
}
