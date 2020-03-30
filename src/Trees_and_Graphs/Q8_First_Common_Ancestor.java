package Trees_and_Graphs;

public class Q8_First_Common_Ancestor {
	/*
	 * 2 nodes : first common ancestor, not necessarily BST; BST : modify the find
	 * algorithm, see difference; not necessarily : link to parents??
	 */

	// version A -- each node has link to parents; get to the same, upper level of
	// the 2 nodes, trace both until intersection;
	// O(d) -- depth of deeper node
	public static TreeNode commonAncestorA(TreeNode p, TreeNode q) {
		int delta = depth(p) - depth(q);

		TreeNode first = delta > 0 ? q : p; // upper
		TreeNode second = delta > 0 ? p : q; // deeper
		second = goUpBy(second, Math.abs(delta));

		// until intersection
		while (first != second && first != null && second != null) {
			first = first.parent;
			second = second.parent;
		}
		return first == null || second == null ? null : first;

	}

	public static TreeNode goUpBy(TreeNode node, int delta) {
		while (delta > 0 && node != null) {
			node = node.parent;
			delta--;
		}
		return node;
	}

	public static int depth(TreeNode node) {
		int depth = 0;
		while (node != null) {
			node = node.parent;
			depth++;
		}
		return depth;
	}

	// version B -- similar to vA, check p's path, and check if any nodes cover q;
	// first that covers is the answer; need to check only nodes uncovered, the ones
	// below x's sibling; each iteration sibling gets to set to the old parent's
	// sibling node and parent : set to parent
	// O(t) -- t : size of subtree for the common ancestor
	public static TreeNode commonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) {
			return null;
		} else if (covers(p, q)) {
			return p;
		} else if (covers(q, p)) {
			return q;
		}

		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while (!covers(sibling, q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		return parent;
	}

	public static boolean covers(TreeNode root, TreeNode node) {
		if (root == null)
			return false;
		if (root == node)
			return true;
		return covers(root.left, node) || covers(root.right, node);
	}

	public static TreeNode getSibling(TreeNode node) {
		if (node == null || node.parent == null)
			return null;

		TreeNode parent = node.parent;

		return parent.left == node ? parent.right : parent.left;
	}

	// version C -- without link to parents; look at the path : in the moment when
	// either of the nodes "derived" from the common path, the ancestor is found
	// O(n)
	public static TreeNode commonAncestorC(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q)) {
			return null;
		}
		return ancestorHelp(root, p, q);
	}

	public static TreeNode ancestorHelp(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;

		boolean qIsOnLeft = covers(root.left, q);
		boolean pIsOnLeft = covers(root.left, p);
		if (pIsOnLeft != qIsOnLeft)
			return root;

		TreeNode childSide = pIsOnLeft ? root.left : root.right;
		return ancestorHelp(childSide, p, q);
	}

	// version D -- covers works very much, search for q and p once; use function :
	// see if q/p/none are in the subtree, else give the common ancestor; if from a
	// node n both subtrees give null, the ancestor is n + checking existence or : q
	// is child of p OR p is in tree and q not(or other way around)
	// O(n)
	public static class Result {
		public TreeNode node;
		public boolean isAncestor;

		public Result(TreeNode n, boolean isAnc) {
			node = n;
			isAncestor = isAnc;
		}
	}

	public static TreeNode commonAncestorD(TreeNode root, TreeNode p, TreeNode q) {
		Result r = commonAncestorHelper(root, p, q);
		if (r.isAncestor)
			return r.node;
		return null;
	}

	public static Result commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return new Result(null, false);
		if (root == p && root == q)
			return new Result(root, true);

		Result rl = commonAncestorHelper(root.left, p, q);
		if (rl.isAncestor)
			return rl;

		Result rr = commonAncestorHelper(root.right, p, q);
		if (rr.isAncestor)
			return rr;

		if (rl.node != null && rr.node != null) {
			return new Result(root, true);
		} else if (root == p || root == q) {
			// currently at p/q, also found one of them in a subtree, this is truly the
			// ancestor and the flag should be true
			boolean isAncestor = rl.node != null || rr.node != null;
			return new Result(root, isAncestor);
		} else {
			return new Result(rl.node != null ? rl.node : rr.node, false);
		}
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);
		TreeNode first = root.find(1);
		TreeNode second = root.find(10);

		// TreeNode ancestor = commonAncestorA(first, second);
		// TreeNode ancestor = commonAncestorB(root, first, second);
		//TreeNode ancestor = commonAncestorC(root, first, second);
		TreeNode ancestor = commonAncestorD(root, first, second);
		System.out.println(ancestor.data);

	}
}
