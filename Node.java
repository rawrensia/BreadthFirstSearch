public class Node {
	
	    int key;
	    Node parent;
	    String location;
	 
	    public Node(int item)
	    {
	        key = item;
	        parent = null;
	        location = "";
	    }
	    public Node (int item, String loc) {
		    	key = item;
		    	parent = null;
		    	location = loc;
	    	
	    }
	
}
