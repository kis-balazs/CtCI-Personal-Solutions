package BigO;

//Examples and Exercises
//Example 6

public class Ex_11 {
	// O(length / 2) => O(length)
	public static void reverse(int[] array) {
		for (int i = 0; i < array.length / 2; i++) {
			int other = array.length - i - 1;
			int temp = array[i];
			array[i] = array[other];
			array[other] = temp;
		}
	}

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 3, 4, 5 };
		reverse(array);
		for (int x : array) {
			System.out.println(x);
		}
	}
}
