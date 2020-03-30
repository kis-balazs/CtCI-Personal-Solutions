package BigO;

//Drop the Constants

public class Ex_03 {
	// O(length)
	public static void minAndMax1(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int it : array) {
			if (it < min)
				min = it;
			if (it > max)
				max = it;
		}
		System.out.println(min + ", " + max);
	}

	// O(2 * length) => O(length)
	public static void minAndMax2(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int it : array) {
			if (it < min)
				min = it;
		}
		for (int it : array) {
			if (it > max)
				max = it;
		}
		System.out.println(min + ", " + max);
	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 4, 2, 1, 5, 6 };
		minAndMax1(array);
		// minAndMax2(array);
	}
}
