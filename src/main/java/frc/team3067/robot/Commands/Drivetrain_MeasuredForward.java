package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3067.robot.Robot;


public class Drivetrain_MeasuredForward extends Command {
    private double getInches;
    public Drivetrain_MeasuredForward(double inches) {
        requires(Robot.drive);
        getInches = inches;
    }
    @Override
    protected void initialize() {
        Robot.drive.encoderReset();
        Robot.drive.driveForward();
    }
    @Override
    protected void execute() { }

    @Override
    protected boolean isFinished() {
        //ends if the distance traveled is greater than the target distance
        return (-Robot.drive.LeftEnc.getDistance() > getInches && Robot.drive.RightEnc.getDistance() > getInches);
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
