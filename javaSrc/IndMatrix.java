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
	// vertices
	private int initRowValue = 1;
	private int rows = 0;
	// edges
	private int initColValue = 1;
	private int cols = 0;
	
	private int minVertForEdge = 2;
	
	T[][] incMat;
	PrintWriter PW; 
	/**
	 * Contructs empty graph.
	 */
	@SuppressWarnings("unchecked")
    public IndMatrix() {
    	// Implement me!
    		incMat = (T[][]) new Object[initRowValue][initColValue];
    } // end of IndMatrix()
    
	// add vertices
    public void addVertex(T vertLabel) {
        // Implement me!
    		
    		try {
    			// if row 1, creates T[2][1] array
    			// check if new vertex char is already stored in current array
    			
    			
    			// if row value is larger value than initRowValue(1)
    			if (rows >= initRowValue) {
    				@SuppressWarnings("unchecked")
    				T[][] incMat = (T[][]) new Object[rows + 1][initColValue];
    				for (int i = 0; i < rows + 1; i++) {
    					for (int j = 0; j < initColValue; j++) {
    						if (incMat[i][j].equals(vertLabel)) {
    							System.out.println("The vertex already exists in the matrix");
    						} else {
    							incMat[rows][cols] = vertLabel;
    							System.out.print(incMat[i][j] + ", ");
	    		    				rows++;
    						}
    					}
    				}
    				System.out.println();
    			// if row value is lower than initRowValue(1)
    			} else {
    				incMat[rows][cols] = vertLabel;
    				System.out.println(vertLabel);
    				rows++;
    			}
    		} catch (ArrayIndexOutOfBoundsException e) {
    			System.err.println("array out of bounds");
    		}
    } // end of addVertex()
	
    
    // srcLabel: starting point
    // tarLabel: target point
    // this method is to add connect vertices
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    		try {
    			// if there are at least 2 vertices, add edges
    			if (rows >= minVertForEdge) {
    				T srcVertInMat, tarVertInMat;
    				for (int i = 0; i < rows; i++) {
    					for (int j = 0; j < cols; j++) {
//    						if (incMat[i][j] = )
    					}
    				}
    				
    			// otherwise, print "unable to connect"
    			} else {
    				System.out.println("At least 2 vertices are required");
    			}
    		} catch (NullPointerException npe) {
    			System.err.println("this is null");
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
        // Implement me!
    		try {
    			os.println();
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
