package Trees_and_Graphs;

public class Node {
	private Node adjacent[];
	public int adjacentNo;
	private String vertex;
	public Q1_Route_between_Nodes.State state;
	
	public Node(String vertex, int adjacentLength) {
		this.vertex = vertex;
		adjacentNo = 0;
		adjacent = new Node[adjacentLength];
	}
	
	public void addAdjacent(Node newNode) {
		if (adjacentNo < adjacent.length) {
			this.adjacent[adjacentNo] = newNode;
			adjacentNo++;
		} else {
			System.out.println("Can't add adjacent");
		}
	}
	
	public Node[] getAdjacent() {
		return adjacent;
	}
	
	public String getVertex() {
		return vertex;
	}
	
}
