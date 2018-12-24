/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3067.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3067.robot.Subsystems.DrivetrainSubsystem;
import frc.team3067.robot.Subsystems.GrabberSubsystem;
import frc.team3067.robot.Subsystems.LiftSubsystem;
import frc.team3067.util.CrashTracker;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {


    SendableChooser autoChooser;
    public static RobotStick stick;
    public static DrivetrainSubsystem drive;
    public static GrabberSubsystem grabber;
    public static LiftSubsystem lift;
    public static Camera camera;
    public static String switchScale;
    public static RobotAuto auto;

  @Override
  public void robotInit() {
      CrashTracker.logRobotInit();
      try {
          stick = new RobotStick(5);

          drive = new DrivetrainSubsystem();
          grabber = new GrabberSubsystem();
          lift = new LiftSubsystem();

          auto = new RobotAuto();

          camera.CameraThread(1);

          drive.setDriveMotorSpeed(0, 0, 0, 0);

          switchScale = DriverStation.getInstance().getGameSpecificMessage();
          SendableChooser<String> autoChooser = new SendableChooser<>();
          autoChooser.addDefault("Position 1", "1");
          autoChooser.addObject("Position 2", "2");
          autoChooser.addObject("Position 3", "3");
          autoChooser.addObject("Test", "4");
          SmartDashboard.putData("Auto choices", autoChooser);
          //CSVReader.CSVRead("frc/team3067/robot/motionprofile/redTestPath/redTestPath_left_detailed.csv");
      }
      catch(Throwable t){
          CrashTracker.logThrowable(t);
          throw t;
      }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */

  @Override
  public void robotPeriodic() {
      Dashboard.SmartDashUpdate();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
      CrashTracker.logAutoInit();
      try {
          int autoSelected = Integer.parseInt((String) autoChooser.getSelected()); // Turns the string into an integer for ease of use
          System.out.println("Auto selected: " + autoSelected);
          switch (autoSelected) {
              case 1:
                  auto.Pos1Auto();
                  break;
              case 2:
                  auto.Pos2Auto();
                  break;
              case 3:
                  auto.Pos3Auto();
                  break;
              case 4:
                  auto.BasicAuto();
                  break;
          }
      }
      catch (Throwable t){
          CrashTracker.logThrowable(t);
          throw t;
      }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {/*
      TeleSpeed = SmartDashboard.getNumber("TeleSpeed", 0.5);
      TurnSpeed = SmartDashboard.getNumber("TurnSpeed", 0.5);
      driveSmoothing = SmartDashboard.getNumber("smoothing", driveSmoothing);
      smoothSteering = SmartDashboard.getBoolean("smoothSteering", true);
      if(smoothSteering){setMotorSmooth();} else{setMotorStandard();} // Sets drive motors
      grabberGrab(); // Sets grabber motors
      grabberSol(); // Sets grabber solenoid
      new Sequential(); // Sets lift motors
      SmartDashUpdate();
      */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
 //     update();
  }
}
