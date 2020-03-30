package BigO;

//Examples and Exercises
//Example 4

public class Ex_09 {
	// O(lengthA * lengthB)
	public static void printUnorderedPairs(int[] arrayA, int[] arrayB) {
		for (int i = 0; i < arrayA.length; i++) {
			for (int j = 0; j < arrayB.length; j++) {
				if (arrayA[i] < arrayB[j]) {
					System.out.println(arrayA[i] + "," + arrayB[j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] arrayA = { 0, 1, 2, 3 };
		int[] arrayB = { 4, 5, 6 };

		printUnorderedPairs(arrayA, arrayB);
	}
}
