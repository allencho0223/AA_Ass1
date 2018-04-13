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
		
		int count = 0;
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
    	if (currentDensity <= targetDensity) 
    	{
    		double density = currentDensity;
    		while (density != targetDensity) 
    		{	
	    		
	    		for (int i = 0; i < vertexList.size(); i++) 
	    		{
	    			int count = 0;
	    			while (count < vertexList.size()) 
	    			{
	    			edge =  new Edge(vertexList.get(i), vertexList.get(count));
	    			
	    			if (!edgeExists(edge.getVertex1(), edge.getVertex2()))
    					edgeList.add(edge);
	    			
	    			density = calculateCurrentDensity();
	    			count++;
	    			}
	    			
	    			
	    		}

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
	
	public static String arrayIterator()
	{
		ListIterator<String> it = vertexList.listIterator();
		String newVertex = it.hasNext()?it.next():null;
		return newVertex;
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
    	
    	File f2 = new File("commandsList.txt");
    	if (f2.exists()) {
    		System.out.println("file exists");
    	   f2.delete();
    	}
    	
		createDensityFile("facebook_combined.txt", "facebook_density.txt", 0.9);  
		
	//	createCommandsFile("scenario1.txt", 1);
	//	createCommandsFile("scenario2.txt", 2);
	//	createCommandsFile("scenario3.txt", 3);
	//	
		
    }   // end of main method
	
	
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
			edge = new Edge(randomNumberGenerator(300), randomNumberGenerator(300));
    		if (!edgeExists(edge.getVertex1(), edge.getVertex2()) && vertexList.contains(edge.getVertex1())) {
    			bw.write(addEdge + " " + edge.getVertex1() + " " + edge.getVertex2());
    			bw.newLine();
    			countE++;
    		}
    		
    	 
		}
	}
	
	public static void requestNSPDCommands(BufferedWriter bw) throws IOException 
	{
		System.out.println("NSPD");
		//Function 2: In scenario 2, you would generate random “N x” and “S x y” 
		//commands where vertices x and y both exist in the graph. 
		
		Edge edge = null;	
		int countN = 0;
		int countS = 0;
		String vertex;
		String neighbours = "N";
		String spd = "S";
		
		while (countN < 100) {
			vertex = randomNumberGenerator(600);
			if (vertexList.contains(vertex)) {
				bw.write(neighbours + " " + vertex);
				bw.newLine();
				countN++;
			} 
		}
		
		while (countS < 100) {
			edge = new Edge(randomNumberGenerator(500), randomNumberGenerator(50));
    		if (edgeExists(edge.getVertex1(), edge.getVertex2())) {
    			bw.write(spd + " " + edge.getVertex1() + " " + edge.getVertex2());
    			bw.newLine();
    			countS++;
    		}
    		
    	 
		}
		
		

	}
	
	public static void shrinkFriendshipGraphCommands(BufferedWriter bw) 
	{
		System.out.println("shrink");
		//Function 3: In scenario 3, you would randomly generate “RE x y” and “RV z” 
		//where x, y, z are vertices that exist in the graph and you know that there is an edge present between x and y. 

	}
	
}
