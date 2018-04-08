import java.io.*;
import java.util.*;

public class DataGenerator {
    
    protected static final String progName = "DataGenerator";
    protected String[] dataSet = null;
    protected int endRange;
    Random randGenerator;
    
    // constructor
    public DataGenerator() {
        dataSet = retrieveData();
        randGenerator = new Random(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        
        DataGenerator DG = new DataGenerator();
        long startTime, endTime;
        double estimatedTime;
        String[] genData = null;
        
        startTime = System.nanoTime();
        
        for (int i = 0; i < DG.dataSet.length; i++) {
            System.out.println(DG.dataSet[i]);
        }
        
        endTime = System.nanoTime();
        
        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
        
        System.out.println("\ntime taken: " + estimatedTime + "sec");
        
        genData = DG.addVertex(100);
        
        for (int i = 0; i < genData.length; i++) {
            System.out.println("Testing: " + genData[i]);
        }
        
    }   // end of main
    
    public String[] addVertex(int dataSize) {
        
        String[] vertices = new String[dataSize];
        int dataSetRandom = 0;
        System.out.println("test: " + randGenerator.nextInt(endRange));
//        for(int i = 0; i < dataSize; i++)
//        {
//            // need to avoid assigning the same number into vertices array
//            dataSetRandom = randGenerator.nextInt(endRange);
//            vertices[i] = dataSet[dataSetRandom];
//        }
        
        return vertices;
    }   // end of adjAddVertex
    
    public String[] retrieveData() {
        
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
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
        }
        
        endRange = lineNum - 1;
        return data;
    }   // end of retrieveData
    
    
    
//    public String[] adjAddEdge(int dataSize) {
//        
//        String[] edges = new String[dataSize];
//        int dataSetRandom = 0;
//        
//        
//        return;
//    }
    
    
    
    
}   // end of class
