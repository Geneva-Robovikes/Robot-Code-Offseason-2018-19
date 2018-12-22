package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3067.robot.Robot;


public class Drivetrain_GyroRotateLeft extends Command {
    private double getDegrees;
    public Drivetrain_GyroRotateLeft(double degrees) {
        requires(Robot.drive);
        getDegrees = degrees;
    }
    @Override
    protected void initialize() {
        Robot.drive.driveRotateLeft();
    }
    @Override
    protected void execute() { }

    @Override
    protected boolean isFinished() {
        //ends if the degrees rotated is greater than the target distance
        return !(Robot.drive.gyro.getAngle() > getDegrees);
    }
    @Override
    protected void end() {
        Robot.drive.driveStop();
    }
    @Override
    protected void interrupted() {
        end();
        super.interrupted();
    }
}
