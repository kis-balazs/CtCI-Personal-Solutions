package Trees_and_Graphs;

import java.util.LinkedList;

public class Q1_Route_between_Nodes {
	/*
	 * graph, directed;
	 */

	// simple graph traversal : see if the other one is found; use the 3 possible
	// mark solution : Unvisited, Visiting, Visited; use BFS; DFS easier to
	// implement, BFS can also be used for shortest path, DFS going deep and than
	// coming back
	enum State {
		Visited, Unvisited, Visiting
	};

	// BFS : O(V + E)
	static boolean search(Graph g, Node start, Node end) {
		if (start == end)
			return false;

		// init state
		LinkedList<Node> q = new LinkedList<Node>();
		for (Node u : g.getNodes()) {
			u.state = State.Unvisited;
		}
		start.state = State.Visiting;
		q.add(start);
		// BFS	
		Node u;
		while (!q.isEmpty()) {
			u = q.removeFirst();
			if (u != null) {
				for (Node v : u.getAdjacent()) {
					if (v.state == State.Unvisited) {
						if (v == end) {
							return true;
						} else {
							v.state = State.Visiting;
							q.add(v);
						}
					}
				}
				u.state = State.Visited;
			}
		}
		return false;
	}

	public static Graph createGraph() {
		Graph newGraph = new Graph();
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);

		int i;
		for (i = 0; i < 6; i++) {
			newGraph.addNode(temp[i]);
		}
		return newGraph;
	}

	public static void main(String[] args) {
		Graph g = createGraph();
		Node[] nodes = g.getNodes();

		Node start = nodes[3];
		Node end = nodes[5];

		System.out.println(search(g, start, end));
	}
}
