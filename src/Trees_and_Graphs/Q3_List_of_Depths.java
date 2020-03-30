package Trees_and_Graphs;

import java.util.*;

public class Q3_List_of_Depths {
	/*
	 * simple tree, height h : h LL's
	 */

	// version A -- doesn't need to be specifically level-based; walk through and
	// mark level, and add to list needed; works well with modifying pre-order;
	// O(n) -- recursive call memory O(logn)
	public static void levelLinkedListsA(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
		if (root == null)
			return;
		LinkedList<TreeNode> list = null;
		if (lists.size() == level) {
			list = new LinkedList<TreeNode>();
			// recursivity checks that in case of a level i, there were already done all the
			// levels from 0 to i - 1
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(root);

		levelLinkedListsA(root.left, lists, level + 1);
		levelLinkedListsA(root.right, lists, level + 1);
	}

	public static ArrayList<LinkedList<TreeNode>> levelLinkedListsA(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		levelLinkedListsA(root, lists, 0);
		return lists;
	}

	public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
		int depth = 0;
		for (LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while (i.hasNext()) {
				System.out.print(" " + ((TreeNode) i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}

	// version B -- modification of the DFS; iterate through level i, then i+1
	// O(n) -- no additional space
	public static ArrayList<LinkedList<TreeNode>> levelLinkedListsB(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if (root != null)
			current.add(root);

		while (current.size() > 0) {
			result.add(current);
			LinkedList<TreeNode> parents = current;
			current = new LinkedList<TreeNode>();
			for (TreeNode parent : parents) {
				if (parent.left != null)
					current.add(parent.left);
				if (parent.right != null)
					current.add(parent.right);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);
		// ArrayList<LinkedList<TreeNode>> list = levelLinkedListsA(root);
		ArrayList<LinkedList<TreeNode>> list = levelLinkedListsB(root);
		printResult(list);
	}
}
