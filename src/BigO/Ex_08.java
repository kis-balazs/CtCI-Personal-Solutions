package BigO;

//Examples and Exercises
//Example 3

public class Ex_08 {
	// O((length * length) / 2) -- sum of 1 through N - 1,
	// or the sum of all elements in a half - matrix => O(length * length)
	public static void printUnorderedPairs(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				System.out.println(array[i] + "," + array[j]);
			}
		}
	}

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 3 };

		printUnorderedPairs(array);
	}
}
