package frc.team3067.robot.Subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3067.robot.Constants;

public class GrabberSubsystem extends Subsystem {

    public Talon talGrabber;
    public Solenoid solGrabber1, solGrabber2;

    public void initDefaultCommand(){
        talGrabber = new Talon(Constants.kTalGrabberPort); //6

        solGrabber1 = new Solenoid(Constants.kSolGrabber1Port);
        solGrabber2 = new Solenoid(Constants.kSolGrabber2Port);

        talGrabber.set(0);


    }

    public void grabberOn(){talGrabber.set(Constants.kGrabberSpeed); }
    public void grabberOnCustom(double speed){talGrabber.set(speed); }
    public void grabberOff(){talGrabber.set(0); }
    public void grabberOpen(){solGrabber1.set(false); }
    public void grabberClose(){solGrabber1.set(true); }
    public void dropCube(double seconds){
        talGrabber.set(Constants.kGrabberSpeed);
        solGrabber1.set(false);
        Timer.delay(seconds);
        talGrabber.set(0);
    }
}
