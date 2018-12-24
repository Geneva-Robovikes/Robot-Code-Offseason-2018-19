package frc.team3067.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3067.util.CSVReader;
import frc.team3067.robot.Robot;


public class Autopath extends Command {
    private String[][] leftArray;
    private String[][] rightArray;
    private int tick = 0;
    public Autopath(String CSVfile) {
        // THIS COMMAND USES THE REGULAR CSV FILES, NOT THE DETAILED ONES!!!
        requires(Robot.drive);
        String CSVfilebase = CSVfile.endsWith("_left.csv")?(CSVfile.substring(0,CSVfile.length()-9)):(CSVfile.endsWith("_right.csv")?CSVfile.substring(0,CSVfile.length()-10):CSVfile);
        leftArray = CSVReader.CSVRead(CSVfilebase+"_left.csv");
        rightArray = CSVReader.CSVRead(CSVfilebase+"_right.csv");
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.drive.setDriveMotorSpeed(0,0,0,0);
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        Robot.drive.setDriveMotorSpeed(
                Integer.parseInt(leftArray[tick][2]),
                Integer.parseInt(leftArray[tick][2]),
                Integer.parseInt(rightArray[tick][2]),
                Integer.parseInt(rightArray[tick][2]));
        tick++;
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
        try{
            return (leftArray[tick][2] == null||rightArray[tick][2] == null);
        }
        catch(ArrayIndexOutOfBoundsException e){
            return true;
        }
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.drive.setDriveMotorSpeed(0,0,0,0);
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
        end();
        super.interrupted();
    }
}
