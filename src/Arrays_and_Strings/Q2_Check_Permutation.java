package Arrays_and_Strings;

public class Q2_Check_Permutation {
	/*
	 * case sensitivity, whitespace specificity
	 */

	// O(nlogn)
	public static String sort(String str) {
		char[] helper = str.toCharArray();
		java.util.Arrays.sort(helper);
		return new String(helper);
	}

	// version A -- check length identity, and if ok, just verify identity after
	// sorting
	public static boolean isPermutationA(String s, String t) {
		if (s.length() != t.length())
			return false;
		return sort(s).equals(sort(t));
	}

	// version B -- check the count of the characters, if constraints keep, can
	// calculate identity; also first see length identity
	// O(n)
	public static boolean isPermutationB(String s, String t) {
		if (s.length() != t.length())
			return false;

		int[] letters = new int[128];
		char[] str1_array = s.toCharArray();
		for (char c : str1_array)
			letters[c]++;

		int i;
		for (i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			letters[c]--;
			if (letters[c] < 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String s = "dog";
		String t = "gdo";
		System.out.println(s + ", " + t + ": " + isPermutationA(s, t));
		System.out.println(s + ", " + t + ": " + isPermutationB(s, t));
	}

}
