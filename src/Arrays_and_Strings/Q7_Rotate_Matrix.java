package Arrays_and_Strings;

public class Q7_Rotate_Matrix {
	/*
	 * each pixel : 4 bytes => integer
	 */

	// rotate by layers, as in edges : swap of 4(not the usual 2; using a temp)
	// O(n ^ 2)
	public static boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length)
			return false;
		int layer;
		for (layer = 0; layer < matrix.length / 2; layer++) {
			int first = layer;
			int last = matrix.length - 1 - layer;
			int i;
			for (i = first; i < last; i++) {
				int offset = i - first;
				// saved top
				int top = matrix[first][i];
				// left -> top
				matrix[first][i] = matrix[last - offset][first];
				// bottom -> left
				matrix[last - offset][first] = matrix[last][last - offset];
				// right -> bottom
				matrix[last][last - offset] = matrix[i][last];
				// top -> right
				matrix[i][last] = top;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		rotate(matrix);
		resources.PrintMatrix.printMatrix(matrix, "");
	}
}
