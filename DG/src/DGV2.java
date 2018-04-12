import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DGV2 {
	
	static List<String> vertexList = new ArrayList<String>();
	static List<Edge> edgeList = new ArrayList<Edge>();

    public static void readFile(String inputFile, String outputFile) throws IOException {

    	BufferedReader br = null;
    	
    	FileWriter fileStream = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fileStream);
		
		int count = 0;
    	try {
    		br = new BufferedReader(new FileReader(inputFile));
		
	    	String line;
	    	String delimiter = " ";
	    	String[] tokens;


	    	while ((line = br.readLine()) != null && count < 5000) {  //count is how many lines from facebook_combined.txt we want to read in
	    		
	    		tokens = line.split(delimiter);
	    		Edge edge = new Edge(tokens[0], tokens[1]);
	    		
	    		if (!vertexList.contains(tokens[0])) 
	    			vertexList.add(tokens[0]);
	    		
	    		if (!vertexList.contains(tokens[1])) 
	    			vertexList.add(tokens[1]);
	    		
	    		if (edgeExists(tokens[0], tokens[1]) == false)
	    			edgeList.add(edge);
	    	
	    	count++;
	    	}			
		}
		catch (FileNotFoundException ex) {
			System.err.println("File " + inputFile + " not found.");
		}
		catch(IOException ex) {
			System.err.println("Cannot open file " + inputFile);
		}
    
    	adjustDensity(0.2, calculateCurrentDensity());
    	
    	System.out.println("did we make it?"); 
    	
		for (Edge e1: edgeList) {
	    	 bw.write(e1.getVertex1() + " ");
	    	 bw.write(e1.getVertex2());
			 bw.newLine();
			 }
		bw.close();
	
    }     
    
   
   
    public static void adjustDensity(Double targetDensity, Double currentDensity) 
    {
    	
    	Edge edge = null;
    	String vertex = null;
    	if (currentDensity <= targetDensity) 
    	{
    		double density = currentDensity;
    		while (density != targetDensity) 
    		{
	    		edge = new Edge(randomNumberGenerator(), randomNumberGenerator());
	    		vertex = randomNumberGenerator();
	    		if (vertexList.contains(vertex)) 
	    		vertexList.remove(vertex);
	    		if (!edgeExists(edge.getVertex1(), edge.getVertex2()))
	    		edgeList.add(edge);
    		
	    		density = calculateCurrentDensity();
    		}
    	}
    	
    	if (currentDensity > targetDensity) 
    	{
    		System.out.println("more than targetDensity");
    		double density = currentDensity;
    		while (currentDensity != targetDensity) 
    		{
    			edge = new Edge(randomNumberGenerator(), randomNumberGenerator());
    			vertex = randomNumberGenerator();
	    		if (!vertexList.contains(vertex)) 
	    		vertexList.add(vertex);
        		if (edgeExists(edge.getVertex1(), edge.getVertex2()))
        		edgeList.remove(edge);
        		
        		density = calculateCurrentDensity();
    		}
    	}
    	
    	
    }
    
    
    public static double calculateCurrentDensity() 
    {	
    	double truncDouble;
    	double currentDensity;
    	double numOfPossibleEdges;
    	
    	int v = vertexList.size();
    	int e = edgeList.size();
    	
    	System.out.println("v = " + v + "  e = " + e);
    	
    	numOfPossibleEdges = v * (v - 1)/2;
    	currentDensity = e / numOfPossibleEdges;
		
    	truncDouble = new BigDecimal(currentDensity).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		currentDensity = truncDouble;
		
		System.out.println("currentDensity = " + currentDensity);
		return currentDensity;
    }
    


	public static String randomNumberGenerator() 
	{
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		String s = Integer.toString(randomNumber);
		return s;
	}
    

  
    public static boolean edgeExists(String v1, String v2) 
    {
    	for (Edge e: edgeList) {				
    		if (e.getVertex1().equals(v1) && e.getVertex2().equals(v2) 
    				|| e.getVertex1().equals(v2) && e.getVertex2().equals(v1)){ 
    			System.err.println("Edge Already Exists!");
    			return true;
    		}
    	}
    	return false;
    }
    
    
	
	public static void main(String[] args) throws IOException {
      
		File f = new File("facebook_density.txt");
    	if (f.exists()) {
    		System.out.println("file exists");
    	   f.delete();
    	}
    	
		readFile("facebook_combined.txt", "facebook_density.txt");
		
		
    }   // end of main method
	
	
	
	
	// first read in a subset of facebook_combined.txt
	
	
	
	
}
