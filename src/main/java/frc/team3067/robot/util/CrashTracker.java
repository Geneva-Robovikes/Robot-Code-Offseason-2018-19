
package frc.team3067.util;
import java.io.*;
import java.util.*;

public class CrashTracker {
    public static void logAutoInit(){
        writeToLog("AutoInit");
    }
    public static void logRobotInit(){
        writeToLog("RobotInit");
    }
    public static void logThrowable(Throwable th){
        writeToLog("Exception", th);
    }
    private static void writeToLog(String type){
        writeToLog(type, null);
    }

    private static void writeToLog(String type, Throwable exception){

        String crashLogLocation = "C:\\Users\\Sean\\floobits\\share\\Geneva-Robovikes\\Robot-Code-Offseason-2018-19\\src\\main\\java\\frc\\team3067\\robot\\crashLog.txt";
        try (PrintWriter logWriter = new PrintWriter(new FileWriter(crashLogLocation,true))){
            logWriter.print(new Date().toString()+": "+type+": ");
            if(exception!=null){
                exception.printStackTrace(logWriter);
            }
            logWriter.println();
        }
        catch(IOException e){
            try {
                File logFile = new File(crashLogLocation);
                logFile.createNewFile();
                try (PrintWriter logWriter = new PrintWriter(new FileWriter(crashLogLocation,true))){
                    logWriter.print(new Date().toString()+": "+type+": ");
                    if(exception!=null){
                        exception.printStackTrace(logWriter);
                    }
                    logWriter.println();
                }
            }
            catch(IOException g){
                g.printStackTrace();
            }
        }
    }
}
