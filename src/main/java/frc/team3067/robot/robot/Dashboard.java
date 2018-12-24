package frc.team3067.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
    public static void SmartDashUpdate() {
        SmartDashboard.putNumber("talLF", Robot.drive.talLF.get());
        SmartDashboard.putNumber("talLB", Robot.drive.talLB.get());
        SmartDashboard.putNumber("talRF", Robot.drive.talRF.get());
        SmartDashboard.putNumber("talRB", Robot.drive.talRB.get());
        SmartDashboard.putNumber("gyroAngle", Robot.drive.gyro.getAngle());
        SmartDashboard.putNumber("TeleSpeed", Robot.drive.TeleSpeed);
        SmartDashboard.putNumber("TurnSpeed", Robot.drive.TurnSpeed);
        SmartDashboard.putNumber("smoothing", Robot.drive.driveSmoothing);
        SmartDashboard.putBoolean("smoothSteering", Robot.drive.smoothSteering);
        SmartDashboard.updateValues();
    }
}
