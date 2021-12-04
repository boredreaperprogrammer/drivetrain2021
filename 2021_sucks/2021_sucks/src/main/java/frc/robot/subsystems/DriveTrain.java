package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;

public class DriveTrain {

  /*public static final WPI_TalonSRX leftMotor = RobotMap.leftBackDriveMotor;
  public static final WPI_TalonSRX rightMotor = RobotMap.rightBackDriveMotor;
  public static final WPI_TalonSRX leftMotor = RobotMap.leftBackDriveMotor;
  public static final WPI_TalonSRX rightMotor = RobotMap.rightBackDriveMotor; */

  static WPI_TalonFX leftMotor = RobotMap.leftMotor;
  static WPI_TalonFX rightMotor = RobotMap.rightMotor;
  WPI_TalonSRX leftMotorFront = RobotMap.leftFrontDriveMotor;
  WPI_TalonSRX leftMotorBack = RobotMap.leftBackDriveMotor;
  WPI_TalonSRX rightMotorFront = RobotMap.leftFrontDriveMotor;
  WPI_TalonSRX rightMotorBack = RobotMap.leftBackDriveMotor;

  private static final double IN_TO_M = .0254;

  private static final int MOTOR_ENCODER_CODES_PER_REV = 2048;
  private static final double DIAMETER_INCHES = 5.0;

  private static final double WHEEL_DIAMETER = DIAMETER_INCHES * IN_TO_M;
  private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

  private static final double GEAR_RATIO = 12.75;
  private static final double TICKS_PER_METER = (MOTOR_ENCODER_CODES_PER_REV * GEAR_RATIO) / (WHEEL_CIRCUMFERENCE);
  private static final double METERS_PER_TICK = 1 / TICKS_PER_METER;
  private static final int TIMEOUT_MS = 0;

  public DriveTrain() {

    leftMotor.setNeutralMode(NeutralMode.Coast);
    rightMotor.setNeutralMode(NeutralMode.Coast);

    leftMotor.setInverted(false);
    rightMotor.setInverted(true);

    resetEnocoders();

    leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    leftMotor.configVelocityMeasurementWindow(10);
    leftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms);
    rightMotor.configVelocityMeasurementWindow(10);
    rightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 5, 10);

    leftMotorFront.set(ControlMode.Follower, leftMotorBack.getDeviceID());

    leftMotorFront.configNominalOutputForward(0, TIMEOUT_MS);
    leftMotorFront.configNominalOutputReverse(0, TIMEOUT_MS);
    leftMotorFront.configPeakOutputForward(1, TIMEOUT_MS);
    leftMotorFront.configPeakOutputReverse(-1, TIMEOUT_MS);

    rightMotorFront.configNominalOutputForward(0, TIMEOUT_MS);
    rightMotorFront.configNominalOutputReverse(0, TIMEOUT_MS);
    rightMotorFront.configPeakOutputForward(1, TIMEOUT_MS);
    rightMotorFront.configPeakOutputReverse(-1, TIMEOUT_MS);

    leftMotorFront.configNeutralDeadband(0.001,TIMEOUT_MS);
    leftMotorBack.configNeutralDeadband(0.001,TIMEOUT_MS);
    rightMotorFront.configNeutralDeadband(0.001,TIMEOUT_MS);
    rightMotorBack.configNeutralDeadband(0.001,TIMEOUT_MS);

    leftMotorFront.setSensorPhase(true);
    rightMotorFront.setSensorPhase(false);
    leftMotorBack.setSensorPhase(true);
    rightMotorBack.setSensorPhase(false);

    leftMotorFront.setInverted(false);
    rightMotorFront.setInverted(true);
    leftMotorBack.setInverted(false);
    rightMotorBack.setInverted(true);

  }

  public void resetEnocoders() {

    leftMotor.setSelectedSensorPosition(0);
    rightMotor.setSelectedSensorPosition(0);
  }

  public void setModePercentVoltage() {
    leftMotor.set(ControlMode.PercentOutput, 0);
    rightMotor.set(ControlMode.PercentOutput, 0);

    leftMotor.set(ControlMode.PercentOutput, 0);
    rightMotor.set(ControlMode.PercentOutput, 0);
  }

  public static void drive(double throttle, double rotate) {
    leftMotor.set(throttle + rotate);
    rightMotor.set(throttle - rotate);

    leftMotor.set(throttle + rotate);
    rightMotor.set(throttle - rotate);
  }

  public void stop() {
    drive(0, 0);
  }

  public double getLeftEncoderVelocityMetersPerSecond() {
    double leftVelocityMPS = (leftMotor.getSelectedSensorVelocity() * 10);
    leftVelocityMPS = leftVelocityMPS * METERS_PER_TICK;
    return (leftVelocityMPS);
  }

  public double getRightEncoderVelocityMetersPerSecond() {
    double rightVelocityMPS = (rightMotor.getSelectedSensorVelocity() * 10);
        rightVelocityMPS = rightVelocityMPS * METERS_PER_TICK;
        return (rightVelocityMPS);
      }

      public double leftDistanceTravelledInMeters() {
          double left_dist = getleftEncoderPosition() * METERS_PER_TICK;
          return left_dist;
      }

      public double rightDistanceTravelledInMeters() {
        double right_dist = getrightEncoderPosition() * METERS_PER_TICK;
        return right_dist;
    }


      private double getrightEncoderPosition() {
        return 0;
    }

    private double getleftEncoderPosition() {
          return 0;
      }

      public double distanceTravelledinMeters() {
        
        double distanceTravelled = (leftDistanceTravelledInMeters() + rightDistanceTravelledInMeters() ) /2;
        return distanceTravelled;
      }

	public void setDefaultCommand() {
	}
    
    



}
