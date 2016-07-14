package friendships;

import java.io.FileNotFoundException;


import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Graph {
	HashMap<String, Person> people = new HashMap<String, Person>();
	public String graphInfo;

	
	public Graph(String graphInfo) throws FileNotFoundException{
		
		this.graphInfo = graphInfo;
		Scanner scan = new Scanner(System.in);
		try {
			scan = new Scanner(new File(graphInfo));
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		int a = Integer.parseInt(scan.nextLine());
		
		for(int i = 0; i < a; i++){
			String line = scan.nextLine();
			String name = line.substring(0, line.indexOf('|'));
			
			int finder  = line.indexOf('|');		
			String school = "";
			
			if(line.charAt(finder + 1) == 'y' && line.charAt(finder+ 2) == '|'){
				school = line.substring(finder + 1 + 1 + 1);
			}
			else if(line.charAt(line.length() - 1) == 'n' && line.charAt(line.length() - 2) == '|'){
				school = "";
			}
			Person info = new Person(name, school);
			people.put(name, info);
			
			
		}
		while(scan.hasNext()){
			String firstName = "";
			String secondName = "";
			String line = scan.nextLine();	
			firstName = line.substring(0, line.indexOf('|'));
			secondName = line.substring(line.indexOf('|') + 1);
			Person firstPerson = people.get(firstName);
			Person secondPerson = people.get(secondName);
			firstPerson.addFriend(secondPerson);
			secondPerson.addFriend(firstPerson);	
		}
		
		
	}
	
	public Graph subGraph(String school){
//		Graph subgraph = new Graph()
		//IMPLEMENTATION STARTS HERE
		//THIS IS SO THE PROGRAM WILL RUN
		return null;
	}
	
	public String getShortestPath(String firstName, String firstSchool, String secondName, String secondSchool){
		//IMPLEMENTATION STARTS HERE
		//THIS IS SO THE PROGRAM WILL RUN
		return null;
	}
	
	public String getCliques(String school){
		//IMPLEMENTATION STARTS HERE
		//THIS IS SO THE PROGRAM WILL RUN
		return null;
	}
	public String getConnectors(){
		//IMPLEMENTATION STARTS HERE
		//THIS IS SO THE PROGRAM WILL RUN
		return null;
	}
	
}
