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
	private int[][] indMatrix;
	private List<T> vertexCollection = new ArrayList<T>();
	@SuppressWarnings("rawtypes")
	private List<Edge> edgeCollection = new ArrayList<Edge>();
	int numV = 0;
	int numE = 0;
	
    public IndMatrix() {
    	indMatrix = new int [0][0];
    	
    } // end of IndMatrix()
    
    
    public void addVertex(T vertLabel) {
      
    	for (T vertex: vertexCollection) 
    	{
    		if (vertex.equals(vertLabel)) 
    		return;
    	}
    
    	vertexCollection.add(vertLabel);
    	numV++;
    	System.out.println("numV = " + numV);
    	createMatrix();
    	
    } // end of addVertex()
	
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void addEdge(T srcLabel, T tarLabel) {
    	
    	Edge edge = new Edge(srcLabel, tarLabel);
    	
    	if (!(vertexCollection.contains(srcLabel)) 
    			|| !(vertexCollection.contains(tarLabel)))
		{
			System.err.println("Error Message");
			return;
		}
    	
   
    	// check that no duplicate edges come through
    	
    	
    	edgeCollection.add(edge);
    	numE++;  
    	System.out.println("numE = " + numE);
    	createMatrix();
  	
    } // end of addEdge()
	

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        for (Edge e: edgeCollection) {
    		if (e.getVertex1().equals(vertLabel))
    			neighbours.add((T) e.getVertex2());
    		
    		if (e.getVertex2().equals(vertLabel))
    			neighbours.add((T) e.getVertex1());
    		
    	}
      
        return neighbours;
    } // end of neighbours()
    
    
    @SuppressWarnings("rawtypes")
	public void removeVertex(T vertLabel) {        

    	List <Edge> newEdgeList = new ArrayList<Edge>();

       	if (!vertexCollection.contains(vertLabel)) 
    	{
    		System.err.println("vertLabel not found");
			return;
    	}
    	 	
    	vertexCollection.remove(vertLabel);

    	
    	for (Edge e: edgeCollection) {
    		if (!(e.getVertex1().equals(vertLabel)) && !(e.getVertex2().equals(vertLabel))){ 
    			newEdgeList.add(e);
    		}
    		
    	}
    	edgeCollection = newEdgeList;
    	
    	numV--; 
    	numE = edgeCollection.size();
    	
    	createMatrix();
    	

    	
    } // end of removeVertex()
	
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeEdge(T srcLabel, T tarLabel) {
    	
    	List <Edge> newEdgeList = new ArrayList<Edge>();
    	
    	if (!(vertexCollection.contains(srcLabel)) 
    			|| !(vertexCollection.contains(tarLabel)))
		{
			System.err.println("Error Message");
			return;
		}

    	for (Edge e: edgeCollection) {
    		if (!(e.getVertex1().equals(srcLabel) && e.getVertex2().equals(tarLabel)) 
    				&& !(e.getVertex2().equals(srcLabel) && e.getVertex1().equals(tarLabel))) {
    				newEdgeList.add(e);
    		}
    		
    	}

    		edgeCollection = newEdgeList;
    		numE = edgeCollection.size();
    		createMatrix();
    	
    	
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	for (int i = 0; i < indMatrix.length; i++) {
    	    for (int j = 0; j < indMatrix[i].length; j++) {
    	    	System.out.print(indMatrix[i][j] + " ");    	

    	    }System.out.println("");
    	
    	}
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	int[] pair = new int[2];
    	int count = 0;
    	
        String l = null;
        int test = 0;
    	boolean[] marked = new boolean[vertexCollection.size()];
       	for (int i = 0; i < marked.length; i++)
    		marked[i] = false;
    	
		Queue<int[]> q = new ArrayDeque<int[]>(); 
		int sourceVert = vertexCollection.indexOf(vertLabel1);
		int targetVert = vertexCollection.indexOf(vertLabel2);
		
    	marked[sourceVert] = true;
    	pair[0] = sourceVert; 
    	pair[1] = count;
    	q.add(pair);
    	
    	while(!q.isEmpty()) 
    	{
    		int[] p = q.remove();
    		int vert = p[0];
    		int dist = p[1];
    		
    		if(vert == targetVert)   //if index of sourceVert == index of targetVert return the distance
    			return dist;
    		
    		
    		List<T> getNeighbours = neighbours(vertexCollection.get(vert));
    		for (T w: getNeighbours) 
    		{
    				l = w.toString(); 
    				test = vertexCollection.indexOf(l);

    		
    			if (!marked[test])
					{
						marked[test] = true;
						int[] newPair = new int[2];
						newPair[0] = test;
						newPair[1] = dist+1;
						q.add(newPair);
					}
    		}
    		
    	}
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    
    
    
    @SuppressWarnings("rawtypes")
	public void createMatrix() 
    {	
    	indMatrix = new int[numV][numE];
         
    	for (int row = 0; row < numV ; row++) 
    	{
    		for (int col = 0; col < numE; col++) 
    		{  
    			Edge e = (Edge) edgeCollection.get(col);
    			if (vertexCollection.get(row).equals(e.getVertex1()) 
    					|| vertexCollection.get(row).equals(e.getVertex2()))
       			indMatrix[row][col] = 1;  
    			else 
    				indMatrix[row][col] = 0;  

    		}
    	}    	
    	
    	
    }
    
    
    
    
} // end of class IndMatrix
