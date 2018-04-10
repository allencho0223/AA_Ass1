import java.io.*;
import java.util.*;

public class DataGenerator {
    
    private static final String fileName = "command_generator.in";
    private static List<String> vertList = new ArrayList<String>();
    private static List<String> edgeList = new ArrayList<String>();
    private static List<String> neighbourList = new ArrayList<String>();
    private static List<String> pathList = new ArrayList<String>();
    private static List<String> rmvVertList = new ArrayList<String>();
    private static List<String> rmvEdgeList = new ArrayList<String>();
    private static boolean edgeExistence = false;
    String command = " ";
//    protected static final String progName = "DataGenerator";
//    protected String[] dataSet = null;
//    protected int endRange;
//    Random randGenerator;
//    private int[][] adjMatrix;;
//    private List<Integer> vertexCollection = new ArrayList<Integer>();
    
    // constructor
    public DataGenerator() {
        
//        dataSet = retrieveData();
//        randGenerator = new Random(System.currentTimeMillis());
//        adjMatrix = new int[0][0];
    }
    
    private static void commandGenerator() {
        
        BufferedWriter BW = null;
        Random randCommGenerator = new Random();
        String tempString = " ";
        String srcVert = " ";
        String tarVert = " ";
        int count = 0;
        int maxDataNum = 50;
        try {
            // Generate a number of integer vertexes
            for (int vertNum = 0; vertNum < 5; vertNum++) {
                
                tempString = Integer.toString(randCommGenerator.nextInt(5) + 1);
                vertList.add(tempString); 
                
                // if the same number has been created, do not store them
                if (count > 0) {
                    for (int check = 0; check < vertList.size() - 1; check++) {
                        if (tempString.compareTo(vertList.get(check)) == 0) {
                            vertList.remove(check);
                            vertNum--;
                        }
                    }
                }
                count++;
            }
            
//            // bubble sort algorithm applied to sort ascending order
//            for (int i = 0; i < vertList.size(); i++) {
//                for (int j = 0; j < vertList.size() - 1; j++) {
//                    // check if we need to swap
//                    if (Integer.parseInt(vertList.get(j)) > Integer.parseInt(vertList.get(j + 1))) {
//                        String temp = vertList.get(j);
//                        vertList.set(j, vertList.get(j + 1));
//                        vertList.set(j + 1, temp);
//                    }
//                }
//            }
            
            BW = new BufferedWriter(new FileWriter(fileName));
            
            // Store addVertex commands into a text file
            for (String vertex : vertList) {
                BW.write("AV " + vertex + "\n");
            }
            
            
            
            // Store addEdge commands into a text file
            count = 0;
            for (int edgeNum = 0; edgeNum < 20; edgeNum++) {
                srcVert = Integer.toString(randCommGenerator.nextInt(5) + 1);
                tarVert = Integer.toString(randCommGenerator.nextInt(5) + 1);
                tempString = srcVert + " " + tarVert;
                edgeList.add(tempString);
                
                // If there is already a set of edge existing in the current arraylist, do not store them
                if (count > 0) {
                    for (int check = 0; check < edgeList.size() - 1; check++) {
                        if (tempString.compareTo(edgeList.get(check)) == 0) {
                            edgeList.remove(check);
                            // If you want to generate the edges based on the number of array size, edgeNum--;
                        }
                    }
                }
                count++;
            }
            
            for (String edge : edgeList) {
                BW.append("AE " + edge + "\n");
            }
            
            
            // Store neighbour commands into text file
            count = 0;
            for (int neighNum = 0; neighNum < 10; neighNum++) {
                tempString = Integer.toString(randCommGenerator.nextInt(5) + 1);
                neighbourList.add(tempString);
                
                if (count > 0) {
                    for (int check = 0; check < neighbourList.size() - 1; check++) {
                        if (Integer.parseInt(tempString) == Integer.parseInt(neighbourList.get(check))) {
                            System.out.println("same");
                            neighbourList.remove(check);
                        }
                    }
                }
                count++;
            }
            
            for (String neighbour : neighbourList) {
                BW.append("N " + neighbour + "\n");
            }
            
            
            // Store shortest path commands into a text file
            count = 0;
            for (int pathNum = 0; pathNum < 10; pathNum++) {
                srcVert = Integer.toString(randCommGenerator.nextInt(5) + 1);
                tarVert = Integer.toString(randCommGenerator.nextInt(5) + 1);
                tempString = "S " + srcVert + " " + tarVert + "\n";
                pathList.add(tempString);
                
                if (count >0) {
                    for (int check = 0; check < pathList.size() - 1; check++) {
                        if (tempString.compareTo(pathList.get(check)) == 0) {
                            pathList.remove(check);
                        }
                    }
                }
                count++;
            }
            
            for (String path : pathList) {
                BW.append(path);
            }
            
            // Store remove vertex commands into a text file
            count = 0;
            for (int vertNum = 0; vertNum < 10; vertNum++) {
                tempString = Integer.toString(randCommGenerator.nextInt(5) + 1);
                rmvVertList.add(tempString);
                if (count > 0) {
                    for (int check = 0; check < rmvVertList.size() - 1; check++) {
                        if (tempString.compareTo(rmvVertList.get(check)) == 0) {
                            rmvVertList.remove(check);
                        }
                    }
                }
                count++;
            }
            
            for (String vertex : rmvVertList) {
                BW.append("RV " + vertex + "\n");
            }
            
            // Store remove edge commands into a text file
            count = 0;
            for (int edgeNum = 0; edgeNum < 10; edgeNum++) {
                
                srcVert = Integer.toString(randCommGenerator.nextInt(vertList.size()));
                tarVert = Integer.toString(randCommGenerator.nextInt(vertList.size()));
                tempString = srcVert + " " + tarVert;
                while (edgeExistence == false) {
                    System.out.println(edgeExistence);
                    for (int i = 0; i < edgeList.size(); i++) {
                        if (tempString.compareTo(edgeList.get(i)) == 0) {
                            rmvEdgeList.add(tempString);
                            edgeExistence = true;
                            break;
                        }
                    }
                }
                
                
                // If there is already a set of edge existing in the current arraylist, do not store them
                if (count > 0) {
                    for (int check = 0; check < rmvEdgeList.size() - 1; check++) {
                        if (tempString.compareTo(rmvEdgeList.get(check)) == 0) {
                            rmvEdgeList.remove(check);
                            // If you want to generate the edges based on the number of array size, edgeNum--;
                        }
                    }
                }
                count++;
            }
            
            for (String edge : rmvEdgeList) {
                BW.append(edge);
            }
            
                // Store display vertex command into a text file
            BW.append("V\n");
                
                // Store display edge command into a text file
            BW.append("E\n");
                
                // Store quit command into a text file
            BW.append("Q\n");
            
            if (BW != null) {
                BW.flush();
                BW.close();
            }
            
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        
        
        commandGenerator();
        
        
        
        
        
//        DataGenerator DG = new DataGenerator();
//        long startTime, endTime;
//        double estimatedTime;
//        String[] genData = null;
//        
//        startTime = System.nanoTime();
//        
//        for (int i = 0; i < DG.dataSet.length; i++) {
//            System.out.println(DG.dataSet[i]);
//        }
//        
//        DG.dataIntoAdjMatrix();
//        
//        endTime = System.nanoTime();
//        
//        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
//        
//        System.out.println("\ntime taken: " + estimatedTime + "sec");
//        
//        genData = DG.addVertex(100);
//        
//        for (int i = 0; i < genData.length; i++) {
//            System.out.println("Testing: " + genData[i]);
//        }
//        
    }   // end of main
    
//    public void dataIntoAdjMatrix() {
//        
//        List<String> vertexList = new ArrayList<String>(); 
//        List<String> rowVertex = new ArrayList<String>();
//        List<String> colVertex = new ArrayList<String>();
//        File file = new File("facebook_test.txt");
//        int count = 0;
//        String prevVertex = " ";
//        
//        if (file.exists()) {
//            try { 
//                vertexList = Files.readAllLines(file.toPath(),Charset.defaultCharset());
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            if (vertexList.isEmpty()) {
//                return;
//            }
//                
//        }
//        
//        for(String line : vertexList){
//            String [] res = line.split(" ");
//            rowVertex.add(res[0]);
//            
//            colVertex.add(res[1]);
//            
//            if (prevVertex.compareTo(res[0]) != 0) {
//                prevVertex = res[0];
//            }
//            
//            if (prevVertex.compareTo(res[0]) == 0 && count != 0) {
//                rowVertex.remove(1);
//            }
//            count++;
//        }
//        
//        System.out.println(Arrays.toString(rowVertex.toArray()));
//        System.out.println("rowV size: " + rowVertex.size());
//        System.out.println(Arrays.toString(colVertex.toArray()));
//        System.out.println("colV size: " + colVertex.size());
//        
//        
//        
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
//        
//        
//        
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
//        
//        
//    }
//    
//    public String[] retrieveData() {
//        
//        String[] data = null;
//        int lineNum = 0;
//        BufferedReader BR = null;
//        int maxDataNum = 20;
//        String fileName = "facebook_test.txt";
//        try {
//            BR = new BufferedReader(new FileReader(fileName));     
//            String line;
//            
//            data = new String[maxDataNum];
//            while ((line = BR.readLine()) != null) {
//                data[lineNum] = line;
//                
//                lineNum++;
//            }
//        } catch (IOException ioe) {
//            System.err.println(ioe.getLocalizedMessage());
//        }
//        
//        endRange = lineNum - 1;
//        return data;
//    }   // end of retrieveData
    
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
//    
//
//    
//    
//    
//    public String[] adjAddEdge(int dataSize) {
//        
//        String[] edges = new String[dataSize];
//        int dataSetRandom = 0;
//        
//        
//        return;
//    }
//    
//    
//    
//    
}   // end of class
