
package linear.copy;


public class LLUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Node addToFront(Node head, int toAdd){
		Node tmp = new Node();
		tmp.data = toAdd;
		tmp.next= head;
		return tmp;
		
	}
	public static boolean search(Node head, int x){
		if(head == null)
			return false;
		
		Node tmp = head;
		while(tmp != null)
		{
			if(tmp.data ==x){
			return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	public static Node delete(Node head, int x){
		
		if(head == null)
			return head;
		
		Node tmp = head;
		Node prev = null;
		if(head.data == x)
			 return head.next;
		while(tmp != null)
		{
			if(tmp.data ==x){
				prev.next = tmp.next;
			}
			prev = tmp;
			tmp = tmp.next;
		}
		return head;
	}
}

