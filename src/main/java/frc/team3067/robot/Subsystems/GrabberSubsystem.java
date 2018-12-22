package frc.team3067.robot.Subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GrabberSubsystem extends Subsystem {

    public Talon talGrabber;
    public Solenoid solGrabber1, solGrabber2;
    public double GrabberSpeed;

    public void initDefaultCommand(){
        talGrabber = new Talon(6); //6

        solGrabber1 = new Solenoid(0);
        solGrabber2 = new Solenoid(1);

        talGrabber.set(0);
        GrabberSpeed = 0.2; // SET GRABBER SPEED HERE

    }
    //TODO: make anything with Joystick in it into a command


    public void grabberOn(){talGrabber.set(GrabberSpeed); }
    public void grabberOnCustom(double speed){talGrabber.set(speed); }
    public void grabberOff(){talGrabber.set(0); }
    public void grabberOpen(){solGrabber1.set(false); }
    public void grabberClose(){solGrabber1.set(true); }
    public void dropCube(double seconds){
        talGrabber.set(GrabberSpeed);
        solGrabber1.set(false);
        Timer.delay(seconds);
        talGrabber.set(0);
    }
}
