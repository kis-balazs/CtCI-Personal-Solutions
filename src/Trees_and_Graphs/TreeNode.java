package Trees_and_Graphs;

import java.util.Random;

public class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	private int size = 0;

	public TreeNode(int d) {
		data = d;
		size = 1;
	}

	void setLeftChild(TreeNode left) {
		this.left = left;
		if (left != null)
			left.parent = this;
	}

	void setRightChild(TreeNode right) {
		this.right = right;
		if (right != null)
			right.parent = this;
	}

	public TreeNode getRandomNode() {
		int lSize = left == null ? 0 : left.size();
		Random random = new Random();
		int index = random.nextInt(size);
		if (index < lSize) {
			return left.getRandomNode();
		} else if (index == lSize) {
			return this;
		} else
			return right.getRandomNode();
	}

	public void insertInOrder(int d) {
		if (d <= data) {
			if (left == null) {
				left = new TreeNode(d);
			} else
				left.insertInOrder(d);
		} else {
			if (right == null) {
				right = new TreeNode(d);
			} else
				right.insertInOrder(d);
		}
		size++;
	}

	public int size() {
		return size;
	}

	public int data() {
		return data;
	}

	public boolean isBST() {
		if (left != null) {
			if (data < left.data || !left.isBST()) {
				return false;
			}
		}

		if (right != null) {
			if (data >= right.data || !right.isBST()) {
				return false;
			}
		}

		return true;
	}

	public int height() {
		int leftHeight = left != null ? left.height() : 0;
		int rightHeight = right != null ? right.height() : 0;
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public TreeNode find(int d) {
		if (d == data) {
			return this;
		} else if (d <= data) {
			return left != null ? left.find(d) : null;
		} else if (d > data) {
			return right != null ? right.find(d) : null;
		}
		return null;
	}

	// create BST : balanced
	static TreeNode createMinimalBST(int arr[], int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.setLeftChild(createMinimalBST(arr, start, mid - 1));
		n.setRightChild(createMinimalBST(arr, mid + 1, end));
		return n;
	}
}
