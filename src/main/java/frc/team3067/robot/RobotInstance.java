package frc.team3067.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class RobotInstance { // Declares robot components.
	RobotStick StickXD;
	 // front left, front right, back left, back right, grabber belt, lift A, lift B



	RobotAuto auto;


	double TeleSpeed;
	double TurnSpeed;
	double driveSmoothing;

	final double AutoSpeed;
	public final double GrabberSpeed;
	String switchScale;

	private final static int cameraWidth = 480;
	private final static int cameraHeight = 640;
	private final static double DistanceVal = (8 * Math.PI)/360; //1 rotation = 8pi inches


	public Lift lift;
	public Drivetrain drivetrain;
	public Grabber grabber;

	public RobotInstance() { // Instantiate joystick, talons, encoders, limit switches, etc...

		StickXD = new RobotStick(5);






;





		gyroAngle = 0;

		auto = new RobotAuto();

		switchScale = DriverStation.getInstance().getGameSpecificMessage();

        encoderReset();
		SmartDashUpdate();

		//Subsystems go here
		subsystems = new Subsystems();
		lift = subsystems.new Lift();
		drivetrain = subsystems.new Drivetrain();
		grabber = subsystems.new Grabber();
	}

	void encoderSetup(){
		LeftEnc.reset(); // Reset encoders when method runs
		RightEnc.reset();
		LeftEnc.setDistancePerPulse(DistanceVal);
		RightEnc.setDistancePerPulse(DistanceVal);
	}
	public void encoderReset(){
		LeftEnc.reset(); // Reset encoders when method runs
		RightEnc.reset();
	}

	public void update() { // What runs in teleopPeriodic
		TeleSpeed = SmartDashboard.getNumber("TeleSpeed", 0.5);
		TurnSpeed = SmartDashboard.getNumber("TurnSpeed", 0.5);
		driveSmoothing = SmartDashboard.getNumber("smoothing", driveSmoothing);
		smoothSteering = SmartDashboard.getBoolean("smoothSteering", true);
		if(smoothSteering){setMotorSmooth();} else{setMotorStandard();} // Sets drive motors
		grabberGrab(); // Sets grabber motors
		grabberSol(); // Sets grabber solenoid
		lift(); // Sets lift motors
		SmartDashUpdate();
	}
	
	public double scaleMotor(double stickInput, Talon talon) {		
		double newMotorValue = talon.get() * (1 - driveSmoothing) + stickInput * driveSmoothing; 
		if (newMotorValue < .02 && newMotorValue > -.02)
			return 0;
		else
			return newMotorValue;
	}	
	
	public void setMotorSmooth() { // Arcade drive
		talLF.set(scaleMotor(TeleSpeed * -StickXD.getDY() + TurnSpeed * StickXD.getDZ(), talLF));
		talLB.set(scaleMotor(TeleSpeed * -StickXD.getDY() + TurnSpeed * StickXD.getDZ(), talLB));
		talRF.set(scaleMotor(TeleSpeed * StickXD.getDY() + TurnSpeed * StickXD.getDZ(),  talRF));
		talRB.set(scaleMotor(TeleSpeed * StickXD.getDY() + TurnSpeed * StickXD.getDZ(),  talRB));
	}
	
	public void setMotorStandard() {
		talLF.set(TeleSpeed * -StickXD.getDY() + TurnSpeed * StickXD.getDZ());
		talLB.set(TeleSpeed * -StickXD.getDY() + TurnSpeed * StickXD.getDZ());
		talRF.set(TeleSpeed * StickXD.getDY() + TurnSpeed * StickXD.getDZ());
		talRB.set(TeleSpeed * StickXD.getDY() + TurnSpeed * StickXD.getDZ());
	}
	

	


	public void SmartDashUpdate() {
		SmartDashboard.putNumber("talLF", talLF.get());
		SmartDashboard.putNumber("talLB", talLB.get());
		SmartDashboard.putNumber("talRF", talRF.get());
		SmartDashboard.putNumber("talRB", talRB.get());
		SmartDashboard.putNumber("gyroAngle", getGyroAngle());
		SmartDashboard.putNumber("TeleSpeed", TeleSpeed);
		SmartDashboard.putNumber("TurnSpeed", TurnSpeed);
		SmartDashboard.putNumber("smoothing", driveSmoothing);
		SmartDashboard.putBoolean("smoothSteering", smoothSteering);
		SmartDashboard.updateValues();
	}


	public void CameraThread(int mode){
		UsbCamera camera;
		SmartDashboard.putNumber("Camera port", CameraServer.kBasePort);
		System.out.println("Camera port: " + CameraServer.kBasePort);
		switch (mode){
			case 0:
				System.out.println("Camera mode is in disabled mode. Video will not be captured.");
			case 1:
				camera = CameraServer.getInstance().startAutomaticCapture();
				camera.setResolution(cameraWidth,cameraHeight);
				System.out.println("Camera mode is in automatic capture mode. This will output directly to the SmartDashboard.");
			case 2:
				camera = CameraServer.getInstance().startAutomaticCapture();
				camera.setResolution(cameraWidth,cameraHeight);
				System.out.println("Camera mode is in autoprocess mode. This is a test and will output a filtered version of the footage.");
				new Thread(() -> {

					CvSink cvSink = CameraServer.getInstance().getVideo();
					CvSource outputStream = CameraServer.getInstance().putVideo("FULL SEND CAM", cameraWidth, cameraHeight);

					Mat source = new Mat();
					Mat convertOutput = new Mat();
					Mat output = new Mat();
					Scalar HSVMax = new Scalar(180,255,255);
					Scalar HSVMin =	new Scalar(0,185, 185);

					while(!Thread.interrupted()){
						cvSink.grabFrame(source);
						Imgproc.cvtColor(source,convertOutput,Imgproc.COLOR_RGB2HSV);
						Core.inRange(convertOutput,HSVMin, HSVMax, output);
						outputStream.putFrame(output);
					}
				}).start();
			case 3:
				System.out.println("Camera mode is in offboard processing mode. The camera should be plugged into a coprocessor (Should be the raspberry pi).");
				System.out.println("More details at: https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/687863-off-board-vision-processing-in-java");
		}
	}
}
