package Trees_and_Graphs;

import java.util.HashMap;

public class Q12_Paths_with_Sum {

	
	// VERSION A - divide the problem into counting from root + recursion on left & recursion on right
	// as in any other count problem, just keeping the sum in each step, and have a counter
	// which counts in each subtree, subsequently in the whole tree
	// O(NlogN), worst case O(n^2)
	public static int countPathsWithSum(TreeNode root, int targetSum) {
		if (root == null) return 0;
		
		/* Count paths with sum starting from the root. */
		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
		
		/* Try the nodes on the left and right. */
		int pathsOnLeft = countPathsWithSum(root.left, targetSum);
		int pathsOnRight = countPathsWithSum(root.right, targetSum);
		
		return pathsFromRoot + pathsOnLeft + pathsOnRight;
	}
	
	public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
		if (node == null) return 0;
	
		currentSum += node.data;
		
		int totalPaths = 0;
		if (currentSum == targetSum) { // Found a path from the root
			totalPaths++;
		}
		
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum); // Go left
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum); // Go right
		
		return totalPaths;
	}	
	
	// VERSION B - runningSum(x) = runningSum(y) - targetSum();
	// use a look-up table to store values because once appearing there is known which value to be
	// taken for any runningSum -> HashTable can store values fairly easily
	// 1. Track its runningSum. We'll take this in as a parameter and immediately increment it by node. value.
	// 2. Look up runningSum - targetSum in the hash table. The value there indicates the total number. Set
	//    totalPaths to this value.
	// 3. If runningSum == targetSum, then there's one additional path that starts at the root. Increment
	//    totalPaths.
	// 4. Add runningSum to the hash table (incrementing the value if it's already there).
	// 5. Recurse left and right, counting the number of paths with sum targetSum.
	// 6. After we're done recursing left and right, decrement the value of runningSum in the hash table. 
	//    This is essentially backing out of our work; it reverses the changes to the hash table so that 
	//    other nodes don't use it (since we're now done with node).
	// O(logN), space complexity O(n) maximum
	public static int cntPathsWithSum(TreeNode root, int targetSum) {
		return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
	}
	
	public static int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
		if (node == null) return 0; // Base case
		
		runningSum += node.data;
		
		/* Count paths with sum ending at the current node. */
		int sum = runningSum - targetSum;
		int totalPaths = pathCount.getOrDefault(sum, 0);
		
		/* If runningSum equals targetSum, then one additional path starts at root. Add in this path.*/
		if (runningSum == targetSum) {
			totalPaths++;
		}

		/* Add runningSum to pathCounts. */
		incrementHashTable(pathCount, runningSum, 1);
		
		/* Count paths with sum on the left and right. */
		totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);
		
		incrementHashTable(pathCount, runningSum, -1); // Remove runningSum
		return totalPaths;
	}
	
	public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
		int newCount = hashTable.getOrDefault(key, 0) + delta;
		if (newCount == 0) { // Remove when zero to reduce space usage
			hashTable.remove(key);
		} else {
			hashTable.put(key, newCount);
		}
	}

	public static void main(String [] args) {
		TreeNode root = new TreeNode(-7);
		root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(cntPathsWithSum(root, -14));
		
		System.out.println(countPathsWithSum(root, -14));
	}

}
