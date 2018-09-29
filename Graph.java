import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

	//No. of vertices
	private int V;

	//Adjacency Lists
	private LinkedList<Node> adj[];

	public Graph(int V) {
		this.V = V;
		adj = new LinkedList[V];
		for (int i = 0; i < V; ++i) {
			adj[i] = new LinkedList<Node>();
		}
	}

	//Add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(new Node(w));
		adj[w].add(new Node(v));
	}

	public void addEdge(int v, String from, int w, String to) {
		adj[v].add(new Node(w,to));
		adj[w].add(new Node(v,from));
	}

	//BFS traversal from a given source
	public void BFS(Node source, Node destination) {

		//Mark all the vertices as not visited (false)
		boolean visited[] = new boolean[V];

		//Create a queue for BFS
		LinkedList<Node> queue = new LinkedList<Node>();

		//Mark the current node as visited and enqueue it
		visited[source.key] = true;
		queue.add(source);

		while (queue.size() != 0) {

			// Dequeue a vertex from queue
			source = queue.poll();

			// Get all adjacent vertices of the dequeued vertex source
			Iterator<Node> iterator = adj[source.key].listIterator();

			// If an adjacent vertex has not been visited, then mark it visited and enqueue it
			while (iterator.hasNext()) {
				Node n = iterator.next();

				if (!visited[n.key]) {
					visited[n.key] = true;
					queue.add(n);
					n.parent=source;
					
					if (n.key == destination.key) {
						printShortestPath(n);
						return;
						}
				}
			}
		}
	}

	
	private void printShortestPath(Node destination) {
		ArrayList arList = new ArrayList();
		Node n = destination;
		
		while (n!=null) {
			arList.add(n.location);
			n = n.parent;
		}
		
		for (int i = arList.size(); i > 0 ; i--) {
			if (arList.get(i-1) != "")  // Only print Nodes with a known location
			System.out.println(arList.get(i-1));
		}
	}

	public static void main(String args[]) {
		Graph g = new Graph(15);

		g.addEdge(0, "Beijing", 1, "Seattle");
		g.addEdge(0,"Beijing", 4, "Singapore");
		g.addEdge(1,"Seattle", 9, "Tokyo");
		g.addEdge(1,"Seattle", 2, "Vancouver");
		g.addEdge(1,"Seattle", 3, "New York");
		g.addEdge(2,"Vancouver", 9, "Tokyo");
		g.addEdge(2,"Vancouver", 3, "New York");
		g.addEdge(3,"New York", 8, "Sydney");
		g.addEdge(3,"New York", 12, "Seoul");
		g.addEdge(4,"Singapore", 5, "Auckland");
		g.addEdge(4,"Singapore", 9, "Tokyo");
		g.addEdge(4, "Singapore", 11, "Dubai");
		g.addEdge(4, "Singapore", 12, "Seoul");
		g.addEdge(4, "Singapore", 8, "Sydney");
		g.addEdge(5, "Auckland", 8, "Sydney");
		g.addEdge(6, "London", 10, "Amsterdam");
		g.addEdge(6, "London", 11, "Dubai");
		g.addEdge(7, "Jakarta", 8, "Sydney");
		g.addEdge(7, "Jakarta", 12, "Seoul");
		g.addEdge(7, "Jakarta", 9, "Tokyo");
		g.addEdge(10, "Amsterdam", 13, "Zurich");
		g.addEdge(10, "Amsterdam", 11, "Dubai");
		g.addEdge(11, "Dubai", 13, "Zurich");
		g.addEdge(11, "Dubai", 14, "Paris");
		g.addEdge(14, "Paris", 6, "London");
		g.addEdge(6, "London", 3, "New York");
		g.addEdge(7, "Jakarta", 4, "Singapore");
		
		System.out.println("Here is the list of source and destination cities:");
		System.out.print(
				"0:Beijing\n" + 
				"1:Seattle\n" +
				"2:Vancouver\n" +
				"3:New York\n" +
				"4:Singapore\n" +
				"5:Auckland\n" +
				"6:London\n" +
				"7:Jakarta\n" +
				"8:Sydney\n" +
				"9:Tokyo\n" +
				"10:Amsterdam\n" +
				"11:Dubai\n" +
				"12:Seoul\n" +
				"13:Zurich\n" +
				"14:Paris\n" +
				"15: Show CPU times for graphs of different sizes and minimum number of non-stop flights\n"+
				"16: Show CPU times for graphs of different sizes and maximum number of non-stop flights\n" +
				"17: Show CPU times for graphs of different sizes and intermediate number of non-stop flights");

		System.out.println ("\nPlease enter the number of source and destination cities (e.g. 4 5):\n\n\n");

		Scanner s = new Scanner(System.in);
		int source = s.nextInt();
		
		if (source == 15) { // Each vertex is only connected to one vertex
			Graph g1 = new Graph(100);
			Graph g2 = new Graph(1000);
			Graph g3 = new Graph(2000);
			
			int numOfNonStopFlight = 0;

			//n = 100:    99 Non-Stop Flights
			for (int i = 0; i<99; i++) {
				g1.addEdge(i,i+1);	
				numOfNonStopFlight++;
				}
			
			long time1 = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k));
					g1.BFS(new Node(k), new Node(i));	// Back and forth
					bfs_count1 = bfs_count1+2;
					}
				}

			time1= System.nanoTime()- time1;
			System.out.println("Average CPU time for n = 100: "+ time1/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			//n = 1000:   999 Non-Stop Flights
			numOfNonStopFlight = 0;
			for (int i = 0; i<999; i++) {
				g2.addEdge(i,i+1);	
				numOfNonStopFlight++;
				}
			
			long time2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));	// Back and forth
					bfs_count2 = bfs_count2+2;
					}
				}
			time2= System.nanoTime()- time2;
			System.out.println("Average CPU time for n = 1000: "+ time2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+numOfNonStopFlight);
			
			//n = 2000:  1999 Non-Stop Flights
			numOfNonStopFlight = 0;
			for (int i = 0; i<1999; i++) {
				g3.addEdge(i,i+1);	
				numOfNonStopFlight++;
				}
			long time3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));	// Back and forth
					bfs_count3 = bfs_count3+2;
					}
				}
			time3= System.nanoTime()- time3;
			System.out.println("Average CPU time for n = 2000: "+ time3/bfs_count3 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
		}
		else if (source ==16) {
			
			Graph g1 = new Graph(100);
			Graph g2 = new Graph(1000);
			Graph g3 = new Graph(2000);
			
			//n = 100:   Number of Non-Stop Flights = 99 + 98 + 97 +... 1
			int numOfNonStopFlight = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				System.out.println(numOfNonStopFlight);
				}
			
			long time1 = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k));
					g1.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count1 = bfs_count1+2;
					}
				}
			time1= System.nanoTime()- time1;
			System.out.println("Average CPU time for n = 100: "+ time1/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);

			//n = 1000:  Number of Non-Stop Flights = 999 + 998 + 997 +... 1
			numOfNonStopFlight = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			long time2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count2 = bfs_count2+2;
					}
				}
			time2 = System.nanoTime()- time2;
			System.out.println("Average CPU time for n = 1000: "+ time2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			
			//n = 2000:      Number of Non-Stop Flights = 1999 + 1998 + 1997 +... 1
			numOfNonStopFlight=0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}

			long time3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k = i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count3 = bfs_count3+2;
					}
				}
			time3= System.nanoTime()- time3;
			System.out.println("Average CPU time for n = 2000: "+ time3/bfs_count3 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			return;
			
		}
		
		
