
import java.io.*;

public class DataGenerator {

    public static void main(String[] args) {
        timeEstimation();
    }   // end of main
    
    private static void readFile() {
        InputStream input = null;
        
        try {
            input = new BufferedInputStream(new FileInputStream("facebook_test.txt"));
            byte[] buffer = new byte[8192];
            
            for (int length = 0; (length = input.read(buffer)) != -1;) {
                System.out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }   // end of readFile
    
    private static void timeEstimation() {
        long startTime, endTime;
        double estimatedTime;
        
        startTime = System.nanoTime();
        // test must be implemented inbetween startTime and endTime
        readFile();
        //
        endTime = System.nanoTime();
        
        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
        
        System.out.println("\ntime taken: " + estimatedTime + "sec");
    }
    
    
}   // end of class
