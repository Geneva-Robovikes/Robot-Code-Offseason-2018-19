package frc.team3067.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {

    public Talon talLiftA, talLiftB;
    public DigitalInput limTopA, limBottomA, limTopB, limBottomB;
    public double LiftSpeed;

    public void initDefaultCommand() {

        talLiftA   = new Talon(4); //5
        talLiftB   = new Talon(5); //4

        limTopA    = new DigitalInput(0);
        limBottomA = new DigitalInput(1);
        limTopB    = new DigitalInput(2);
        limBottomB = new DigitalInput(3);

        LiftSpeed = 1; // SET LIFT SPEED HERE
    }
    public void liftRaise(){talLiftB.set(LiftSpeed);}
    public void liftLower(){talLiftB.set(-LiftSpeed);}
    public void liftStop(){talLiftB.set(0);}

}