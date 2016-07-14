public class Node
{
	public int data;
	public Node next;
	
	public Node(int data, Node next)
	{
		this.data = data;
		this.next = next;
	}

	// This method allows us to pass a Node object into System.out.println(node) and get 1 -> 2 -> 3 -> null
	public String toString()
	{
		return "" + data + " -> " + next;
	}

	// This is the method that calculates the difference of Linked list one to Link list 2
	public static Node difference (Node frontL1, Node frontL2) 
	{
		if(frontL1 == null || frontL2 == null){
			return frontL1;
		}
		Node first = null, last = null, tmp1 = frontL1, tmp2 = frontL2;
		while(tmp1 != null){
				if(tmp2 == null)
				{
					last.next = tmp1;
					break;
				}	
				if(tmp1.data == tmp2.data){
					tmp1 = tmp1.next;
					tmp2 = tmp2.next;
				}
				else if(tmp1.data >tmp2.data)
					tmp2 = tmp2.next;
				else{
					Node difference = new Node(tmp1.data, null);
					if(last != null){
						last.next = difference;
					}
					else{
						first = difference;
					}
					last = difference;
					tmp1 = tmp1.next;
				}
			}	
		return first;
	}
	// Now we need a main to test it
	public static void main(String [] args)
	{
		Node l1 = new Node(3, new Node(9, new Node(12, new Node(15, new Node(21, null)))));
        Node l2 = new Node(2, new Node(3, new Node(6, new Node(12, new Node (19, null)))));
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(difference(l1, l2));
	}
}
