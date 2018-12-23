package frc.team3067.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3067.robot.Constants;

public class LiftSubsystem extends Subsystem {

    public Talon talLiftA, talLiftB;
    public DigitalInput limTopA, limBottomA, limTopB, limBottomB;

    public void initDefaultCommand() {

        talLiftA   = new Talon(Constants.kTalLiftAPort); //4
        talLiftB   = new Talon(Constants.kTalLiftBPort); //5

        limTopA    = new DigitalInput(Constants.kLimTopAPort);
        limBottomA = new DigitalInput(Constants.kLimBottomAPort);
        limTopB    = new DigitalInput(Constants.kLimTopBPort);
        limBottomB = new DigitalInput(Constants.kLimBottomBPort);
    }
    public void liftRaise(){talLiftB.set(Constants.kLiftSpeed);}
    public void liftLower(){talLiftB.set(-Constants.kLiftSpeed);}
    public void liftStop(){talLiftB.set(0);}

}