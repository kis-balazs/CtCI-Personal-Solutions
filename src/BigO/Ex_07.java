package BigO;

//Examples and Exercises
//Example 2

public class Ex_07 {
	// O(length * length)
	public static void printPairs(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.println(array[i] + ", " + array[j]);
			}
		}
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4 };

		printPairs(array);
	}
}
