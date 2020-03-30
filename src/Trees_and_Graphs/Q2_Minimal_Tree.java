package Trees_and_Graphs;

public class Q2_Minimal_Tree {
	/*
	 * sorted(increasing) unique integer elements : minimal height BST
	 */

	// from the array pick the middle, and from the left/right sub-array of it pick
	// again middle and split each sub-array to left and right
	// O(nlogn)
	public static TreeNode createMinimalBST(int[] array) {
		return createMinimalBST(array, 0, array.length - 1);
	}

	public static TreeNode createMinimalBST(int[] array, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		TreeNode n = new TreeNode(array[mid]);
		n.left = createMinimalBST(array, left, mid - 1);
		n.right = createMinimalBST(array, mid + 1, right);
		return n;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		TreeNode root = createMinimalBST(array);
		System.out.println("root : " + root.data);

		System.out.println("BST? : " + root.isBST());
		System.out.println("height : " + root.height());
	}
}
