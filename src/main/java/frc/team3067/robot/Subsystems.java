package frc.team3067.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Subsystems {
    /***
     * -------------------------Lift------------------------------------
     */
    public class Lift extends Subsystem{

        Talon talLiftA, talLiftB;
        public DigitalInput limTopA, limBottomA, limTopB, limBottomB;
        double outputA;
        double outputB;
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
        public void lift() {
            outputA = (joystick.getButton(4) && !joystick.getButton(6))? -LiftSpeed /*down*/
                    :((!joystick.getButton(4) && joystick.getButton(6))? LiftSpeed /*up*/ :0); //A Section
            outputB = (joystick.getButton(4) && !joystick.getButton(6))? -LiftSpeed /*down*/
                    :((!joystick.getButton(4) && joystick.getButton(6))? LiftSpeed /*up*/ :0); //B Section
            talLiftA.set(outputA);//These two set the motor speed at the end
            talLiftB.set(outputB);
        }
    }

    /***
     * ----------------------------Drivetrain---------------------------------
     */
    public class Drivetrain extends Subsystem {
        Talon talLF, talRF, talLB, talRB;
        public Encoder LeftEnc, RightEnc;
        ADXRS450_Gyro gyro;
        double TeleSpeed;
        double TurnSpeed;
        double AutoSpeed;
        double driveSmoothing;
        boolean smoothSteering;
        double gyroAngle;
        public void initDefaultCommand() {
            talLF      = new Talon(0); //0
            talLB      = new Talon(1); //1
            talRF      = new Talon(2); //2
            talRB      = new Talon(3); //3

            LeftEnc  = new Encoder(4,5,true, CounterBase.EncodingType.k4X);
            RightEnc = new Encoder(6,7,false, CounterBase.EncodingType.k4X);

            gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

            AutoSpeed = 0.2; // SET AUTONOMOUS SPEED HERE
            TeleSpeed = 0.5; // SET TELEOP SPEED HERE
            TurnSpeed = 0.8; // SET TURN SPEED HERE

            driveSmoothing = 0.3;
            smoothSteering = true;
            gyro.reset();
            
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

    /***
     * -------------------------Grabber-------------------------
     */
    public class Grabber extends Subsystem{

        Talon talGrabber;
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
        public void grabberGrab() { // Spins belt in/out
            talGrabber.set(0);
            if(joystick.getButton(2) && !joystick.getButton(1)) {
                talGrabber.set(GrabberSpeed);
            }
            if(!joystick.getButton(2) && joystick.getButton(1)) {
                talGrabber.set(-GrabberSpeed);
            }
        }
        public void grabberSol() { // Grabber pneumatics
            if(joystick.getButtonPress(3)) {
                boolean val = joystick.getButtonToggle(3);
                solGrabber1.set(val);
                solGrabber2.set(!val);
            }

        }
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

}
