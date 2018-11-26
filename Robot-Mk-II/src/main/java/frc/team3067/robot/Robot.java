/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3067.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;
import org.opencv.imgproc.*;
import org.opencv.core.Mat;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {

    public static RobotInstance dozer;
    SendableChooser autoChooser;

    private synchronized static void setRobotInstance() {
        // Allows you to make a static instance of RobotInstance so you can use it in commands
        dozer = new RobotInstance();
    }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */


  @Override
  public void robotInit() {
      dozer.CameraThread(1);
      setRobotInstance();
      dozer.drivetrain.setDriveMotorSpeed(0,0,0,0);
      SendableChooser<String> autoChooser = new SendableChooser<>();
      autoChooser.addDefault("Position 1", "1");
      autoChooser.addObject("Position 2", "2");
      autoChooser.addObject("Position 3", "3");
      autoChooser.addObject("Test", "4");
      SmartDashboard.putData("Auto choices", autoChooser);
      CSVReader.CSVRead("frc/team3067/robot/motionprofile/redTestPath/redTestPath_left_detailed.csv");

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
  public void robotPeriodic() {}

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
    int autoSelected = Integer.parseInt((String)autoChooser.getSelected()); // Turns the string into an integer for ease of use
      System.out.println("Auto selected: " + autoSelected);
      switch (autoSelected) {
          case 1:
            dozer.auto.Pos1Auto();
              break;
          case 2:
              dozer.auto.Pos2Auto();
              break;
          case 3:
              dozer.auto.Pos3Auto();
              break;
          case 4:
              dozer.auto.BasicAuto();
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
  public void teleopPeriodic() {
      dozer.update();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
      dozer.update();
  }
}
