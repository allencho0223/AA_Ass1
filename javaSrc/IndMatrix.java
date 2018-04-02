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
	
    int vertNum = 0;
    @SuppressWarnings("unchecked")
    T edgeDisconnected = (T) "0";
    T edgeConnected = (T) "1";
    private int nextEdge = 0;
    private T[] vertIndex;
	
	
	private T[][] incMatrix;
	List<T> vertices = new ArrayList<T>();
	
	/**
	 * Contructs empty graph.
	 */
	@SuppressWarnings("unchecked")
    public IndMatrix() {
    	// Implement me!
    		incMatrix = (T[][]) new Object[0][0];
    } // end of IndMatrix()
    
	// add vertices
    @SuppressWarnings("unchecked")
    public void addVertex(T vertLabel) {
        // Implement me!
        
        int matrixLength = incMatrix.length;
        
        // Check if the vertex exists in current matrix
        for (T vertex : vertices) {
            if (vertex.equals(vertLabel)) {
                System.out.println("the vertex already exists");
                return;
            }
        }
        
//        if (vertNum == matrixLength) {
        matrixLength++;
        T[][] newIncMatrix = (T[][]) new Object[matrixLength][matrixLength];
        
        for (int i = 0; i < incMatrix.length; i++) {
            for (int j = 0; j < incMatrix[i].length; j++) {
                newIncMatrix[i][j] = incMatrix[i][j];
            }
        }
        incMatrix = newIncMatrix;
//        }
        
        vertices.add(vertLabel);
        
        // Store vertex, edge into vertIndex array then assign to the matrix at the end
        for (int row = vertNum; row >= 0; row--) {
            vertIndex = (T[])new Object[3];
            vertIndex[0] = vertices.get(row);
            vertIndex[1] = vertLabel;
            vertIndex[2] = edgeDisconnected;
            incMatrix[row][vertNum] = (T) vertIndex; 
        }
        
        for (int col = vertNum; col >= 0; col--) {
            vertIndex = (T[])new Object[3];
            vertIndex[0] = vertLabel;
            vertIndex[1] = vertices.get(col);
            vertIndex[2] = edgeDisconnected;
            incMatrix[vertNum][col] = (T) vertIndex;
        }
        
        
        vertNum++;
    } // end of addVertex()
	
    @SuppressWarnings("unchecked")
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
        int srcIndex = 0;
        int tarIndex = 0;
        
        T[] edgeArray = (T[]) new Object[3];
        
    		try {
    		    if (incMatrix.length >= 1 && (vertices.contains(srcLabel) && vertices.contains(tarLabel))) {
    		       srcIndex = vertices.indexOf(srcLabel);
    		       tarIndex = vertices.indexOf(tarLabel);

    		       edgeArray = (T[]) incMatrix[srcIndex][tarIndex];
    		       if (edgeArray[2] == edgeDisconnected) {
    		           edgeArray[2] = edgeConnected;
    		       }
    		       nextEdge++;
    		        
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
