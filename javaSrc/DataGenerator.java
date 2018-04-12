    import java.io.*;
    import java.util.*;

public class DataGenerator {
    
    Random r = new Random();
    private List<String> vertList = new ArrayList<String>();
    private List<String> edgeList = new ArrayList<String>();
    private List<String> subEdgeList = new ArrayList<String>();
    private int fbVertNum = 0;
    private int fbEdgeNum = 0;
    
    private final int reqCommandNum = 50;
    
    
    public DataGenerator() {
        
    }
    
    public void retrieveData() {
    
        BufferedWriter BW = null;
        Random r = new Random();
        BufferedReader BR = null;
        String fileName = "facebook_combined.txt";
        
        try {
            // Read facebook_combined.txt file
            BR = new BufferedReader(new FileReader(fileName));     
            String edge;
            
            // Assign all edges (88234) into edgeList
            while ((edge = BR.readLine()) != null) {
                edgeList.add(edge);
            }
        } catch (IOException ioe) {
          System.err.println(ioe.getLocalizedMessage());
        }
        
        // Tokenise edges into separate (individual) vertex and assign them into vertList array
        StringTokenizer st;
        String rightVertex = " ";
        String leftVertex = " ";
        int line = 0;
        double density, lowDensity, medDensity, highDensity = 0;
        

        /**
         *  leftVertex: the left hand side vertex as the name implies
         *  rightVertex: the right hand side vertex as the name implies
         *  
         *  FYI: the reason why I did the while condition (leftVertex.compareTo("500") != 0) is 
         *  to get the vertex numbers from 0 to 499 meaning that
         *  the right vertices are target vertices so that not in ascending order,
         *  but if you check the facebook_combined.txt, it has the ascending order of numbers in the left hand side,
         *  so it actually means that I get all the edges of 0 to 499 vertices.
         *  For the testing purpose, I will leave those print out statements, with bubble sorting.
         *  We don't need bubble sorting but if you run the code in eclipse, you will see there is not
         *  the same number and ascending order, so the numbers above 499 means that they are connected with
         *  any of the vertices from 0 to 499. For example, the last number (3290) is connected with the vertex 58.
         *  You might get lost at some points, but this is what I can explain to you right now. Also,
         *  I have to now implement getting the actual edges based on our subset data. Need to think about it.
         *  
         */

        // Not sure if we just have to assign 500 vertexes, or even other vertexes connected with those 500 vertexes.
        while (leftVertex.compareTo("500") != 0) {
            st = new StringTokenizer(edgeList.get(line), " ");
            leftVertex = st.nextToken();
            
            if (!vertList.contains(rightVertex)) {
                vertList.add(leftVertex);
            }
            
            rightVertex = st.nextToken();
            if (!vertList.contains(rightVertex)) {
                vertList.add(rightVertex);
            }
            
            subEdgeList.add(leftVertex + " " + rightVertex);
            subEdgeList.add(rightVertex + " " + leftVertex);
            line++;
//            System.out.println("prev: " + leftVertex);
//            System.out.println("crnt: " + rightVertex);
        }
        
        // bubble sort algorithm applied to sort vertices in ascending order 
        // note: not required for the assignment itself but only for the testing purpose
        for (int i = 0; i < vertList.size(); i++) {
            for (int j = 0; j < vertList.size() - 1; j++) {
                // check if we need to swap
                if (Integer.parseInt(vertList.get(j)) > Integer.parseInt(vertList.get(j + 1))) {
                    String temp = vertList.get(j);
                    vertList.set(j, vertList.get(j + 1));
                    vertList.set(j + 1, temp);
                }
            }
        }
        
        // vert: 1820, edge: 15694
//        System.out.println("vertList size: " + vertList.size());
//        System.out.println("subEdgeList size: " + subEdgeList.size());
//        for (int i = 0; i < subEdgeList.size(); i++) {
//            System.out.println("subEdgeList: " + subEdgeList.get(i));
//        }

        // Assign the size of vertices and edges into those variables
        fbVertNum = vertList.size();
        
        // As the graph is undirected, the number of edges should be doubled.
        fbEdgeNum = subEdgeList.size();
        
        // Density formula
        density =  (double) fbEdgeNum / (double) (fbVertNum  * fbVertNum);
        System.out.println("density: " + density);
        // Low density (these are just bs now lol)
        lowDensity = density * 0.3;
        System.out.println("low Density: " + lowDensity);
        medDensity = density * 0.6;
        System.out.println("med Density: " + medDensity);
        highDensity = density * 0.9;
        System.out.println("high Density: " + highDensity);
        
        // low density
        /**
         * for 5522 (magic number), initial edge num is 7847, and so the density is 0.027
         * low density is 0.008; hence the formula i used is : 
         * 0.005 : 0.001 = 15694 : x -> 3139, 15694 - 3139 = 12555
         * mid for 0.005 : 0.003 = 15694 : x -> 9416, 15694 - 9416 = 6278
         * high for 0.005 : 0.004 = 15694 : x -> 12555, 15694 - 12555 = 3139
         * eventually, we might have to think of the formula (not magic number) for better testing results
         */
        
        try {
            // low density
            BW = new BufferedWriter(new FileWriter("facebook_low_remove.txt"));
            for (int i = 0; i < 12555; i++) {
                BW.write("RE " + subEdgeList.get(r.nextInt(subEdgeList.size())) + "\n");
            }
            if (BW != null) {
                BW.close();
            }
            
            // med density
            BW = new BufferedWriter(new FileWriter("facebook_med_remove.txt"));
            for (int i = 0 ; i < 6278; i++) {
                BW.write("RE " + subEdgeList.get(r.nextInt(subEdgeList.size())) + "\n");
            }
            if (BW != null) {
                BW.close();
            }
            
            // high density
            BW = new BufferedWriter(new FileWriter("facebook_high_remove.txt"));
            for (int i = 0 ; i < 3139; i++) {
                BW.write("RE " + subEdgeList.get(r.nextInt(subEdgeList.size())) + "\n");
            }
            if (BW != null) {
                BW.close();
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        
        // Write edges and vertices into text file, but only for the testing purpose
//        try {
//            BW = new BufferedWriter(new FileWriter("edgeTest.txt"));
//            for (String edge : edgeList) {
//                BW.write(edge + "\n");
//            }
//            BW = new BufferedWriter(new FileWriter("vertTest.txt"));
//            for (String vertex : vertList) {
//                BW.write(vertex + "\n");
//            }
//            BW = new BufferedWriter(new FileWriter("subedgelist.txt"));
//            for (String subEdge : subEdgeList) {
//                BW.write(subEdge + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }   // end of retrieveData method
    
    public void addVertexAndEdge() {
        
        BufferedWriter BW = null;
        String newVert = " ";
        String newSrcEdge = " ";
        String newTarEdge = " ";
        String edgeSet = " ";
        
        int maxNum = 5000;
        List<String> newVertList = new ArrayList<String>();
        List<String> newEdgeList = new ArrayList<String>();
        
        try {
            BW = new BufferedWriter(new FileWriter("add_vert_and_edge.txt"));
            
            for (int i = 0; i < reqCommandNum; i++) {
                newVert = Integer.toString(r.nextInt(maxNum) + 1);
                if (!vertList.get(i).contains(newVert)) {
                    newVertList.add(newVert);
                }
            }
            
            for (String vertex : newVertList) {
                BW.write("AV " + vertex + "\n");
            }
            
            /**
             *  Not sure for this part because we are generating new vertices
             *  e.g. we're generating random numbers from 0 ~ 5000 (but don't worry 
             *  because it will check the same number existing in current array and not store them
             *  so technically it would be from 500 ~ 5000). My question is whether we have to make
             *  new edges from current vertices (as new vertices are not created yet)
             */
            for (int i = 0; i < reqCommandNum; i++) {
                newSrcEdge = vertList.get(r.nextInt(vertList.size()));
                newTarEdge = vertList.get(r.nextInt(vertList.size()));
                edgeSet = newSrcEdge + " " + newTarEdge;
                if (!subEdgeList.get(i).contains(edgeSet)) {
                    newEdgeList.add(edgeSet);
                    newEdgeList.add(newTarEdge + " " + newSrcEdge); 
                }
            }
            
            for (String edge : newEdgeList) {
                BW.append("AE " + edge + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            try {
                if (BW != null) {
                    BW.close();
                }
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
    
    public void addNeighbourAndShortestPath() {
        
        List<String> neighbourList = new ArrayList<String>();
        List<String> sPathList = new ArrayList<String>();
        BufferedWriter BW = null;
        String neighbour = " ";
        String srcSPath = " ";
        String tarSPath = " ";
        String sPathSet = " "; 
        int reqCommandNum = 50;
                
        
        try {
            BW = new BufferedWriter(new FileWriter("add_neigh_and_spath.txt"));
            for (int i = 0; i < reqCommandNum; i++) {
                neighbour = vertList.get(r.nextInt(vertList.size()));
                if (!neighbourList.contains(neighbour)) {
                    neighbourList.add(neighbour);
                }
            }
            
            for (String neigh : neighbourList) {
                BW.write("N " + neigh + "\n");
            }
            
            for (int i = 0; i < reqCommandNum; i++) {
                srcSPath = vertList.get(r.nextInt(vertList.size()));
                tarSPath = vertList.get(r.nextInt(vertList.size()));
                sPathSet = srcSPath + " " + tarSPath;
                if (!sPathList.contains(sPathSet)) {
                    sPathList.add(sPathSet);
                }
            }
            
            for (String path : sPathList) {
                BW.append("S " + path + "\n");
            }
            
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if (BW != null) {
                try {
                    BW.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void removeVertexAndEdge() {
        
        List<String> rmvVertList = new ArrayList<String>();
        List<String> rmvEdgeList = new ArrayList<String>();
        BufferedWriter BW = null;
        String rmvVertex = " ";
        String rmvEdge = " ";
//        StringTokenizer st;
//        String rightVertex = " ";
//        String leftVertex = " ";
        
        try {
            // Remove vertices
            BW = new BufferedWriter(new FileWriter("rmv_vert_and_edge.txt"));
            
            for (int i = 0; i < reqCommandNum; i++) {
                rmvVertex = vertList.get(r.nextInt(vertList.size()));
                if (vertList.contains(rmvVertex)) {
                    rmvVertList.add(rmvVertex);
                }
            }
            
            for (String vert : rmvVertList) {
                BW.write("RV " + vert + "\n");
            }
            
            // Remove Edges
            for (int i = 0; i < 25; i++) {
                rmvEdge = subEdgeList.get(r.nextInt(subEdgeList.size()));
                
                if (!subEdgeList.contains(rmvVertex)) {
                    rmvEdgeList.add(rmvEdge);
//                    st = new StringTokenizer(rmvEdge, " ");
//                    leftVertex = st.nextToken();
//                    rightVertex = st.nextToken();
//                    rmvEdgeList.add(rightVertex + " " + leftVertex);
                }
            }
            
            for (String edge : rmvEdgeList) {
                BW.append("RE " + edge + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        
        } finally {
            
            try {
                if (BW != null) {
                    BW.close();
                }
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    public static void main(String[] args) {
        
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        DataGenerator dataGenerator = new DataGenerator();
        long startTime, endTime;
        double estimatedTime;
        String scenario;
        
        startTime = System.nanoTime();
        try {
            scenario = inReader.readLine();
            
            dataGenerator.retrieveData();
            
            switch (scenario) {
                case "1":
                    dataGenerator.addVertexAndEdge();
                    break;
                case "2":
                    dataGenerator.addNeighbourAndShortestPath();
                    break;
                case "3":
                    dataGenerator.removeVertexAndEdge();
                    break;
            }
            
            endTime = System.nanoTime();
            
            estimatedTime = ((double) (endTime - startTime)) / Math.pow(10, 9);
            
            System.out.println("\ntime taken: " + estimatedTime + "sec");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        
        
        
        

    }   // end of main method
    

}   // end of class


//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.util.*;

//private static final String fileName = "command_generator.txt";
//private static List<String> vertList = new ArrayList<String>();
//private static List<String> edgeList = new ArrayList<String>();
//private static List<String> neighbourList = new ArrayList<String>();
//private static List<String> pathList = new ArrayList<String>();
//private static List<String> rmvVertList = new ArrayList<String>();
//private static List<String> rmvEdgeList = new ArrayList<String>();
//private static boolean edgeExistence = false;
//String command = " ";
//protected static final String progName = "DataGenerator";
//protected List<String> dataSet = new ArrayList<String>();
//protected int endRange;
//Random randGenerator;
//private int[][] adjMatrix;
//private List<Integer> vertexCollection = new ArrayList<Integer>();
//
//// constructor
//public DataGenerator() {
//
//dataSet = retrieveData();
//randGenerator = new Random(System.currentTimeMillis());
//adjMatrix = new int[0][0];
//}    


//public void dataIntoAdjMatrix() {
//  
//  List<String> vertexList = new ArrayList<String>(); 
//  List<String> rowVertex = new ArrayList<String>();
//  List<String> colVertex = new ArrayList<String>();
//  File file = new File("facebook_combined.txt");
//  int count = 0;
//  String prevVertex = " ";
//  
//  if (file.exists()) {
//      try { 
//          vertexList = Files.readAllLines(file.toPath(),Charset.defaultCharset());
//      } catch (IOException ex) {
//          ex.printStackTrace();
//      }
//      if (vertexList.isEmpty()) {
//          return;
//      }
//          
//  }
//  
//  for(String line : vertexList){
//      String [] res = line.split(" ");
//      rowVertex.add(res[0]);
//      
//      colVertex.add(res[1]);
//      
//      if (prevVertex.compareTo(res[0]) != 0) {
//          prevVertex = res[0];
//      }
//      
//      if (prevVertex.compareTo(res[0]) == 0 && count != 0) {
//          rowVertex.remove(1);
//      }
//      count++;
//  }
//  
//  System.out.println(Arrays.toString(rowVertex.toArray()));
//  System.out.println("rowV size: " + rowVertex.size());
//  System.out.println(Arrays.toString(colVertex.toArray()));
//  System.out.println("colV size: " + colVertex.size());
//  
//  
//  
//  DataGenerator DG = new DataGenerator();
//  StringTokenizer st;
//  String crntToken = null;
//  String prevToken = null;
//  int[][] newAdjMatrix = new int[dataSet.size()][dataSet.size()];
//  
//  for (int i = 0; i < newAdjMatrix.length; i++) {
//      st = new StringTokenizer(DG.dataSet.get(i));
//      crntToken = st.nextToken();
//      if (crntToken != prevToken) {
//          prevToken = crntToken;
//          System.out.println("token stored! " + prevToken);
//      }
//      else {
//          continue;
//      }
//      
//  }
//  
//  
//  
//  for (int row = 0; row < newAdjMatrix.length; row++) {
//      newAdjMatrix[row][0] = Integer.parseInt(DG.dataSet.get(row));
//  }
//  
//  for (int col = 0; col < newAdjMatrix.length; col++) {
//      newAdjMatrix[0][col] = Integer.parseInt(DG.dataSet.get(col));
//  }
//  
//  
//  
//  for (int i = 0; i < adjMatrix.length; i++) {
//      for (int j = 0; j < adjMatrix.length; j++) {
//          System.out.println("values in the matrix: " + adjMatrix[i][j]);
//      }
//  }
//  
//  
//}
////


//
//








//private static void commandGenerator() {
//
//BufferedWriter BW = null;
//Random randCommGenerator = new Random();
//String tempString = " ";
//String srcVert = " ";
//String tarVert = " ";
//int count = 0;
//int maxDataNum = 50;
//try {
//  
//  BW = new BufferedWriter(new FileWriter(fileName));
//  
//  // Generate a number of integer vertexes
//  for (int vertNum = 0; vertNum < maxDataNum; vertNum++) {
//      
//      tempString = Integer.toString(randCommGenerator.nextInt(maxDataNum) + 1);
//      vertList.add(tempString); 
//      
//      // if the same number has been created, do not store them
//      if (count > 0) {
//          for (int check = 0; check < vertList.size() - 1; check++) {
//              if (tempString.compareTo(vertList.get(check)) == 0) {
//                  vertList.remove(check);
//                  vertNum--;
//              }
//          }
//      }
//      count++;
//  }
//  
//  for (String vertex : vertList) {
//      BW.write("AV " + vertex + "\n");
////      vertBW.write(vertex + " ");
//  }
//  
////  // bubble sort algorithm applied to sort ascending order
////  for (int i = 0; i < vertList.size(); i++) {
////      for (int j = 0; j < vertList.size() - 1; j++) {
////          // check if we need to swap
////          if (Integer.parseInt(vertList.get(j)) > Integer.parseInt(vertList.get(j + 1))) {
////              String temp = vertList.get(j);
////              vertList.set(j, vertList.get(j + 1));
////              vertList.set(j + 1, temp);
////          }
////      }
////  }
//  
//  
//  // Store addEdge commands into a text file
//  count = 0;
//  for (int edgeNum = 0; edgeNum < 20; edgeNum++) {
//      srcVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      tarVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      tempString = srcVert + " " + tarVert;
//      edgeList.add(tempString);
//      
//      // If there is already a set of edge existing in the current arraylist, do not store them
//      if (count > 0) {
//          for (int check = 0; check < edgeList.size() - 1; check++) {
//              if (tempString.compareTo(edgeList.get(check)) == 0) {
//                  edgeList.remove(check);
//                  // If you want to generate the edges based on the number of array size, edgeNum--;
//              }
//          }
//      }
//      count++;
//  }
//  
//  for (String edge : edgeList) {
//      BW.append("AE " + edge + "\n");
//  }
//  
//  
//  // Store neighbour commands into text file
//  count = 0;
//  for (int neighNum = 0; neighNum < 10; neighNum++) {
//      tempString = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      neighbourList.add(tempString);
//      
//      if (count > 0) {
//          for (int check = 0; check < neighbourList.size() - 1; check++) {
//              if (Integer.parseInt(tempString) == Integer.parseInt(neighbourList.get(check))) {
//                  neighbourList.remove(check);
//              }
//          }
//      }
//      count++;
////      neighbourBW.append(tempString);
//  }
//  
//  for (String neighbour : neighbourList) {
//      BW.append("N " + neighbour + "\n");
//  }
//  
//  
//  // Store shortest path commands into a text file
//  count = 0;
//  for (int pathNum = 0; pathNum < 10; pathNum++) {
//      srcVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      tarVert = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      tempString = srcVert + " " + tarVert;
//      pathList.add(tempString);
//      
//      if (count >0) {
//          for (int check = 0; check < pathList.size() - 1; check++) {
//              if (tempString.compareTo(pathList.get(check)) == 0) {
//                  pathList.remove(check);
//              }
//          }
//      }
//      count++;
//  }
//  
//  for (String path : pathList) {
//      BW.append("S " + path + "\n");
//  }
//  
//  // Store remove vertex commands into a text file
//  count = 0;
//  for (int vertNum = 0; vertNum < 10; vertNum++) {
//      tempString = vertList.get(randCommGenerator.nextInt(vertList.size()));
//      rmvVertList.add(tempString);
//      if (count > 0) {
//          for (int check = 0; check < rmvVertList.size() - 1; check++) {
//              if (tempString.compareTo(rmvVertList.get(check)) == 0) {
//                  rmvVertList.remove(check);
//              }
//          }
//      }
//      count++;
//  }
//  
//  for (String vertex : rmvVertList) {
//      BW.append("RV " + vertex + "\n");
//  }
//  
//  
//  // Store remove edge commands into a text file
//  count = 0;
//  for (int edgeNum = 0; edgeNum < 10; edgeNum++) {
//      tempString = edgeList.get(randCommGenerator.nextInt(edgeList.size()));
//      
//      while (edgeExistence == false) {
//          for (int i = 0; i < edgeList.size(); i++) {
//              if (tempString.compareTo(edgeList.get(i)) == 0) {
//                  rmvEdgeList.add(tempString);
//                  edgeExistence = true;
//                  break;
//              }
//          }
//      }
//      
//      // If there is already a set of edge existing in the current arraylist, do not store them
//      if (count > 0) {
//          for (int check = 0; check < rmvEdgeList.size() - 1; check++) {
//              if (tempString.compareTo(rmvEdgeList.get(check)) == 0) {
//                  rmvEdgeList.remove(check);
//                  // If you want to generate the edges based on the number of array size, edgeNum--;
//              }
//          }
//      }
//      count++;
//      edgeExistence = false;
//  }
//  
//  for (String edge : rmvEdgeList) {
//      BW.append("RE " + edge + "\n");
//  }
//  
//      // Store display vertex command into a text file
//  BW.append("V\n");
//      
//      // Store display edge command into a text file
//  BW.append("E\n");
//      
//      // Store quit command into a text file
//  BW.append("Q\n");
//  
//  if (BW != null) {
//      BW.flush();
//      BW.close();
//  }
//  System.out.print("Dataset has been successfully generated");
//} catch (IOException e) {
//  System.err.println(e.getLocalizedMessage());
//}
//} // end of commandGenerator





//public List<String> retrieveData() {
//
//List<String> data = new ArrayList<String>();
////String[] data = null;
//int lineNum = 0;
//BufferedReader BR = null;
////int maxDataNum = 20;
//String fileName = "facebook_combined.txt";
//try {
//  BR = new BufferedReader(new FileReader(fileName));     
//  String line;
//  
////  data = new String[]();
//  while ((line = BR.readLine()) != null) {
//      data.add(line);
//      
//      lineNum++;
//  }
//} catch (IOException ioe) {
//  System.err.println(ioe.getLocalizedMessage());
//}
//
//endRange = lineNum - 1;
//return data;
//}   // end of retrieveData
