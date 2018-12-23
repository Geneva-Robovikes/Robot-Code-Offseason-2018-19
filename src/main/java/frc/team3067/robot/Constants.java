package frc.team3067.robot;

public class Constants {


    public final static double kDistanceVal = (8 * Math.PI)/360; //1 rotation = 8pi inches

    public static final double kAutoSpeed = 0.2; // SET AUTONOMOUS SPEED HERE
    public static final double kTeleSpeed = 0.5; // SET TELEOP SPEED HERE
    public static final double kTurnSpeed = 0.8; // SET TURN SPEED HERE
    public static final double kGrabberSpeed = 0.2; // SET GRABBER SPEED HERE
    public static final double kDriveSmoothing = 0.3;
    public static final boolean kSmoothSteering = true;
    public static final double kLiftSpeed = 1;

    public final static int kCameraWidth = 480;
    public final static int kCameraHeight = 640;

    //Talons
    public static final int kTalLFPort = 0;
    public static final int kTalLBPort = 1;
    public static final int kTalRFPort = 2;
    public static final int kTalRBPort = 3;
    public static final int kTalLiftAPort = 4;
    public static final int kTalLiftBPort = 5;
    public static final int kTalGrabberPort = 6;

    //Solenoids
    public static final int kSolGrabber1Port = 0;
    public static final int kSolGrabber2Port = 1;

    //Limit Switches
    public static final int kLimTopAPort  = 0;
    public static final int kLimBottomAPort  = 1;
    public static final int kLimTopBPort  = 2;
    public static final int kLimBottomBPort  = 3;
}
