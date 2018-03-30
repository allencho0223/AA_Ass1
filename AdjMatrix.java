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
	List<T> vertexList = new ArrayList<T>();
	int numV = 0;
	int n = 0;
	@SuppressWarnings("unchecked")
	T edge = (T) "0";
	private T[] newArray;
	
	
    @SuppressWarnings("unchecked")
	public AdjMatrix() {
    	adjMatrix = (T[][])new Object[0][0];
    } // end of AdjMatrix()
    
    
    @SuppressWarnings("unchecked")
	public void addVertex(T vertLabel) {
    	
    	int matrixLength = adjMatrix.length;
    	for (T vertex: vertexList) 
    	{
    		if (vertex.equals(vertLabel)) {
    		System.out.println("match");
    		return;
    		}
    	}
    	
    	if (n == matrixLength) 
    	{
    		matrixLength++;
    		T[][] newAdjMatrix = (T[][]) new Object[matrixLength][matrixLength];
    	 	
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix[i].length; j++) {
                    newAdjMatrix[i][j] = adjMatrix[i][j];
                }
            }
            adjMatrix = newAdjMatrix;
         
    	}  
    	
    	vertexList.add(vertLabel);
    
    	for (int col = numV; col >=0 ; col--) {
    		newArray = (T[])new Object[3];
    		newArray[0] = vertLabel;
    		newArray[1] = vertexList.get(col);
    		newArray[2] = edge;
    		adjMatrix[numV][col] = (T) newArray; 
    	}
    	for (int row = numV; row >=0 ; row--) {
    		newArray = (T[])new Object[3];
    		newArray[0] = vertexList.get(row);
    		newArray[1] = vertLabel;
    		newArray[2] = edge;
    		adjMatrix[row][numV] = (T) newArray; 
    	}
    	numV++; n++;
        

    } // end of addVertex()
	
    
    @SuppressWarnings("unchecked")
	public void addEdge(T srcLabel, T tarLabel) {
    	T[] edgeArray = (T[]) new Object[3];
    	T newEdge = (T) new Object();
    	newEdge = (T) "1";
    	int sourceIndex;
    	int targetIndex;
    	
    	if (!(vertexList.contains(srcLabel)) || !(vertexList.contains(tarLabel)))
    			{
    				System.err.println("Error Message");
    				return;
    			}
    	else
    	
    		sourceIndex = vertexList.indexOf(srcLabel); // i.e. 2
    		targetIndex = vertexList.indexOf(tarLabel); // i.e. 4
    	
    	edgeArray = (T[]) adjMatrix[sourceIndex][targetIndex];
    	if (edgeArray[2] == edge)
    	edgeArray[2] = newEdge;
    	edgeArray = (T[]) adjMatrix[targetIndex][sourceIndex];
    	if (edgeArray[2] == edge)
    	edgeArray[2] = newEdge;
    	
    } // end of addEdge()
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        T edge = (T) "1";
        int row = vertexList.indexOf(vertLabel);
        T[] colArray;
        int col = row;
        while (col < adjMatrix.length) 
        {
        	colArray = (T[]) adjMatrix[row][col];
        	if (colArray[2].equals(edge))
        		neighbours.add(colArray[1]);
        	col++;
        }
        
        return neighbours;
        
    } // end of neighbours()
    
    
    @SuppressWarnings("unchecked")
	public void removeVertex(T vertLabel) {
    	int matrixLength = adjMatrix.length;
    	int index = vertexList.indexOf(vertLabel);
    	vertexList.remove(vertLabel);
    	T[] movedArray = (T[]) new Object[3];

    	for (int row = 0; row < index; row++) 
    	{
    		for (int col = index; col < adjMatrix.length-1; col++) 
    		{
    			int nextCol = col+1;
    			movedArray = (T[]) adjMatrix[row][nextCol];
    			adjMatrix[row][col] = (T) movedArray;
    			
    		}
    	}
    	
    	for (int row = index; row < adjMatrix.length -1; row++) 
    	{
    		for (int col = 0; col < index; col++) 
    		{
    		   	 int nextRow = row+1;
    			 movedArray = (T[]) adjMatrix[nextRow][col];
    			 adjMatrix[row][col] = (T) movedArray;
    		}
    	}
    	
    	for (int row = index; row < adjMatrix.length -1; row++) 
    	{
    		for (int col = index; col < adjMatrix.length -1; col++)
    		{
    			int nextRow = row+1;
    			int nextCol = col+1;
    			movedArray = (T[]) adjMatrix[nextRow][nextCol];
    			adjMatrix[row][col] = (T) movedArray;
    			
    		}
    	}
    	
    	matrixLength--;
		T[][] newAdjMatrix = (T[][]) new Object[matrixLength][matrixLength];
	 	
        for (int i = 0; i < newAdjMatrix.length; i++) {
            for (int j = 0; j < newAdjMatrix[i].length; j++) {
                newAdjMatrix[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = newAdjMatrix;
        n--; numV--;      
    	        
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	T[] edgeArray = (T[]) new Object[3];
    	T newEdge = (T) new Object();
    	newEdge = (T) "1";
    	int sourceIndex;
    	int targetIndex;
    	
    	if (!(vertexList.contains(srcLabel)) || !(vertexList.contains(tarLabel)))
    			{
    				System.err.println("Error Message");
    				return;
    			}
    	else
    	
    		sourceIndex = vertexList.indexOf(srcLabel); // i.e. 2
    		targetIndex = vertexList.indexOf(tarLabel); // i.e. 4
    	
    	edgeArray = (T[]) adjMatrix[sourceIndex][targetIndex];
    	if (edgeArray[2].equals(newEdge))
    	edgeArray[2] = edge;
    	edgeArray = (T[]) adjMatrix[targetIndex][sourceIndex];
    	if (edgeArray[2].equals(newEdge))
    	edgeArray[2] = edge;
    	
    	
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	
    	for (int i = 0; i < adjMatrix.length; i++) {
    	    for (int j = 0; j < adjMatrix[i].length; j++) {
    	    	System.out.print(Arrays.deepToString((Object[]) adjMatrix[i][j]));    	

    	    }System.out.println(" ");
    	
    	
    	    //os.print(vertexList);
    	}
    
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjMatrix