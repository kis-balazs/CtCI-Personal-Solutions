package Arrays_and_Strings;

public class Q8_Zero_Matrix {
	/*
	 * matrix of N * M
	 */

	// version A -- keep track of the rows and cols and set row/col where found 0
	// and then make 0 row and col
	// O(n ^ 2)
	public static void setZerosA(int[][] matrix) {
		boolean[] rows = new boolean[matrix.length];
		boolean[] cols = new boolean[matrix[0].length];

		int i, j;
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[0].length; j++)
				if (matrix[i][j] == 0) {
					rows[i] = true;
					cols[j] = true;
				}
		}

		// null rows
		for (i = 0; i < rows.length; i++) {
			if (rows[i])
				setRows(matrix, i);
		}

		// null cols
		for (j = 0; j < cols.length; j++) {
			if (cols[j])
				setCols(matrix, j);
		}
	}

	public static void setRows(int[][] matrix, int row) {
		int j;
		for (j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}
	}

	public static void setCols(int[][] matrix, int col) {
		int i;
		for (i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}

	// version B -- use first col and row instead of two separate arrays; first see
	// if there is any 0 in these two, to know to set it at the end 0, and then just
	// set them to 0 if found 0 in the matrix at the intersection of them
	// O(n ^ 2), without additional memory
	public static void setZerosB(int[][] matrix) {
		boolean rowHasZeros = false;
		boolean colHasZeros = false;
		
		int i, j;
		for(i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0)
				rowHasZeros = true;
		}
		
		for(j = 0; j < matrix[0].length; i++) {
			if (matrix[0][j] == 0)
				colHasZeros = true;
		}
		
		for(i = 1; i < matrix.length; i++) {
			for(j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for(i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0)
				setRows(matrix, i);
		}
		
		for(j = 1; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0)
				setCols(matrix, j);
		}
		
		if (rowHasZeros) {
			setRows(matrix, 0);
		}
		if (colHasZeros) {
			setCols(matrix, 0);
		}
		
	}
	
	public static void main(String[] args) {
		int[][] matrixA = new int[][] { { 1, 2, 0, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int[][] matrixB = new int[][] { { 1, 2, 0, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 0, 14, 15, 16 } };
		resources.PrintMatrix.printMatrix(matrixA, "The initial matrix");
		setZerosA(matrixA);
		resources.PrintMatrix.printMatrix(matrixA, "After setZerosA");
		
		resources.PrintMatrix.printMatrix(matrixB, "The initial matrix");
		setZerosA(matrixB);
		resources.PrintMatrix.printMatrix(matrixB, "After setZerosB");
	}
}
