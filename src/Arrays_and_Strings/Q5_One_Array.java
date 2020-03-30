package Arrays_and_Strings;

public class Q5_One_Array {
	/*
	 * 3 operations : insert, remove, replace in string insertion is the opposite of
	 * the removal
	 */

	// version A -- brute-force solution of checking all 3 operations and comparing
	// the strings if there is at most one possible operation to be the difference
	// O(n)
	public static boolean oneEditAwayA(String s, String t) {
		if (s.length() == t.length())
			return replace(s, t);
		else if (s.length() + 1 == t.length())
			return insert(s, t);
		else if (s.length() - 1 == t.length())
			return insert(t, s); // the delete
		return false;
	}

	// same length, just on one place there is a difference between characters
	public static boolean replace(String s, String t) {
		boolean diff = false;
		int i;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (diff)
					return false;
				else
					diff = true;
			}
		}
		return true;
	}

	// different length by inserting one character somewhere
	public static boolean insert(String s, String t) {
		int s1 = 0;
		int t1 = 0;
		while (s1 < s.length() && t1 < t.length()) {
			if (s.charAt(s1) != t.charAt(t1)) {
				if (s1 != t1)
					return false;
				t1++;
			} else {
				s1++;
				t1++;
			}
		}
		return true;
	}

	// version B -- only just observing the difference between replace and insert,
	// which is by means of length; replace : flag difference, insert : increment
	// pointer to the longer string
	// O(n)
	public static boolean oneEditAwayB(String s, String t) {
		if (Math.abs(s.length() - t.length()) > 1)
			return false;

		// short
		String s1 = s.length() < t.length() ? s : t;
		// long
		String t1 = s.length() < t.length() ? t : s;

		int s2 = 0;
		int t2 = 0;
		boolean diff = false;

		while (s2 < s1.length() && t2 < t1.length()) {
			if (s1.charAt(s2) != t1.charAt(t2)) {
				if (diff)
					return false;
				diff = true;

				if (s1.length() == t1.length())
					s2++;
			} else {
				s2++;
			}
			t2++;
		}
		return true;

	}

	public static void main(String[] args) {
		String s = "dog";
		String t = "doga";
		System.out.println(s + ", " + t + ": " + oneEditAwayA(s, t));
		System.out.println(s + ", " + t + ": " + oneEditAwayB(s, t));
	}
}
