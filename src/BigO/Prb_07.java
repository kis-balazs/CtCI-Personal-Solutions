package BigO;

//Additional Problems
//VI. 9

public class Prb_07 {
	// O(lengthArray * lengthArray) -- exactly sum 1 ... lengthArray
	// as in lengthArray * value incr by 1 each step
	public static int[] copyArray(int[] array) {
		int[] copy = new int[0];
		for (int value : array) {
			copy = appendToArray(copy, value);
		}
		return copy;
	}

	// O(lengthArray)
	public static int[] appendToArray(int[] array, int value) {
		int[] bigger = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			bigger[i] = array[i];
		}
		bigger[bigger.length - 1] = value;
		return bigger;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		int[] copy = copyArray(array);
		for (int x : copy) {
			System.out.println(x);
		}
	}
}
