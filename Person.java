package friendships;

import java.util.Iterator;
import java.util.LinkedList;


public class Person {
    public String name;
    public String school;
    public LinkedList<Person> front;
    public Person(String name, String school){
        this.name = name;
        this.school = school;
        front = new LinkedList<Person>();
    }
    
    public void addFriend(Person friend){
        front.add(friend);

    }
    
    public void deleteFriend(Person toDelete){
        front.remove(toDelete);
     }
        
        
//        while(front != null && front.equals(friend)){
//            front = front.front;
//        }
//        Person tmp = front;
//        if(tmp == null)    return;
//        while(tmp.front != null){
//            if(tmp.front.equals(friend)){
//                tmp.front = tmp.front.front;    
//                
//            }else{
//                tmp = tmp.front;
//            }
//        }
    
    public boolean equals(Person a){
        if(a.name.equals(this.name) && a.school.equals(this.school)){
            return true;
        }
        return false;
    }
    public String toString(){
    	String a = "";
    	if(school.equals("")){
    		a = name + "|n|";
    	}
    	else
         a = name + "|y|" + school + " ";
//        Iterator<Person> iter = front.iterator();
//        while(iter.hasNext()){
//        	Person b = iter.next();
//        }
        return a;
        
    }
    
}