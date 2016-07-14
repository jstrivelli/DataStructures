public class Diff{

	public static Node difference(Node frontL1, Node frontL2){
		if(frontL1 == null || frontL2 == null){
			return frontL1;
		}
		Node first = null, last = null, tmp1 = frontL1, tmp2 = frontL2;
		while(tmp1 != null){
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
}
// where is your node.java file