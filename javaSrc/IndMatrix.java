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

	private int rows;
	private int cols;
	T[][] incidenceMatrix;
//	String command = null;
	PrintWriter PW = new PrintWriter(System.out, true);
	
	
	/**
	 * Contructs empty graph.
	 */
	@SuppressWarnings("unchecked")
    public IndMatrix() {
    	// Implement me!
    		incidenceMatrix = (T[][]) new Object[rows + 1][cols + 1];
    } // end of IndMatrix()
    
	// add vertices
    public void addVertex(T vertLabel) {
        // Implement me!
    		try {
    			// get the vertex value
    			// display
    			PW.print(vertLabel);
    		} catch (NullPointerException npe) {
    			System.err.println("this shit is null");
    		}
    		
    		
    		
    } // end of addVertex()
	
    
    // srcLabel: starting point
    // tarLabel: target point
    // this method is to add connect vertices
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of addEdge()
	
	// Display every vertex connected with source vertex
    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
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
        // Implement me!
    		try {
    			
    		} catch (NullPointerException npe) {
    			System.err.println("null");
    		}
    } // end of printVertices()
	
    // print current edges connecting vertices
    public void printEdges(PrintWriter os) {
        // Implement me!
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
