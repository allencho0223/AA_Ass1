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
import java.util.ListIterator;
import java.util.Random;


public class DGV2 {
	
	static List<String> vertexList = new ArrayList<String>();
	static List<Edge> edgeList = new ArrayList<Edge>();

    
	
	
	public static void createDensityFile(String inputFile, String outputFile, double density) throws IOException {

    	BufferedReader br = null;
    	
    	FileWriter fileStream = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fileStream);
		
		try {
    		br = new BufferedReader(new FileReader(inputFile));
		
	    	String line;
	    	String delimiter = " ";
	    	String[] tokens;


	    	while ((line = br.readLine()) != null && vertexList.size() < 50) {  //count is how many lines from facebook_combined.txt we want to read in
	    		
	    		tokens = line.split(delimiter);
	    		Edge edge = new Edge(tokens[0], tokens[1]);
	    		
	    		if (!vertexList.contains(tokens[0])) 
	    			vertexList.add(tokens[0]);
	    		
	    		if (!vertexList.contains(tokens[1])) 
	    			vertexList.add(tokens[1]);
	    		
	    		if (edgeExists(tokens[0], tokens[1]) == false)
	    			edgeList.add(edge);
	    	
	    	}			
		}
		catch (FileNotFoundException ex) {
			System.err.println("File " + inputFile + " not found.");
		}
		
    
    	adjustDensity(density, calculateCurrentDensity());
    	
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
    	if (currentDensity <= targetDensity) 
    	{
    			double density = currentDensity;
	    		for (int i = 0; i < vertexList.size(); i++) 
	    		{
	    			int count = 0;
	    			while (count < vertexList.size()) 
	    			{
	    				edge = new Edge(vertexList.get(i), vertexList.get(count));
	    				if (!edgeExists(edge.getVertex1(), edge.getVertex2()))
	    						edgeList.add(edge);
	    			
	    				density = calculateCurrentDensity();
	    				if (density == targetDensity)
	    						break;
	    				
	    				count++;
	    			}
	    			
	    		}
    	}
    	
   
    }
    
    
    public static double calculateCurrentDensity() 
    {	
    	double truncDouble, currentDensity, numOfPossibleEdges;
    	
    	int v = vertexList.size();
    	int e = edgeList.size();
    	
    	numOfPossibleEdges = v * (v - 1);
    	currentDensity = e / numOfPossibleEdges;
		
    	truncDouble = new BigDecimal(currentDensity).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		currentDensity = truncDouble;
		
		System.out.println("currentDensity = " + currentDensity);
		return currentDensity;
    }
      

	public static String randomNumberGenerator(int limit) 
	{
		Random random = new Random();
		int randomNumber = random.nextInt(limit);
		String s = Integer.toString(randomNumber);
		return s;
	}
    
	public static String arrayRandom() 
	{
		String newVertex = vertexList.get(new Random().nextInt(vertexList.size()));
		return newVertex;
	}
	

    public static boolean edgeExists(String v1, String v2) 
    {
    	for (Edge e: edgeList) 
    	{				
    		if (e.getVertex1().equals(v1) && e.getVertex2().equals(v2) /*|| e.getVertex1().equals(v2) && e.getVertex2().equals(v1)*/)
    		{ 
    			//System.err.println("Edge Exists!");
    			return true;
    		}
    	}
    	return false;
    }
    
	
    public static boolean edgeReverseExists(String v1, String v2) 
    {
    	for (Edge e: edgeList) 
    	{				
    		if (e.getVertex1().equals(v1) && e.getVertex2().equals(v2) || e.getVertex1().equals(v2) && e.getVertex2().equals(v1))
    		{ 
    			//System.err.println("Edge Exists!");
    			return true;
    		}
    	}
    	return false;
    }
	
	 public static void createCommandsFile(String outputFile , int command) throws IOException 
	    {
		 	BufferedWriter bw = null;
	    	try{
	    		FileWriter fileStream = new FileWriter(outputFile);
	    		bw = new BufferedWriter(fileStream);
	    		
	    		switch (command) 
	    		{
		    		case 1: growFriendshipGraphCommands(bw);
		    			break;
		    		case 2: requestNSPDCommands(bw);
		    			break;
		    		case 3: shrinkFriendshipGraphCommands(bw); 
		    			break;
	    		}
	    		
			
	    	}catch (IOException ex) {
				System.err.println("Cannot open file " + outputFile);

			}
			
	    	bw.close();
			
	    }
	
	
	public static void growFriendshipGraphCommands(BufferedWriter bw) throws IOException 
	{
		System.out.println("grow");
		Edge edge = null;	
		int countV = 0;
		int countE = 0;
		String vertex;
		String addVertex = "AV";
		String addEdge = "AE";
		
		while (countV < 100) {
			vertex = randomNumberGenerator(600);
			if (!vertexList.contains(vertex)) {
			
				bw.write(addVertex + " " + vertex);
				bw.newLine();
				countV++;
			} 
		}
		
		while (countE < 100) {
			edge = new Edge(arrayRandom(), arrayRandom());
    		if (!edgeReverseExists(edge.getVertex1(), edge.getVertex2())) {
    			bw.write(addEdge + " " + edge.getVertex1() + " " + edge.getVertex2());
    		
    			bw.newLine();
    			countE++;
    		}
    		
    	 
		}
	}
	
	public static void requestNSPDCommands(BufferedWriter bw) throws IOException 
	{
		System.out.println("NSPD");
		
		Edge edge = null;	
		int countN = 0;
		int countS = 0;
		String vertexN, SV1, SV2;
		String neighbours = "N";
		String spd = "S";
		
		while (countN < 100) {
			vertexN = arrayRandom();
			if (vertexList.contains(vertexN)) {
				bw.write(neighbours + " " + vertexN);
				bw.newLine();
				countN++;
			} 
		}
		
		while (countS < 100) {
			SV1 = arrayRandom();
			SV2 = arrayRandom();
    		if (vertexList.contains(SV1) && vertexList.contains(SV2)) {
    			bw.write(spd + " " + SV1 + " " + SV2);
    			bw.newLine();
    			countS++;
    		}
    		
    	 
		}
		
		

	}
	
	public static void shrinkFriendshipGraphCommands(BufferedWriter bw) throws IOException 
	{
		System.out.println("shrink");
		
		Edge edge = null;	
		int countV = 0;
		int countE = 0;
		String vertex;
		String addVertex = "RV";
		String addEdge = "RE";
		
		while (countV < 100) {
			vertex = arrayRandom();
			if (vertexList.contains(vertex)) {
				bw.write(addVertex + " " + vertex);
				bw.newLine();
				countV++;
			} 
		}
		
		while (countE < 100) {
			edge = new Edge(arrayRandom(), arrayRandom());
    		if (edgeReverseExists(edge.getVertex1(), edge.getVertex2())) {
    			bw.write(addEdge + " " + edge.getVertex1() + " " + edge.getVertex2());
    			bw.newLine();
    			countE++;
    		}
    	 
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
	      
		File f = new File("facebook_density.txt");
    	if (f.exists()) {
    		System.out.println("file exists");
    	   f.delete();
    	}
    	
		createDensityFile("facebook_combined.txt", "facebook_density.txt", 0.5);  
		
		createCommandsFile("scenario1.txt", 1);
		createCommandsFile("scenario2.txt", 2);
		createCommandsFile("scenario3.txt", 3);
		
		System.out.println("end of main method");
    }   // end of main method
	
	
}
