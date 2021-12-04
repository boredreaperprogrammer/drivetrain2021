package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {

    public static NetworkTable limelightTable;

    public static double limelight_x;
    public static double limelight_y;

    public static double FORCE_OFF = 1;
    public static double FORCE_BLINK = 2;
    public static double FORCE_ON = 3;

    public static double VISION_PROCESSOR = 0;
    public static double DRIVER_CAMERA = 1;

    public static <NetworkTableEntry> void init_Limelight() {

        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = (NetworkTableEntry)limelightTable.getEntry("tx");
        NetworkTableEntry ty = (NetworkTableEntry) limelightTable.getEntry("ty");

        limelight_x = (Double) ((edu.wpi.first.networktables.NetworkTableEntry) tx).getDouble(0.0);
        limelight_y = (Double) ((edu.wpi.first.networktables.NetworkTableEntry) ty).getDouble(0.0);

        limelightTable.getEntry("ledMode").setNumber(FORCE_ON);
        limelightTable.getEntry("camMode").setNumber(VISION_PROCESSOR);
    }

    public static void getLimelightData() {
        limelight_x = (Double) limelightTable.getEntry("tx").getDouble(0.0);
        limelight_y = (Double) limelightTable.getEntry("ty").getDouble(0.0);
    }
    public static void turn_LED_ON(){

        limelightTable.getEntry("ledMode").setNumber(FORCE_ON);


    }

    public static void turn_LED_OFF() {
     
        limelightTable.getEntry("ledMode").setNumber(FORCE_OFF);
    }

    public static void turn_LED_FLASH_BLINK() {

        limelightTable.getEntry("ledMode").setNumber(FORCE_BLINK);

    }

    public static double getlimelightX() {

        return (Double) limelightTable.getEntry("tx").getDouble(0.0);

    }

    public static double getlimelightY() {

        return (Double) limelightTable.getEntry("ty").getDouble(0.0);

    }

    public static void setDriverCamera() {
        limelightTable.getEntry("camMode").setNumber(DRIVER_CAMERA);
        turn_LED_OFF();
    }

    public static void setVisionProcessor() {
        limelightTable.getEntry("camMode").setNumber(VISION_PROCESSOR);
        turn_LED_ON();
    }

    static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

 

}


    
    

