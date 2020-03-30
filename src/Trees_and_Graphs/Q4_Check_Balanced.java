package Trees_and_Graphs;

public class Q4_Check_Balanced {
	/*
	 * balanced tree here : heights of the 2 subtrees of any node never differ by
	 * more than one
	 */

	// version A -- already know the basic criteria, so just get height and check
	// O(nlogn)
	public static int getHeightA(TreeNode root) {
		if (root == null)
			return -1;
		return Math.max(getHeightA(root.left), getHeightA(root.right)) + 1;
	}

	public static boolean isBalancedA(TreeNode root) {
		if (root == null)
			return true;

		int heightDiff = getHeightA(root.left) - getHeightA(root.right);
		if (Math.abs(heightDiff) > 1)
			return false;
		else
			return isBalancedA(root.left) && isBalancedA(root.right);
	}

	// version B -- checking balance when checking heights; check height of each
	// subtree, rec. from root; each node get both heights; if subtree is balanced,
	// return height, else break it, because already got an error; use any kind of
	// safe error code
	// O(n) -- O(h) space
	public static int checkHeightB(TreeNode root) {
		if (root == null)
			return -1;
		
		int leftHeight = checkHeightB(root.left);
		if (leftHeight == -2) return -2;
		
		int rightHeight = checkHeightB(root.right);
		if (rightHeight == -2) return -2;
		
		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1)
			return -2;
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static boolean isBalancedB(TreeNode root) {
		return checkHeightB(root) != -2;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = TreeNode.createMinimalBST(array, 0, array.length - 1);

		//System.out.println("The tree is balanced : " + isBalancedA(root));
		System.out.println("The tree is balanced : " + isBalancedB(root));
	}
}
