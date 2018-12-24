package frc.team3067.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Camera {
    UsbCamera camera;
    private final static int cameraWidth = 480;
    private final static int cameraHeight = 640;
    public void CameraThread(int mode){
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
