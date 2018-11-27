package frc.team3067.robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team3067.robot.Commands.*;

import static frc.team3067.robot.Robot.dozer;

class RobotAuto extends CommandGroup {
    void Pos1Auto() { //If we are staring in position 1 (far left)
        dozer.encoderSetup();                                       //Sets up the encoders and resets the encoder values.
        if (dozer.switchScale.charAt(0) == 'R') {                   // If switch is on right
            addSequential(new MeasuredDriveForward(154));    // Move forward across base line (154 inches)
        }
        else if (dozer.switchScale.charAt(0) == 'L') {              // If switch is on left:
            addParallel(new LiftToTop());                           // Start raising the lift and move to whatever's next
            addSequential(new MeasuredDriveForward(154));    // Move forward until in line with switch (154 inches
            addSequential(new MeasuredRotateRight(90));     // Rotate 90 degrees right
            addSequential(new MeasuredDriveForward(35));     // Drive 35 inches until up in front of switch
            addSequential(new DropCube(1));                 // Drop the cube
            }
        }
    void Pos2Auto(){ //If we are staring in position 2 (middle)
        int rotateValue = 50;
        dozer.encoderSetup();
        addParallel(new LiftToTop());
        if(dozer.switchScale.charAt(0) == 'L') {                    // If switch is on left
            // Go to left side
            addSequential(new MeasuredDriveForward(12));     // Leave the wall
            addSequential(new MeasuredRotateLeft(rotateValue));     // Rotate Left 90
            addSequential(new MeasuredDriveForward(60));     // Move until even with switch (60 inches)
            addSequential(new MeasuredRotateRight(rotateValue));    // Rotate Right 90
            addSequential(new MeasuredDriveForward(80));     // Move until at switch
            addSequential(new DropCube(1));
        }
        else if (dozer.switchScale.charAt(0) == 'R') {              //If switch is on right
            // Go to right side
            addSequential(new MeasuredDriveForward(12));     // Leave the wall
            addSequential(new MeasuredRotateRight(rotateValue));    // Rotate Right 90
            addSequential(new MeasuredDriveForward(60));     // Move until even with switch (60 inches)
            addSequential(new MeasuredRotateLeft(rotateValue));     // Rotate Left 90
            addSequential(new MeasuredDriveForward(80));     // Move until at switch
            addSequential(new DropCube(1));
        }
    }
    void Pos3Auto(){ //If we are staring in position 3 (far right):
        dozer.encoderSetup();
        if (dozer.switchScale.charAt(0) == 'R') {                   // If switch is on right:
            addSequential(new MeasuredDriveForward(154));    // Move forward across base line (154 inches)
        }
        else if (dozer.switchScale.charAt(0) == 'L') {              // If switch is on left
            addParallel(new LiftToTop());                           // Brings the lift to the top while it starts the next command
            addSequential(new MeasuredDriveForward(154));    // Move forward until in line with switch (154 inches)
            addSequential(new MeasuredRotateLeft(90));      // Rotate left 90 degrees
            addSequential(new MeasuredDriveForward(35));     // Drive forward until directly next to edge of switch (35 inches)
            addSequential(new DropCube(1));                 // Drop the cube
            }
        }
    void BasicAuto(){
        addSequential(new MeasuredDriveForward(174));
    }
    void testAuto(){
        addSequential(new Autopath("C:\\Users\\2102062\\Documents\\Robot Mk II\\src\\main\\java\\frc\\team3067\\robot\\motionprofile\\redTestPath\\redTestPath"));
    }
}
