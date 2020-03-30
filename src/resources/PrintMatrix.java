package resources;

public class PrintMatrix {
	public static void printMatrix(int[][] matrix, String str) {
		System.out.println(str);
		int i, j;
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
