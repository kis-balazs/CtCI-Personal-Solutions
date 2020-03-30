package Trees_and_Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Q7_Build_Order {
	/*
	 * dependencies : ProjectA2 depends on ProjectA1 -- TOPOLOGICAL SORT
	 */

	// version A -- add to build order each time the ones with no incoming edges;
	// after doing this, update, and release those which are added and have edges
	// coming out from them, until done; if nodes remain, error;
	// O(D + P) -- D : dependencies, P : projectAs; edge removal
	public static class ProjectA {
		private ArrayList<ProjectA> children = new ArrayList<ProjectA>();
		private HashMap<String, ProjectA> map = new HashMap<String, ProjectA>();
		private String name;
		private int dependencies = 0;

		public ProjectA(String n) {
			name = n;
		}

		public void addNeighbor(ProjectA name) {
			if (!map.containsKey(name.getName())) {
				children.add(name);
				map.put(name.getName(), name);
				name.incrementDependencies();
			}
		}

		public void incrementDependencies() {
			dependencies++;
		}

		public void decrementDependencies() {
			dependencies--;
		}

		public String getName() {
			return name;
		}

		public ArrayList<ProjectA> getChildren() {
			return children;
		}

		public int getDependencies() {
			return dependencies;
		}
	}

	public static class GraphA {
		private ArrayList<ProjectA> nodes = new ArrayList<ProjectA>();
		private HashMap<String, ProjectA> map = new HashMap<String, ProjectA>();

		public ProjectA getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				ProjectA node = new ProjectA(name);
				nodes.add(node);
				map.put(name, node);
			}
			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			ProjectA start = getOrCreateNode(startName);
			ProjectA end = getOrCreateNode(endName);
			start.addNeighbor(end);
		}

		public ArrayList<ProjectA> getNodes() {
			return nodes;
		}
	}

	/* find correct build order */
	public static ProjectA[] findBuildOrderA(String[] projectAs, String[][] dependencies) {
		GraphA g = buildGraphA(projectAs, dependencies);
		return orderProjectAsA(g.getNodes());
	}

	// build graph by adding the edge (a, b) if b is dep. by a; pair is listed in
	// "build order";
	public static GraphA buildGraphA(String[] projectAs, String[][] deps) {
		GraphA g = new GraphA();
		for (String ProjectA : projectAs) {
			g.getOrCreateNode(ProjectA);
		}

		for (String[] dep : deps) {
			String first = dep[0];
			String second = dep[1];
			g.addEdge(first, second);
		}
		return g;
	}

	// ret. list of projectAs in correct build order
	public static ProjectA[] orderProjectAsA(ArrayList<ProjectA> projectAs) {
		ProjectA[] order = new ProjectA[projectAs.size()];

		// add roots
		int endOfList = addNonDependent(order, projectAs, 0);

		int toBeProcessed = 0;
		while (toBeProcessed < order.length) {
			ProjectA current = order[toBeProcessed];

			// no rem. projectAs with zero dependency => circular dependency
			if (current == null)
				return null;
			// remove itself as dependency
			ArrayList<ProjectA> children = current.getChildren();
			for (ProjectA child : children) {
				child.decrementDependencies();
			}

			// add children with no dependencies(incoming edges)
			endOfList = addNonDependent(order, children, endOfList);
			toBeProcessed++;
		}
		return order;
	}

	// insert projectAs with 0 dependencies(incoming edges) into array
	public static int addNonDependent(ProjectA[] order, ArrayList<ProjectA> projectAs, int from) {
		for (ProjectA ProjectA : projectAs) {
			if (ProjectA.getDependencies() == 0) {
				order[from] = ProjectA;
				from++;
			}
		}
		return from;
	}

	// version B -- use DFS : terminal nodes can get into the build order; children
	// of a get in the order after a; once done with those, start over one DFS,
	// until no more nodes; use State to avoid cycles;
	// O(D + P)
	public static class ProjectB {
		private ArrayList<ProjectB> children = new ArrayList<ProjectB>();
		private HashMap<String, ProjectB> map = new HashMap<String, ProjectB>();
		private String name;

		public enum State {
			COMPLETE, PARTIAL, BLANK
		};

		public State state = State.BLANK;

		public ProjectB(String n) {
			name = n;
		}

		public void addNeighbor(ProjectB name) {
			if (!map.containsKey(name.getName())) {
				children.add(name);
				map.put(name.getName(), name);
			}
		}

		public State getState() {
			return state;
		}

		public void setState(State s) {
			state = s;
		}

		public String getName() {
			return name;
		}

		public ArrayList<ProjectB> getChildren() {
			return children;
		}
	}

	public static class GraphB {
		private ArrayList<ProjectB> nodes = new ArrayList<ProjectB>();
		private HashMap<String, ProjectB> map = new HashMap<String, ProjectB>();

		public ProjectB getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				ProjectB node = new ProjectB(name);
				nodes.add(node);
				map.put(name, node);
			}
			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			ProjectB start = getOrCreateNode(startName);
			ProjectB end = getOrCreateNode(endName);
			start.addNeighbor(end);
		}

		public ArrayList<ProjectB> getNodes() {
			return nodes;
		}
	}

	public static GraphB buildGraphB(String[] projectBs, String[][] deps) {
		GraphB g = new GraphB();
		for (String ProjectB : projectBs) {
			g.getOrCreateNode(ProjectB);
		}

		// init
		for (String[] dep : deps) {
			String first = dep[0];
			String second = dep[1];
			g.addEdge(first, second);
		}
		return g;
	}

	public static Stack<ProjectB> findBuildOrderB(String[] projectBs, String[][] dependencies) {
		GraphB g = buildGraphB(projectBs, dependencies);
		return orderProjectAsB(g.getNodes());
	}

	public static Stack<ProjectB> orderProjectAsB(ArrayList<ProjectB> projectBs) {
		Stack<ProjectB> stack = new Stack<ProjectB>();
		for (ProjectB project : projectBs) {
			if (project.getState() == ProjectB.State.BLANK) {
				if (!doDFS(project, stack))
					return null;
			}
		}
		return stack;
	}

	public static boolean doDFS(ProjectB project, Stack<ProjectB> stack) {
		// cycle
		if (project.getState() == ProjectB.State.PARTIAL) {
			return false;
		}

		if (project.getState() == ProjectB.State.BLANK) {
			project.setState(ProjectB.State.PARTIAL);
			ArrayList<ProjectB> children = project.getChildren();
			for (ProjectB child : children) {
				if (!doDFS(child, stack)) {
					return false;
				}
			}
			project.setState(ProjectB.State.COMPLETE);
			stack.push(project);
		}
		return true;
	}

	public static String[] convertToStringList(Stack<ProjectB> projects) {
		String[] buildOrder = new String[projects.size()];
		for (int i = 0; i < buildOrder.length; i++) {
			buildOrder[i] = projects.pop().getName();
		}
		return buildOrder;
	}

	public static String[] buildOrderWrapper(String[] projects, String[][] dependencies) {
		Stack<ProjectB> buildOrder = findBuildOrderB(projects, dependencies);
		if (buildOrder == null)
			return null;
		String[] buildOrderString = convertToStringList(buildOrder);
		return buildOrderString;
	}

	public static void main(String[] args) {
		String[] projectAs = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		String[][] dependenciesA = { { "a", "b" }, { "b", "c" }, { "a", "c" }, { "d", "e" }, { "b", "d" }, { "e", "f" },
				{ "a", "f" }, { "h", "i" }, { "h", "j" }, { "i", "j" }, { "g", "j" } };

		ProjectA[] buildOrderA = findBuildOrderA(projectAs, dependenciesA);

		String[] projectBs = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		String[][] dependenciesB = { { "a", "b" }, { "b", "c" }, { "a", "c" }, { "d", "e" }, { "b", "d" }, { "e", "f" },
				{ "a", "f" }, { "h", "i" }, { "h", "j" }, { "i", "j" }, { "g", "j" } };
		// create stack and after create the string
		String[] buildOrderB = buildOrderWrapper(projectBs, dependenciesB);

		// A
		System.out.println("Version A :");
		if (buildOrderA == null) {
			System.out.println("Circular Dependency.");
		} else {
			for (ProjectA s : buildOrderA) {
				System.out.print(s.name + " ");
			}
		}
		System.out.println("\nVersion B :");
		if (buildOrderB == null) {
			System.out.println("Circular Dependency.");
		} else {
			for (String s : buildOrderB) {
				System.out.print(s + " ");
			}
		}
	}
}
