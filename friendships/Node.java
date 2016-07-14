package friendships;

public class Node {
	public Node next;
	public Person data;
	public Node(Person data, Node next){
		this.next = next;
		this.data = data;
	}
}
