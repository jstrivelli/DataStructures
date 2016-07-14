package friendships; 
 
import java.io.FileNotFoundException; 
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Iterator; 
import java.util.Map; 
import java.util.Scanner; 
import java.util.Set;
import java.io.File; 
import java.util.LinkedList; 
import java.util.ArrayList; 
 
 
public class Graph { 
    HashMap<String, Person> people = new HashMap<String, Person>(); 
    public String graphInfo; 
    public Graph(){} 
    public Graph(String graphInfo) throws FileNotFoundException{ 
        this.graphInfo = graphInfo; 
        buildGraph(); 
    } 
    public void buildGraph() throws FileNotFoundException{ 
        Scanner scan = null; 
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
    public Graph subGraph(String school)throws FileNotFoundException{ 
        Graph subGraph = null; 
        try{ 
             subGraph = new Graph(graphInfo); 
        } 
        catch(FileNotFoundException e){ 
            System.out.println("File Not Found"); 
            System.exit(1); 
        } 
        for (Person p : people.values()) { 
            if(!p.school.equals(school)){ 
                subGraph.people.remove(p.name); 
                 for(Person a: people.values()){
                	 subGraph.people.remove(p.name);
                 }
                for(Person q: subGraph.people.values()){ 
                    q.deleteFriend(p); 
                } 
            }              
        } 
        return subGraph; 
    } 
     
    public LinkedList getShortestPath(String firstName, String secondName){ 
        ArrayList<Person> bfs = new ArrayList<Person>(); 
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>(); 
        ArrayList<Integer> currIn = new ArrayList<Integer>(); 
        LinkedList<Person> a = new LinkedList<Person>(); 
        bfs.add(people.get(firstName)); 
        visited.put(firstName, true); 
        currIn.add(-1); 
        int cwi = -1; 
        while(!bfs.isEmpty()){ 
            cwi++; 
            Person tmp = bfs.get(cwi); 
            for(Person curr: tmp.front){ 
                Person friend = curr; 
                if(!visited.containsKey(friend.name)) 
                visited.put(friend.name, false); 
                if(visited.get(friend.name) == false){ 
                    bfs.add(people.get(friend.name)); 
                    currIn.add(cwi); 
                } 
            } 
            visited.put(tmp.name, true); 
            if(tmp.name.equals(secondName)){ 
                while(true){ 
                    a.add(bfs.get(cwi)); 
                    cwi = currIn.get(cwi); 
                    if(cwi == -1) return a; 
                } 
            } 
        } 
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
    public String toString(){ 
        String s =""; 
        for(Person a: people.values()){ 
            s = s + a.toString() +"\n";
        }
        // Because we're lazy
        Set<String> addedStrings = new HashSet<String>();
        for(Person p: people.values()){
        	for(Person tmp: p.front){
        		String toAdd = tmp.name + "|" + p.name;
        		String toAddReverse = p.name + "|" + tmp.name;
        		if (!(addedStrings.contains(toAdd) || addedStrings.contains(toAddReverse))) {
        			s = s + toAdd +  "\n";
        			addedStrings.add(toAdd);
        		}
        	}
        }
        return s; 
    } 
     
}
