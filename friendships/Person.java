package friendships;

public class Person {
	public String name;
	public String school;
	public Node front;
	public int length; 
	public Person(String name, String school){
		this.name = name;
		this.school = school;
		length = 1;
	}
	
	public void addFriend(Person friend){
		front = new Node(friend, front);
		length++; 
	}
	
	public void deleteFriend(Person friend){
		Node tmp = front;
		while(tmp.next != null){
			if(tmp.next.data.equals(friend)){
				tmp.next = tmp.next.next;
				return;
			}
			tmp = tmp.next;
		}
		length--;
	}
	public boolean equals(Person a){
		if(a.name.equals(name) && a.school.equals(school)){
			return true;
		}
		return false;
	}
	public int getLength(){
		return length;
	}
	
}
