package frc.team3067.robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team3067.robot.Commands.*;
import frc.team3067.robot.Robot;

class RobotAuto extends CommandGroup {
    void Pos1Auto() { //If we are staring in position 1 (far left)
        Robot.drive.encoderSetup();                                       //Sets up the drive.encoders and resets the drive.encoder values.
        if (Robot.switchScale.charAt(0) == 'R') {                   // If switch is on right
            addSequential(new Drivetrain_MeasuredForward(154));    // Move forward across base line (154 inches)
        }
        else if (Robot.switchScale.charAt(0) == 'L') {              // If switch is on left:
            addParallel(new Lift_ToTop());                           // Start raising the lift and move to whatever's next
            addSequential(new Drivetrain_MeasuredForward(154));    // Move forward until in line with switch (154 inches
            addSequential(new Drivetrain_GyroRotateRight(90));     // Rotate 90 degrees right
            addSequential(new Drivetrain_MeasuredForward(35));     // Drive 35 inches until up in front of switch
            addSequential(new Grabber_DropCube(1));                 // Drop the cube
            }
        }
    void Pos2Auto(){ //If we are staring in position 2 (middle)
        int rotateValue = 50;
        Robot.drive.encoderSetup();
        addParallel(new Lift_ToTop());
        if(Robot.switchScale.charAt(0) == 'L') {                    // If switch is on left
            // Go to left side
            addSequential(new Drivetrain_MeasuredForward(12));     // Leave the wall
            addSequential(new Drivetrain_GyroRotateLeft(rotateValue));     // Rotate Left 90
            addSequential(new Drivetrain_MeasuredForward(60));     // Move until even with switch (60 inches)
            addSequential(new Drivetrain_GyroRotateRight(rotateValue));    // Rotate Right 90
            addSequential(new Drivetrain_MeasuredForward(80));     // Move until at switch
            addSequential(new Grabber_DropCube(1));
        }
        else if (Robot.switchScale.charAt(0) == 'R') {              //If switch is on right
            // Go to right side
            addSequential(new Drivetrain_MeasuredForward(12));     // Leave the wall
            addSequential(new Drivetrain_GyroRotateRight(rotateValue));    // Rotate Right 90
            addSequential(new Drivetrain_MeasuredForward(60));     // Move until even with switch (60 inches)
            addSequential(new Drivetrain_GyroRotateLeft(rotateValue));     // Rotate Left 90
            addSequential(new Drivetrain_MeasuredForward(80));     // Move until at switch
            addSequential(new Grabber_DropCube(1));
        }
    }
    void Pos3Auto(){ //If we are staring in position 3 (far right):
        Robot.drive.encoderSetup();
        if (Robot.switchScale.charAt(0) == 'R') {                   // If switch is on right:
            addSequential(new Drivetrain_MeasuredForward(154));    // Move forward across base line (154 inches)
        }
        else if (Robot.switchScale.charAt(0) == 'L') {              // If switch is on left
            addParallel(new Lift_ToTop());                           // Brings the lift to the top while it starts the next command
            addSequential(new Drivetrain_MeasuredForward(154));    // Move forward until in line with switch (154 inches)
            addSequential(new Drivetrain_GyroRotateLeft(90));      // Rotate left 90 degrees
            addSequential(new Drivetrain_MeasuredForward(35));     // Drive forward until directly next to edge of switch (35 inches)
            addSequential(new Grabber_DropCube(1));                 // Drop the cube
            }
        }
    void BasicAuto(){
        addSequential(new Drivetrain_MeasuredForward(174));
    }
    void testAuto(){
        addSequential(new Autopath("C:\\Users\\2102062\\Documents\\Robot Mk II\\src\\main\\java\\frc\\team3067\\robot\\motionprofile\\redTestPath\\redTestPath"));
    }
}
