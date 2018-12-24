package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import frc.team3067.robot.Robot;


public class Drivetrain_SetMotor extends Command {
    public Drivetrain_SetMotor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
            Robot.drive.talLF.set(Robot.drive.TeleSpeed * -Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ());
            Robot.drive.talLB.set(Robot.drive.TeleSpeed * -Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ());
            Robot.drive.talRF.set(Robot.drive.TeleSpeed * Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ());
            Robot.drive.talRB.set(Robot.drive.TeleSpeed * Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ());
    }

    public void setMotorSmooth() { // Arcade drive
        Robot.drive.talLF.set(scaleMotor(Robot.drive.TeleSpeed * -Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ(), Robot.drive.talLF));
        Robot.drive.talLB.set(scaleMotor(Robot.drive.TeleSpeed * -Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ(), Robot.drive.talLB));
        Robot.drive.talRF.set(scaleMotor(Robot.drive.TeleSpeed * Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ(), Robot.drive.talRF));
        Robot.drive.talRB.set(scaleMotor(Robot.drive.TeleSpeed * Robot.stick.getDY() + Robot.drive.TurnSpeed * Robot.stick.getDZ(), Robot.drive.talRB));
    }
    public double scaleMotor(double stickInput, Talon talon) {
        double newMotorValue = talon.get() * (1 - Robot.drive.driveSmoothing) + stickInput * Robot.drive.driveSmoothing;
        if (newMotorValue < .02 && newMotorValue > -.02)
            return 0;
        else
            return newMotorValue;
    }
    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {

    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
