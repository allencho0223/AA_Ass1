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
	private T[][] indMatrix;
	List<T> vertexList = new ArrayList<T>();
	List<T> edgeList = new ArrayList<T>();
	@SuppressWarnings("rawtypes")
	List<Edge> edgeCollection = new ArrayList<Edge>();
	private T[] matrixArray;
	@SuppressWarnings("rawtypes")
	Edge edge;
	@SuppressWarnings("unchecked")
	T e = (T) "0";

    @SuppressWarnings("unchecked")
	public IndMatrix() {
    	indMatrix = (T[][])new Object[12][15];

    } // end of IndMatrix()
    
    
    @SuppressWarnings("unchecked")
	public void addVertex(T vertLabel) {
    	//int matrixLength = indMatrix.length;
    	
    	if (addtoVertexList(vertLabel) == true) 
    		vertexList.add(vertLabel);
    	//System.out.println("vertexlist = " + vertexList);
    	
    	edgeList.add(vertLabel); 
    	if (edgeList.size() > 1 && edgeList.size() % 2 == 0) {
    	for (int i = 0; i < edgeList.size() -1 ; i++ ) 
    	{
    		T vertex1 = edgeList.get(i);
    		int next = ++i;
    		T vertex2 = edgeList.get(next);
    		edge = new Edge<T>(vertex1, vertex2);

    	}
    	edgeCollection.add(edge); 
    	//System.out.println("edgeCollection = " + edge.toString());
    	}  
    	
    	
    	for (int row = 0; row<vertexList.size(); row++) 
    	{
    		for (int col = 0; col < edgeCollection.size(); col++) 
    		{
    			matrixArray = (T[])new Object[3];
    			matrixArray[0] = vertexList.get(row);
    			if (edgeList.size() > 1)
    			matrixArray[1] = (T) edgeCollection.get(col);
    			else 
    				matrixArray[1] = null;
    			matrixArray[2] = e;
    			indMatrix[row][col] = (T) matrixArray; 
    		}
    	}
    	
    	
    	
    	
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
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
    	for (int i = 0; i < indMatrix.length; i++) {
    	    for (int j = 0; j < indMatrix[i].length; j++) {
    	    	System.out.print(Arrays.deepToString((Object[]) indMatrix[i][j]));    	

    	    }System.out.println(" ");
    	
    	}
    }
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    
    
    public boolean addtoVertexList(T vertLabel)
    {
    	if (vertexList.contains(vertLabel)){
    		return false;
    	}
    	return true;
    }
    
} // end of class IndMatrix







