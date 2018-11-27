package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import static frc.team3067.robot.Robot.dozer;


public class MeasuredRotateRight extends Command {
    private double getDegrees;
    public MeasuredRotateRight(double degrees) {
        requires(dozer.drivetrain);
        getDegrees = degrees;
    }
    @Override
    protected void initialize() {
        dozer.drivetrain.driveRotateRight();
    }
    @Override
    protected void execute() { }

    @Override
    protected boolean isFinished() {
        //ends if the degrees rotated is greater than the target distance
        return !(dozer.getGyroAngle() < getDegrees);
    }
    @Override
    protected void end() {
        dozer.drivetrain.driveStop();
    }
    @Override
    protected void interrupted() {
        end();
        super.interrupted();
    }
}
