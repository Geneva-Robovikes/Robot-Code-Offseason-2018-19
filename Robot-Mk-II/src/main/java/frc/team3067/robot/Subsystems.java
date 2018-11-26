package frc.team3067.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Subsystems extends RobotInstance {
    /***
     * -------------------------Lift------------------------------------
     */
    public class Lift extends Subsystem{
        public void initDefaultCommand() {}
        public void liftRaise(){talLiftB.set(LiftSpeed);}
        public void liftLower(){talLiftB.set(-LiftSpeed);}
        public void liftStop(){talLiftB.set(0);}
    }

    /***
     * ----------------------------Drivetrain---------------------------------
     */
    public class Drivetrain extends Subsystem {
        public void setDriveMotorSpeed(double LF, double LB, double RF, double RB){
            talLF.set(LF);
            talLB.set(LB);
            talRF.set(RF);
            talRB.set(RB);
        }
        public void initDefaultCommand() {}
        public void driveForward(){ setDriveMotorSpeed(-AutoSpeed,-AutoSpeed,AutoSpeed,AutoSpeed);}
        public void driveBackward(){ setDriveMotorSpeed(AutoSpeed,AutoSpeed,-AutoSpeed,-AutoSpeed);}
        public void driveStop(){ setDriveMotorSpeed(0,0,0,0);}
        public void driveRotateRight(){ setDriveMotorSpeed(-AutoSpeed,-AutoSpeed,-AutoSpeed,-AutoSpeed);}
        public void driveRotateLeft(){setDriveMotorSpeed(AutoSpeed,AutoSpeed,AutoSpeed,AutoSpeed);}
    }

    /***
     * -------------------------Grabber-------------------------
     */
    public class Grabber extends Subsystem{
        public void initDefaultCommand(){ talGrabber.set(0); }
        public void grabberOn(){ talGrabber.set(LiftSpeed); }
        public void grabberOnCustom(double speed){ talGrabber.set(speed); }
        public void grabberOff(){ talGrabber.set(0); }
        public void grabberOpen(){ solGrabber1.set(false); }
        public void grabberClose(){ solGrabber1.set(true); }
        public void dropCube(double seconds){
            talGrabber.set(LiftSpeed);
            solGrabber1.set(false);
            Timer.delay(seconds);
            talGrabber.set(0);
        }
    }

}
