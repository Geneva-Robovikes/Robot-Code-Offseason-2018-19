package frc.team3067.robot.Subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team3067.robot.Constants;

public class DrivetrainSubsystem extends Subsystem {
    public Talon talLF, talRF, talLB, talRB;
    public Encoder LeftEnc, RightEnc;
    public ADXRS450_Gyro gyro;
    public double TeleSpeed, TurnSpeed, AutoSpeed;
    public double driveSmoothing;
    public boolean smoothSteering;
    public double gyroAngle;
    public void initDefaultCommand() {
        talLF      = new Talon(Constants.kTalLFPort); //0
        talLB      = new Talon(Constants.kTalLBPort); //1
        talRF      = new Talon(Constants.kTalRFPort); //2
        talRB      = new Talon(Constants.kTalRBPort); //3

        LeftEnc  = new Encoder(4,5,true, CounterBase.EncodingType.k4X);
        RightEnc = new Encoder(6,7,false, CounterBase.EncodingType.k4X);

        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);


        driveSmoothing = 0.3;
        smoothSteering = true;
        gyro.reset();

    }

    public void encoderSetup(){
        LeftEnc.reset(); // Reset encoders when method runs
        RightEnc.reset();
        LeftEnc.setDistancePerPulse(Constants.kDistanceVal);
        RightEnc.setDistancePerPulse(Constants.kDistanceVal);
    }
    public void encoderReset() {
        LeftEnc.reset(); // Reset encoders when method runs
        RightEnc.reset();
    }
    public void setDriveMotorSpeed(double LF, double LB, double RF, double RB){
        talLF.set(LF);
        talLB.set(LB);
        talRF.set(RF);
        talRB.set(RB);
    }


    public void driveForward(){ setDriveMotorSpeed(-AutoSpeed,-AutoSpeed,AutoSpeed,AutoSpeed);}
    public void driveBackward(){ setDriveMotorSpeed(AutoSpeed,AutoSpeed,-AutoSpeed,-AutoSpeed);}
    public void driveStop(){ setDriveMotorSpeed(0,0,0,0);}
    public void driveRotateRight(){ setDriveMotorSpeed(-AutoSpeed,-AutoSpeed,-AutoSpeed,-AutoSpeed);}
    public void driveRotateLeft(){setDriveMotorSpeed(AutoSpeed,AutoSpeed,AutoSpeed,AutoSpeed);}
}