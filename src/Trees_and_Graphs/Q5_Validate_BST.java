package Trees_and_Graphs;

public class Q5_Validate_BST {
	/*
	 * assume tree doesn't have duplicates, for the specific equality;
	 */
	// version A -- in-order traversal and check for property left < p < right; set
	// up the equality between two!!
	public static int index = 0;

	public static void copyBSTA(TreeNode root, int[] array) {
		if (root == null)
			return;
		copyBSTA(root.left, array);
		array[index] = root.data;
		index++;
		copyBSTA(root.right, array);
	}

	public static boolean validateBSTA(TreeNode root, int size) {
		// exact value of the array from main
		int[] array = new int[size];
		copyBSTA(root, array);
		int i;
		for (i = 1; i < array.length; i++) {
			if (array[i] <= array[i - 1])
				return false;
		}
		return true;
	}

	// version B -- no need for an array, only track last used element, and compare
	// as it goes

	// another solution besides static variable is a wrapper class
	public static Integer last_printed = null;

	public static boolean checkBSTB(TreeNode root) {
		if (root == null)
			return true;

		if (!checkBSTB(root.left))
			return false;

		// actual part
		if (last_printed != null && root.data <= last_printed)
			return false;
		last_printed = root.data;

		if (!checkBSTB(root.right))
			return false;
		return true;
	}

	// version C -- min and max; check minimum from right and maximum from left, and
	// if checked recursively correct, it fulfills the BST property
	public static boolean checkBSTC(TreeNode root) {
		return checkBSTC(root, null, null);
	}

	public static boolean checkBSTC(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;

		if ((min != null && root.data <= min) || (max != null && root.data > max))
			return false;
		if (!checkBSTC(root.left, min, root.data) || !checkBSTC(root.right, root.data, max))
			return false;
		return true;
	}

	public static void main(String[] args) {
		// int[] array = { 5, 2, 4 };
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);

		// System.out.println("The tree is a BST : " + validateBSTA(root,
		// array.length));
		// System.out.println("The tree is a BST : " + checkBSTB(root));
		System.out.println("The tree is a BST : " + checkBSTC(root));
	}
}
