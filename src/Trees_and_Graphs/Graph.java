package Trees_and_Graphs;

public class Graph {
	public static int MAX_VERTICES = 6;
	private Node vert[];
	public int count;
	
	public Graph() {
		vert = new Node[MAX_VERTICES];
		count = 0;
	}
	
	public void addNode(Node newNode) {
		if (count < vert.length) {
			vert[count] = newNode;
			count++;
		} else {
			System.out.println("FULL GRAPH!");
		}
	}
	
	public Node[] getNodes() {
		return vert;
	}
}
