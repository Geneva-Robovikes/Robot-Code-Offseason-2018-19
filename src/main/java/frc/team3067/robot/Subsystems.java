package frc.team3067.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import static frc.team3067.robot.Robot.dozer;
public class Subsystems {
    /***
     * -------------------------Lift------------------------------------
     */
    public class Lift extends Subsystem{
        public void initDefaultCommand() {}
        public void liftRaise(){dozer.talLiftB.set(dozer.LiftSpeed);}
        public void liftLower(){dozer.talLiftB.set(-dozer.LiftSpeed);}
        public void liftStop(){dozer.talLiftB.set(0);}
    }

    /***
     * ----------------------------Drivetrain---------------------------------
     */
    public class Drivetrain extends Subsystem {
        public void setDriveMotorSpeed(double LF, double LB, double RF, double RB){
            dozer.talLF.set(LF);
            dozer.talLB.set(LB);
            dozer.talRF.set(RF);
            dozer.talRB.set(RB);
        }
        public void initDefaultCommand() {}
        public void driveForward(){ setDriveMotorSpeed(-dozer.AutoSpeed,-dozer.AutoSpeed,dozer.AutoSpeed,dozer.AutoSpeed);}
        public void driveBackward(){ setDriveMotorSpeed(dozer.AutoSpeed,dozer.AutoSpeed,-dozer.AutoSpeed,-dozer.AutoSpeed);}
        public void driveStop(){ setDriveMotorSpeed(0,0,0,0);}
        public void driveRotateRight(){ setDriveMotorSpeed(-dozer.AutoSpeed,-dozer.AutoSpeed,-dozer.AutoSpeed,-dozer.AutoSpeed);}
        public void driveRotateLeft(){setDriveMotorSpeed(dozer.AutoSpeed,dozer.AutoSpeed,dozer.AutoSpeed,dozer.AutoSpeed);}
    }

    /***
     * -------------------------Grabber-------------------------
     */
    public class Grabber extends Subsystem{
        public void initDefaultCommand(){ dozer.talGrabber.set(0); }
        public void grabberOn(){ dozer.talGrabber.set(dozer.LiftSpeed); }
        public void grabberOnCustom(double speed){ dozer.talGrabber.set(speed); }
        public void grabberOff(){ dozer.talGrabber.set(0); }
        public void grabberOpen(){ dozer.solGrabber1.set(false); }
        public void grabberClose(){ dozer.solGrabber1.set(true); }
        public void dropCube(double seconds){
            dozer.talGrabber.set(dozer.LiftSpeed);
            dozer.solGrabber1.set(false);
            Timer.delay(seconds);
            dozer.talGrabber.set(0);
        }
    }

}
