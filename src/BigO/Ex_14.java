package BigO;

//Examples and Exercises
//Example 12

public class Ex_14 {
	public static void permutation(String str) {
		permutation(str, "");
	}

	// O(n * n!) -- n - number of each prefix, n! the number
	// of permutations possible on a string
	// and each call will work in O(n) => O(n ^ 2 * n!) => O(n!)
	public static void permutation(String str, String prefix) {
		if (str.length() == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < str.length(); i++) {
				String rem = str.substring(0, i) + str.substring(i + 1);
				permutation(rem, prefix + str.charAt(i));
			}
		}
	}

	public static void main(String[] args) {
		permutation("abc");
	}
}
