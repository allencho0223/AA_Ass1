import java.io.*;
import java.util.*;

public class DataGenerator {
    
    protected static final String progName = "DataGenerator";
    protected String[] dataSet = null;
    protected int endRange;
    Random randGenerator;
    
    // constructor
    public DataGenerator() {
        dataSet = receiveData();
        randGenerator = new Random(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        
        DataGenerator DG = new DataGenerator();
        long startTime, endTime;
        double estimatedTime;
        
        startTime = System.nanoTime();
        
        for (int i = 0; i < DG.dataSet.length; i++) {
            System.out.println(DG.dataSet[i]);
        }
//        System.out.println(DG.dataSet.length);
        endTime = System.nanoTime();
        
        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
        
        System.out.println("\ntime taken: " + estimatedTime + "sec");
    }   // end of main
    
    public String[] receiveData() {
        
        String[] data = null;
        int lineNum = 0;
        BufferedReader BR = null;
        String fileName = "facebook_test.txt";
        try {
            BR = new BufferedReader(new FileReader(fileName));     
            String line;
            data = new String[1507];
            while ((line = BR.readLine()) != null) {
                data[lineNum] = line;
                
                lineNum++;
            }
            if (BR != null) {
                BR.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
        }
        
        endRange = lineNum - 1;
        return data;
    }
    
    
}   // end of class
