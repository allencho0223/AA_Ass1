import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private T[][] adjMatrix;
//	private int rows = 5;
//	private int columns = 5;
	private int count = 2;
	private int index = 0;
	private boolean[][] edgeMatrix;
	/**
	 * Contructs empty graph.
	 */
    @SuppressWarnings("unchecked")
	public AdjMatrix() {

    	adjMatrix = (T[][])new Object[count][count];
      	
    	// Implement me!
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
    	
    	int sizeOfMatrix = adjMatrix.length;   // assign length of matrix to sizeOfMatrix 
    	adjMatrix[index][index] = vertLabel;	// adjMatrix index 0 0 = vertex label 
    	index++;
    	if(index > 0.5 * sizeOfMatrix) {		// if index 0 0 is more than half the sizeOfMatrix
    		sizeOfMatrix++;					//sizeOfmatrix variable + 1
    		
    	 @SuppressWarnings("unchecked")
		T[][] newAdjMatrix = (T[][]) new Object[sizeOfMatrix][sizeOfMatrix];
         for (int i = 0; i < adjMatrix.length; i++) {
             for (int j = 0; j < adjMatrix[i].length; j++) {
                 newAdjMatrix[i][j] = adjMatrix[i][j];
             }
         }    	
         adjMatrix = newAdjMatrix;
    	 }  	    
    	
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	
    	edgeMatrix = new boolean[10][10];
    	for (int i = 0; i < adjMatrix.length; i++) {
    	    for (int j = 0; j < adjMatrix[i].length; j++) {
    	    	if (adjMatrix[i][j] != null && adjMatrix[i][j].equals(srcLabel)) {
    	    		edgeMatrix[i][j] = true;
    	    	//	edgeMatrix[j][i] = true;
    	    	}  	    	
    	    	if (adjMatrix[i][j] != null && adjMatrix[i][j].equals(tarLabel)) {
        	    	edgeMatrix[i][j] = true;
        	    //	edgeMatrix[j][i] = true;
        	    		
    	    	}
    	    		
    	    }
    	 
    	}
    	 
    	
        // Implement me!
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    public void printVertices(PrintWriter os) {
    	
    	for (int i = 0; i < adjMatrix.length; i++) {
    	    for (int j = 0; j < adjMatrix[i].length; j++) {
    	    	//if (adjMatrix[i][j] != null)
    	    		//os.print(adjMatrix[i][j]);
    	    		
    	        System.out.print(adjMatrix[i][j] + " ");

    	    }System.out.println("");
    	  
    	}
        // Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	
    	for (int i = 0; i < adjMatrix.length; i++) {
    	    for (int j = 0; j < adjMatrix[i].length; j++) {
    	    	if (edgeMatrix[i][j] == true) {
        	    	System.out.print(" " + adjMatrix[i][j]);
        	    	System.out.print(" " + adjMatrix[j][i]);

    	    	}
    	    } 

    	  
    	}
    	
    	
    	
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjMatrix