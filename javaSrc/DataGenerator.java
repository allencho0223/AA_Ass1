import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class DataGenerator {
    
    protected static final String progName = "DataGenerator";
    protected String[] dataSet = null;
    protected int endRange;
    Random randGenerator;
    private int[][] adjMatrix;;
    private List<Integer> vertexCollection = new ArrayList<Integer>();
    
    // constructor
    public DataGenerator() {
        dataSet = retrieveData();
        randGenerator = new Random(System.currentTimeMillis());
        adjMatrix = new int[0][0];
    }

    public static void main(String[] args) {
        
        DataGenerator DG = new DataGenerator();
        long startTime, endTime;
        double estimatedTime;
        String[] genData = null;
        
        startTime = System.nanoTime();
        
//        for (int i = 0; i < DG.dataSet.length; i++) {
//            System.out.println(DG.dataSet[i]);
//        }
        
        DG.dataIntoAdjMatrix();
        
        endTime = System.nanoTime();
        
        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
        
        System.out.println("\ntime taken: " + estimatedTime + "sec");
        
//        genData = DG.addVertex(100);
//        
//        for (int i = 0; i < genData.length; i++) {
//            System.out.println("Testing: " + genData[i]);
//        }
        
    }   // end of main
    
    public void dataIntoAdjMatrix() {
        
        List<String> vertexList = new ArrayList<String>(); 
        List<String> rowVertex = new ArrayList<String>();
        List<String> colVertex = new ArrayList<String>();
        File file = new File("facebook_test.txt");
        int count = 0;
        String prevVertex = " ";
        
        if (file.exists()) {
            try { 
                vertexList = Files.readAllLines(file.toPath(),Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (vertexList.isEmpty()) {
                return;
            }
                
        }
        
        for(String line : vertexList){
            String [] res = line.split(" ");
            rowVertex.add(res[0]);
            
            colVertex.add(res[1]);
            
            if (prevVertex.compareTo(res[0]) != 0) {
                prevVertex = res[0];
            }
            
            if (prevVertex.compareTo(res[0]) == 0 && count != 0) {
                rowVertex.remove(1);
            }
            count++;
        }
        
        System.out.println(Arrays.toString(rowVertex.toArray()));
        System.out.println("rowV size: " + rowVertex.size());
        System.out.println(Arrays.toString(colVertex.toArray()));
        System.out.println("colV size: " + colVertex.size());
        
        
        
//        DataGenerator DG = new DataGenerator();
//        StringTokenizer st;
//        String crntToken = null;
//        String prevToken = null;
//        int[][] newAdjMatrix = new int[dataSet.length][dataSet.length];
//        
//        for (int i = 0; i < newAdjMatrix.length; i++) {
//            st = new StringTokenizer(DG.dataSet[i]);
//            crntToken = st.nextToken();
//            if (crntToken != prevToken) {
//                prevToken = crntToken;
//                System.out.println("token stored! " + prevToken);
//            }
//            else {
//                continue;
//            }
//            
//        }
        
        
        
//        for (int row = 0; row < newAdjMatrix.length; row++) {
//            newAdjMatrix[row][0] = Integer.parseInt(DG.dataSet[row]);
//        }
//        
//        for (int col = 0; col < newAdjMatrix.length; col++) {
//            newAdjMatrix[0][col] = Integer.parseInt(DG.dataSet[col]);
//        }
//        
//        
//        
//        for (int i = 0; i < adjMatrix.length; i++) {
//            for (int j = 0; j < adjMatrix.length; j++) {
//                System.out.println("values in the matrix: " + adjMatrix[i][j]);
//            }
//        }
        
        
    }
    
    public String[] retrieveData() {
        
        String[] data = null;
        int lineNum = 0;
        BufferedReader BR = null;
        int maxDataNum = 20;
        String fileName = "facebook_test.txt";
        try {
            BR = new BufferedReader(new FileReader(fileName));     
            String line;
            
            data = new String[maxDataNum];
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
    
//    public String[] addVertex(int dataSize) {
//        
//        String[] vertices = new String[dataSize];
//        int dataSetRandom = 0;
//        System.out.println("test: " + randGenerator.nextInt(endRange));
//        for(int i = 0; i < dataSize; i++)
//        {
//            // need to avoid assigning the same number into vertices array
//            dataSetRandom = randGenerator.nextInt(endRange);
//            vertices[i] = dataSet[dataSetRandom];
//        }
//        
//        return vertices;
//    }   // end of adjAddVertex
    

    
    
    
//    public String[] adjAddEdge(int dataSize) {
//        
//        String[] edges = new String[dataSize];
//        int dataSetRandom = 0;
//        
//        
//        return;
//    }
    
    
    
    
}   // end of class
