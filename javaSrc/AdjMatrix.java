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
	private int[][] adjMatrix;
	private List<T> vertexCollection = new ArrayList<T>();
	@SuppressWarnings("rawtypes")
	private List<Edge> edgeCollection = new ArrayList<Edge>();
	int numV = 0;
	
	
    public AdjMatrix() {
    	adjMatrix = new int [0][0];
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
    	
    	for (T vertex: vertexCollection) 
    	{
    		if (vertex.equals(vertLabel)) 
    		return;
    	}
    
    	vertexCollection.add(vertLabel);
    	numV++;
    	createMatrix();
    	
    	
    } // end of addVertex()
	
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void addEdge(T srcLabel, T tarLabel) {
    	
    	Edge edge = new Edge(srcLabel, tarLabel);
   
    	edgeErrorCheck(srcLabel, tarLabel);

    	// check that no duplicate edges come through
    	for (Edge e: edgeCollection) {				
    		if (e.getVertex1().equals(srcLabel) && e.getVertex2().equals(tarLabel) 
    				|| e.getVertex1().equals(tarLabel) && e.getVertex2().equals(srcLabel)){ 
    			System.err.println("Edge Already Exists!");
    			return;
    		}
    	}
    	
    	edgeCollection.add(edge);
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
    	createMatrix();
    	
    } // end of removeVertex()
	
    
    @SuppressWarnings("rawtypes")
	public void removeEdge(T srcLabel, T tarLabel) {
    	
    	List <Edge> newEdgeList = new ArrayList<Edge>();
    	
    	edgeErrorCheck(srcLabel, tarLabel);

    	for (Edge e: edgeCollection) {
    		if (!(e.getVertex1().equals(srcLabel) && e.getVertex2().equals(tarLabel)) 
    				&& !(e.getVertex2().equals(srcLabel) && e.getVertex1().equals(tarLabel))) {
    				newEdgeList.add(e);
    		}
    		
    	}

    		edgeCollection = newEdgeList;
    		createMatrix();
    		
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	
		for (T v: vertexCollection) 
		{
			os.print(v + " ");
		}
		os.println(" ");
		
		os.flush();
		
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
		for (int row = 0; row < adjMatrix.length; row++) 
		{
			for (int col = 0; col < adjMatrix[row].length; col++) 
			{
				if (adjMatrix[row][col] == 1) 
				{
					os.print(vertexCollection.get(row));
					os.println(" " + vertexCollection.get(col));
				}
			}
		}
    	os.flush();
    	
    	
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	int[] pair = new int[2];
    	int count = 0;
    	
        String l = null;
        int neigh = 0;
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
    				neigh = vertexCollection.indexOf(l);

    			if (!marked[neigh])
					{
						marked[neigh] = true;
						int[] newPair = new int[2];
						newPair[0] = neigh;
						newPair[1] = dist+1;
						q.add(newPair);
					}
    		}
    		
    	}
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    
    @SuppressWarnings("rawtypes")
   	public void createMatrix()  {	
    	
    	System.out.println("vertexCollection = " + vertexCollection);
    	System.out.println("edgeCollection = " + edgeCollection);
       	adjMatrix = new int[numV][numV];
       	
       	
       	for (int row = 0; row < numV ; row++) 
       	{
       		for (int col = 0; col < numV; col++) 
       		{  
             		adjMatrix[row][col] = 0;  

             		for (Edge e: edgeCollection) 
             		{
             			int v1 = vertexCollection.indexOf(e.getVertex1()); //1
             			int v2 = vertexCollection.indexOf(e.getVertex2()); //4
             			
             			if (row == v1 && col == v2 || row == v2 && col == v1) 
             				adjMatrix[row][col] = 1;
             		}	
       		}
       	}
       		
       	

   }// end of class createMatrix
    
    
    public void edgeErrorCheck(T srcLabel, T tarLabel) 
    {

    	if (!(vertexCollection.contains(srcLabel)) 
    			|| !(vertexCollection.contains(tarLabel))
    				|| srcLabel.equals(tarLabel))
		{
			System.err.println("Vertex not found or invalid edge.");
			return;
		}
    }
    
    
} // end of class AdjMatrix