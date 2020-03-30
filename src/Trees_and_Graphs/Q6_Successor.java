package Trees_and_Graphs;

public class Q6_Successor {
	/*
	 * "next" node in BST; next has link to parent
	 */

	// leftmost of right subtree, if it does have one; if doesn't have, traverse the
	// subtree; parent of root node : q; root at left of q, traverse q(left ->
	// current -> right); root at right of q, traverse q's subtree; up from q until
	// find an x, not fully traversed <= move from a left node to it's parent;
	// => traverse all the way up before finding a left child; this is when one hits
	// the end of the inorder trav; if already on far right of the tree, no
	// successor : return null
	public static TreeNode succ(TreeNode root) {
		if (root == null)
			return null;

		// found right children : return leftmost of them
		if (root.right != null) {
			return leftMostChild(root.right);
		} else {
			TreeNode q = root;
			TreeNode x = q.parent;
			// go up until we're on left instead of right
			while (x != null && x.left != q) {
				q = x;
				x = x.parent;
			}
			return x;
		}
	}
	
	public static TreeNode leftMostChild(TreeNode n) {
		if (n == null)
			return null;
		while (n.left != null)
			n = n.left;
		return n;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);

		TreeNode node = root.find(6);
		System.out.println(node.data + "->" + succ(node).data);
		
	}
}
