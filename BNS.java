public class BNS{


public static ArrayList<String> (String [] list, String prefix){
	ArrayList<String> result;
	int hi = list.length - 1;
	int lo = 0;
	int mid = (hi + lo)/2;
	while(!lo > hi){
	  if(list[mid].startsWith(prefix)){
	  	while(list[mid].startsWith(prefix)){
	  		mid -= 1;
	  	}
	  	while(list[mid.startsWith(prefix)){
	  	    result.add(list[mid]);
	  	    mid += 1;
	  	}

	  }
	  int j = list[mid].compareToIgnoreCase(prefix);
	  if(j > 0){
	  	lo = mid + 1
	  	mid = (hi + lo)/2;
	  }
	  else {
	  	hi = mid - 1
	  	mid = (hi + lo)/2;
	  }

	}
	return result;

}


public static float postfixEvaluate(String expr){
	Stack<Float> stk = new Stack<Float>
	for(int i = 0; i  expr.length(); i++){
		char ch = expr.chatAt(i);
		if(ch == ' ')
			continue;
		if()
	}
}
}