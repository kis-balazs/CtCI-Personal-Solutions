package Trees_and_Graphs;

import java.util.*;

public class Q9_BST_Sequences {
	/*
	 * all possible arrays leading to tree; no duplicates
	 */

	// ordering; no order : left/right doesn't matter; recursively weaving : merge 2
	// arrays in all possible ways, keeping relative order within one array; store
	// as LL, push prefixed elements down the recursion, if both first and second
	// are empty, add prefix and done
	public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> res = new ArrayList<LinkedList<Integer>>();

		if (node == null) {
			res.add(new LinkedList<Integer>());
			return res;
		}

		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);

		// recurse left & right subtrees
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

		// weave together each list from the left and right sides
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				res.addAll(weaved);
			}
		}
		return res;
	}

	// all possible weavings; remove head from one list, recurse, same with the
	// other one
	public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
			ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		// one list is empty; add remainder to (a cloned) prefix and store values
		if (first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}

		// recurse with head of first added to the prefix; removing head will damage
		// first, so need to place back afterwards
		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);

		// same thing on the second, damaging, then restoring the list
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		second.addFirst(headSecond);
	}
	
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4 };
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);
		
		ArrayList<LinkedList<Integer>> allSeq = allSequences(root);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
		System.out.println(allSeq.size());
	}
}