else if (source == 17) {
			
			Graph g1 = new Graph(100);
			Graph g2 = new Graph(1000);
			Graph g3 = new Graph(2000);
			
			//n = 100:
			int numOfNonStopFlight =0;
			for (int i = 0; i<50; i++) {
				for (int k =i+1; k<100; k++) {
					g1.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			
			long time1 = System.nanoTime();
			int bfs_count1 = 0;
			for (int i = 0; i<100; i++) {
				for (int k =i+1; k<100; k++) {
					g1.BFS(new Node(i), new Node(k));
					g1.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count1 = bfs_count1+2;
					}
				}
			time1= System.nanoTime()- time1;
			System.out.println("Average CPU time for n = 100: "+ time1/bfs_count1 + "ns");
			System.out.println("Number of non-stop flights = "+ numOfNonStopFlight);
			
			
			//n = 1000:
			numOfNonStopFlight= 0;
			for (int i = 0; i<500; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			long time2 = System.nanoTime();
			int bfs_count2 = 0;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<1000; k++) {
					g2.BFS(new Node(i), new Node(k));
					g2.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count2 = bfs_count2+2;
					}
				}
			time2= System.nanoTime()- time2;
			System.out.println("Average CPU time for n = 1000: "+ time2/bfs_count2 + "ns");
			System.out.println("Number of non-stop flights = "+numOfNonStopFlight);
			
			//n = 2000:
			numOfNonStopFlight =0 ;
			for (int i = 0; i<1000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.addEdge(i,k);	
					numOfNonStopFlight++;
					}
				}
			long time3 = System.nanoTime();
			int bfs_count3 = 0;
			for (int i = 0; i<2000; i++) {
				for (int k =i+1; k<2000; k++) {
					g3.BFS(new Node(i), new Node(k));
					g3.BFS(new Node(k), new Node(i));	// Two-way
					bfs_count3 = bfs_count3+2;
					}
				}
			time3= System.nanoTime()- time3;
			System.out.println("Average CPU Time for Graph Size 2000: "+ time3/bfs_count3 + "ns"); 
			System.out.println("Number of Non-Stop Flights = "+numOfNonStopFlight);
			return;
}

		int destination = s.nextInt();
		
		String source_string = "";
		switch (source) {
		
		case 0:
			source_string = "Beijing";
			break;
		case 1:
			source_string = "Seattle";
			break;
		case 2:
			source_string = "Vancouver";
			break;
		case 3:
			source_string = "New York";
			break;
		case 4:
			source_string = "Singapore";
			break;
		case 5:
			source_string = "Auckland";
			break;
		case 6:
			source_string = "London";
			break;
		case 7:
			source_string = "Jakarta";
			break;
		case 8:
			source_string = "Sydney";
			break;
		case 9:
			source_string = "Tokyo";
			break;
		case 10:
			source_string = "Amsterdam";
			break;
		case 11:
			source_string = "Dubai";
			break;
		case 12:
			source_string = "Seoul";
			break;
		case 13:
			source_string = "Zurich";
			break;
		case 14:
			source_string = "Paris";
			break;
		}
		
		System.out.println("The shortest flight path: ");
		System.out.println("From: "+ source_string);
		g.BFS(new Node(source), new Node(destination));
	}
}