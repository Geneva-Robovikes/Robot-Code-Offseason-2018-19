package frc.team3067.robot;



import java.io.*;
import java.util.Arrays;

public class CSVReader {

    public static String[][] CSVRead(String csvFile){
        int columns;
        int rows;
        int x = 0;
        String line;
        String cvsSplitBy = ",";
        System.out.println("==========CSVRead==========");
        try {
            rows = countLinesNew(csvFile);
            System.out.println("Rows range from 0 to " + (rows-1));
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            BufferedReader brTest = new BufferedReader(new FileReader(csvFile));
            String[] temp = (brTest.readLine()).split(",");
            columns = temp.length;
            String[][] dst = new String[rows][columns];
            try{
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] readArray = line.split(cvsSplitBy);
                dst[x] = Arrays.copyOf(readArray, columns);
                //System.out.println(Arrays.toString(readArray));
                x++;
            }}catch(ArrayIndexOutOfBoundsException e){
                System.out.println(" Ran out of indices");
            }

            System.out.println(" Final Array:");
            for (int i = 0; i < dst.length; i++) {
                System.out.print(i);
                System.out.println(Arrays.toString(dst[i]));
            }
            System.out.println("========END CSVRead========");
            return dst;
        }
        catch (IOException e){
            System.out.println("[ERROR]");
            System.out.println("Check the input for CSVRead. The file directory is probably incorrect.");
            System.out.println("========END CSVRead========");
            return null;
        }
    }
//some line-counting code I copied from stackOverflow
    public static int countLinesNew(String filename) throws IOException{
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try{
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                System.out.println("readChars:" + readChars);
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        }
        catch(IOException e){
            System.out.println("Something went wrong while calculating the row numbers. This message shouldn't appear.");
            e.printStackTrace();

        }
        finally {
            is.close();
        }
        return -1;
    }
}
