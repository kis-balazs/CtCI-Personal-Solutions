package Trees_and_Graphs;

public class Q10_Check_Subtree {
	/*
	 * T1 >> T2; T2 subtree of T1
	 */

	// version A -- walk through both trees and determine if the walk of T2 is
	// entire substring of T1; pre-order walk is good, but to create a deterministic
	// algorithm, both trees need to be complete : complete with NULL/X/something
	// which doesn't affect existing values until complete tree, then check
	// O(n + m) -- same amount of memory; n, m : nodes of T1 respectively T2
	public static boolean isSubTreeA(TreeNode t1, TreeNode t2) {
		StringBuilder tree1 = new StringBuilder();
		StringBuilder tree2 = new StringBuilder();

		getPreOrderStr(t1, tree1);
		getPreOrderStr(t2, tree2);

		return tree1.indexOf(tree2.toString()) != -1;
	}

	public static void getPreOrderStr(TreeNode t, StringBuilder tree) {
		if (t == null) {
			tree.append("X");
			return;
		}
		tree.append(t.data + " "); // root
		getPreOrderStr(t.left, tree); // left
		getPreOrderStr(t.right, tree); // right
	}

	// version B -- walk T1 and if node == root(T2) : matchTree
	// better in space + time then first one; depends also on the decision of the
	// optimality factors
	// O(n + k * m) -- k times call matchTree, but lower memory
	public static boolean isSubTreeB(TreeNode t1, TreeNode t2) {
		if (t2 == null)
			return true;
		return subTree(t1, t2);
	}

	public static boolean subTree(TreeNode root1, TreeNode root2) {
		if (root1 == null) {
			return false;
		} else if (root1.data == root2.data && matchTree(root1, root2)) {
			return true;
		}
		return subTree(root1.left, root2) || subTree(root1.right, root2);
	}

	public static boolean matchTree(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null) {
			return false;
		} else if (root1.data != root2.data) {
			return false;
		} else {
			return matchTree(root1.left, root2.left) && matchTree(root1.right, root2.right);
		}
	}

	public static void main(String[] args) {
		int[] arrayT1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] arrayT2 = new int[] { 6, 7 };

		TreeNode rootT1 = TreeNode.createMinimalBST(arrayT1, 0, arrayT1.length - 1);
		TreeNode rootT2 = TreeNode.createMinimalBST(arrayT2, 0, arrayT2.length - 1);

		System.out.println("T2 is subtree of T1 : " + isSubTreeA(rootT1, rootT2));
		System.out.println("T2 is subtree of T1 : " + isSubTreeB(rootT1, rootT2));
	}
}
