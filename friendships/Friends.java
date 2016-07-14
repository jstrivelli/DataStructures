package friendships;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Jesse Strivelli
//Carlos Lara

public class Friends {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String [] args){

		System.out.println("Enter File Name: ");
		String graphInfo = scan.nextLine();
		Graph abc = null;
		
		try{
			 abc = new Graph(graphInfo);
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
			System.exit(1);
		}
		
		
		int option = getChoice();
		while(option != 5){
			String school;
			switch(option)
			{
			case 1:
				System.out.println("Enter school name: ");
				school = scan.next();
				System.out.println(abc.subGraph(school));
				break;
			case 2:
				System.out.println("Enter the first person's name: ");
				String name1 = scan.next();
				System.out.println("Enter the first person's school: ");
				String school1 = scan.next();
				System.out.println("Enter the second person's name: ");
				String name2 = scan.next();
				System.out.println("Enter the second person's school: ");
				String school2 = scan.next();
				System.out.println(abc.getShortestPath(name1, school1, name2, school2));
				break;
			case 3: 
				System.out.println("Enter the school of the cliques you want to see: ");
				school = scan.next();
				System.out.print(abc.getCliques(school));
				break;
			case 4:
				System.out.println(abc.getConnectors());
			case 5:
				System.exit(0);
				break;

			}
			option = getChoice();
		}

	}
		
	
	static int getChoice(){
		System.out.println("\nYou must choose");
		System.out.println("(1) Subgraph: Students at a school");
		System.out.println("(2) Shortest Path: Intro Chain");
		System.out.println("(3) Connected Islands: Cliques");
		System.out.println("(4) Connectors: Friends Who Keep Friends Together");
		System.out.println("(5) Quit: Terminates Program \n");
		System.out.println("Enter choice: ");
		int answer = scan.nextInt();
		while(answer > 5 || answer < 1){
			System.out.println("You must enter a valid number!");
			answer = scan.nextInt();
		} 
		return answer;
	}
}
