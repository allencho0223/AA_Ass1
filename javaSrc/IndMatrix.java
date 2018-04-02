import java.io.*;
import java.util.*;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class IndMatrix <T extends Object> implements FriendshipGraph<T>
{
	
    int vertNum = 1;
    @SuppressWarnings("unchecked")
    T edgeDisconnected = (T) "0";
    @SuppressWarnings("unchecked")
    T edgeConnected = (T) "1";
    private int edgeNum = 1;
    private int maxEdgeNum = 0;
    private int maxVertNum = 0;
    @SuppressWarnings("unchecked")
    T[] vertIndex = (T[])new Object[1];
	
	
	private T[][] incMatrix;
	List<T> vertices = new ArrayList<T>();
	
	/**
	 * Contructs empty graph.
	 */
	@SuppressWarnings("unchecked")
    public IndMatrix() {
    	// Implement me!
	    // Initial array setting
	    /*
	     * 0 e1 e2 e3 e4 e5 e6         a[0][0] a[0][1] a[0][2] .....
	     * A 0  0  0  0  0  0          a[1][0] a[1][1] a[1][2] .....
	     * B 0  0  0  0  0  0      =>  a[2][0] a[2][1] a[2][2] .....
	     * C 0  0  0  0  0  0          a[3][0] a[3][1] a[3][2] .....
	     * 
	     */
    		incMatrix = (T[][]) new Object[1][1];
    } // end of IndMatrix()
    
	// add vertices
    @SuppressWarnings("unchecked")
    public void addVertex(T vertLabel) {
        // Implement me!
        // Check if the vertex exists in current matrix
        for (T vertex : vertices) {
            if (vertex.equals(vertLabel)) {
                System.out.println("the vertex already exists");
                return;
            }
        }
        
     
        // When vert 1 -> edge 1, array[2][2]
        // When vert 2 -> edge 3, array[3][4] 
        // When vert 3 -> edge 6, array[4][7]
        // When vert 4 -> edge 10, array[5][11]
        
        // Calculate the maximum number of edges
        maxEdgeNum = vertNum * (vertNum + 1) / 2;
        
        // create a new matrix and temp matrix to transfer data
        incMatrix = (T[][]) new Object[vertNum + 1][maxEdgeNum + 1];
        
        T[][] newIncMatrix = (T[][]) new Object[vertNum + 1][maxEdgeNum + 1];
        
        for (int i = 0; i < vertNum; i++) {
            for (int j = 0; j < maxEdgeNum; j++) {
                newIncMatrix[i][j] = incMatrix[i][j];
            }
        }
        
        incMatrix = newIncMatrix;
        
        vertices.add(vertLabel);
        
     // Store vertices into the row and column 0 based on the number of vertices
        vertIndex[0] = vertLabel;
        incMatrix[vertNum][0] = (T) vertIndex;
        
        
//        for (int vertex = vertNum; vertex >= 1; vertex--) {
//            vertIndex = (T[])new Object[1];
//            vertIndex[0] = vertLabel;
//            incMatrix[vertex][0] = (T) vertIndex;
//        }
        
        // Store edges (initially make it 0) into the column (maximum number of columns to avoid array out of bounds exception) 
        for (int vertex = vertNum; vertex >= 1; vertex--) {
            for (int edge = maxEdgeNum; edge >= 1; edge--) {
                vertIndex = (T[])new Object[1];
                vertIndex[0] = edgeDisconnected;
                incMatrix[vertex][edge] = (T) vertIndex;
            }
        }
        
//        for (int row = vertNum; row >= 0; row--) {
//            vertIndex = (T[])new Object[3];
//            vertIndex[0] = vertices.get(row);
//            vertIndex[1] = vertLabel;
//            vertIndex[2] = edgeDisconnected;
//            incMatrix[row][0] = (T) vertIndex; 
//        }
//        
//        for (int col = vertNum; col >= 0; col--) {
//            vertIndex = (T[])new Object[3];
//            vertIndex[0] = vertLabel;
//            vertIndex[1] = vertices.get(col);
//            vertIndex[2] = edgeDisconnected;
//            incMatrix[vertNum][col] = (T) vertIndex;
//        }
        
        
        vertNum++;
    } // end of addVertex()
	
    @SuppressWarnings("unchecked")
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
        int srcIndex = 0;
        int tarIndex = 0;
        
        T[] edgeArray = (T[]) new Object[1];
        
    		try {
    		    if (incMatrix.length >= 2 && (vertices.contains(srcLabel) && vertices.contains(tarLabel))) {
    		        
    		        srcIndex = vertices.indexOf(srcLabel);
                tarIndex = vertices.indexOf(tarLabel);
    		        
    		        edgeArray = (T[]) incMatrix[maxVertNum][maxEdgeNum];
    		        
    		        System.out.println("src: " + srcIndex);
    		        System.out.println("tar: " + tarIndex);
//    		        for (T vertex : vertices) {
//    		            if (vertex.equals(srcLabel)) {
//    		                System.out.println("the vertex already exists");
//    		                return;
//    		            }
//    		        }
    		        
    		        
//    		       srcIndex = vertices.indexOf(srcLabel);
//    		       tarIndex = vertices.indexOf(tarLabel);
//
//    		       edgeArray = (T[]) incMatrix[srcIndex][tarIndex];
//    		       if (edgeArray[2] == edgeDisconnected) {
//    		           edgeArray[2] = edgeConnected;
//    		       }
//    		       edgeArray = (T[]) incMatrix[tarIndex][srcIndex];
//    		       if (edgeArray[2] == edgeDisconnected) {
//                   edgeArray[2] = edgeConnected;
//               }
    		        
    		    } else {
    		        System.err.println("Not enough number of vertices to be connected");
    		    }
    			
    		} catch (NullPointerException npe) {
    			System.err.println(npe.getLocalizedMessage());
    		} catch (ArrayIndexOutOfBoundsException ai) {
    		    System.err.println(ai.getLocalizedMessage());
    		}
    } // end of addEdge()
	
	// Display every vertex connected with source vertex
    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
//        neighbours.contains(vertLabel);
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    // remove a vertex as the name describes
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    // remove edge b/w vertices
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    // print current vertices
    public void printVertices(PrintWriter os) {
        
        for (int i = 0; i < incMatrix.length; i++) {
            for (int j = 0; j < incMatrix[i].length; j++) {
                System.out.print(Arrays.deepToString((Object[]) incMatrix[i][j]));      

            }System.out.println(" ");
        
        }
        
        try {
            for (T v: vertices) {
                os.print(v + " "); 
                }
        } finally {
            if (os != null) {
                os.close();
            }
        }
    
    } // end of printVertices()
    
    
    @SuppressWarnings("unchecked")
    public void printEdges(PrintWriter os) {
        T[] edge = null;
        try {
            for (int i = 0; i < incMatrix.length; i++)
            {
                for (int j = 0; j < incMatrix[i].length; j++) 
                {
                    edge = (T[]) incMatrix[i][j];
                    if (edge[2].equals("1"))
                    os.println(edge[0] + " " + edge[1]);
                }
            }       
            
        } finally {
            if (os != null) {
                os.close();
            }
        }

    } // end of printEdges()
    
    // displays the selected vertices and distance value
    // e.g. S A E returns A E 3 like this
    // if the 2 vertices are not connected, it returns -1
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class IndMatrix
