package linear.copy;

public class StringLL {
	StringNode head;
	int length;
	public StringLL(){
		head = null;
		length = 0;
	}
	public void addToFront(String item){
		StringNode tmp = new StringNode(item);
		tmp.next = head;
		head = tmp;
		length++;
	}

}
