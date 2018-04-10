import java.io.*;
import java.util.*;

public class DataGenerator {
    
    private static final String fileName = "command_generator.in";
    private static final String vertOutput = "command_generator.vert.exp";
    private static final String edgeOutput = "command_generator.edge.exp";
    private static final String distOutput = "command_generator.dist.exp";
    private static final String neighbourOutput = "command_generator.neigh.exp";
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
        
        BufferedWriter BW, vertBW, edgeBW, distBW, neighbourBW = null;
        Random randCommGenerator = new Random();
        String tempString = " ";
        String srcVert = " ";
        String tarVert = " ";
        int count = 0;
        int maxDataNum = 50;
        try {
            
            BW = new BufferedWriter(new FileWriter(fileName));
            vertBW = new BufferedWriter(new FileWriter(vertOutput));
            edgeBW = new BufferedWriter(new FileWriter(edgeOutput));
            distBW = new BufferedWriter(new FileWriter(distOutput));
            neighbourBW = new BufferedWriter(new FileWriter(neighbourOutput));
            
            
            // Generate a number of integer vertexes
            for (int vertNum = 0; vertNum < maxDataNum; vertNum++) {
                
                tempString = Integer.toString(randCommGenerator.nextInt(maxDataNum) + 1);
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
                BW.write("AV " + vertList.get(vertNum) + "\n");
                vertBW.write(vertList.get(vertNum) + " ");
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
            
            
            
            // Store addEdge commands into a text file
            count = 0;
            for (int edgeNum = 0; edgeNum < 20; edgeNum++) {
                srcVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
                tarVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
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
                BW.append("AE " + edgeList.get(edgeNum) + "\n");
                edgeBW.append(edgeList.get(edgeNum) + "\n");
            }
            
            for (int i = edgeList.size() - 1; i >= 0; i--) {
                edgeBW.append(edgeList.get(i) + "\n");
            }
//            for (String edge : edgeList) {
//                
//            }
            
            
            // Store neighbour commands into text file
            count = 0;
            for (int neighNum = 0; neighNum < 10; neighNum++) {
                tempString = vertList.get(randCommGenerator.nextInt(vertList.size()));
                neighbourList.add(tempString);
                
                if (count > 0) {
                    for (int check = 0; check < neighbourList.size() - 1; check++) {
                        if (Integer.parseInt(tempString) == Integer.parseInt(neighbourList.get(check))) {
                            neighbourList.remove(check);
                        }
                    }
                }
                count++;
                neighbourBW.append(tempString);
            }
            
            for (String neighbour : neighbourList) {
                BW.append("N " + neighbour + "\n");
            }
            
            
            // Store shortest path commands into a text file
            count = 0;
            for (int pathNum = 0; pathNum < 10; pathNum++) {
                srcVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
                tarVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
                tempString = srcVert + " " + tarVert;
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
                BW.append("S " + path + "\n");
            }
            
            // Store remove vertex commands into a text file
            count = 0;
            for (int vertNum = 0; vertNum < 10; vertNum++) {
                tempString = vertList.get(randCommGenerator.nextInt(vertList.size()));
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
                tempString = edgeList.get(randCommGenerator.nextInt(edgeList.size()));
                
                while (edgeExistence == false) {
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
                edgeExistence = false;
            }
            
            for (String edge : rmvEdgeList) {
                BW.append("RE " + edge + "\n");
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
            if (vertBW != null) {
                vertBW.flush();
                vertBW.close();
            }
            if (edgeBW != null) {
                edgeBW.flush();
                edgeBW.close();
            }
            if (distBW != null) {
                distBW.flush();
                distBW.close();
            }
            if (neighbourBW != null) {
                neighbourBW.flush();
                neighbourBW.close();
            }
            System.out.print("Dataset has been successfully generated");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        
        long startTime, endTime;
        double estimatedTime;
        
        startTime = System.nanoTime();
        commandGenerator();
        endTime = System.nanoTime();
        estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
        
        System.out.println("\ntime taken: " + estimatedTime + "sec");
        
        
        
//        DataGenerator DG = new DataGenerator();

//        String[] genData = null;
//        
        
//        
//        for (int i = 0; i < DG.dataSet.length; i++) {
//            System.out.println(DG.dataSet[i]);
//        }
//        
//        DG.dataIntoAdjMatrix();
//        
        
//        

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
