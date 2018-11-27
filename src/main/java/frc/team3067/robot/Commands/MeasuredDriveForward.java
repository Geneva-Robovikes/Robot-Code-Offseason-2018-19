package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import static frc.team3067.robot.Robot.dozer;


public class MeasuredDriveForward extends Command {
    private double getInches;
    public MeasuredDriveForward(double inches) {
        requires(dozer.drivetrain);
        getInches = inches;
    }
    @Override
    protected void initialize() {
        dozer.encoderReset();
        dozer.drivetrain.driveForward();
    }
    @Override
    protected void execute() { }

    @Override
    protected boolean isFinished() {
        //ends if the distance traveled is greater than the target distance
        return (-dozer.LeftEnc.getDistance() > getInches && dozer.RightEnc.getDistance() > getInches);
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
