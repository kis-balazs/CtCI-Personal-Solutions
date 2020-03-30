package Arrays_and_Strings;

public class Q6_String_Compression {
	/*
	 * compression, simply the one after other identicals are grouped
	 */
	// version A -- simply verify from each point the aggregates
	// O(n + charSequences ^ 2) => O(n ^ 2)
	public static String concatA(String s) {
		String ans = "";
		int consecutiveCnt = 0;
		int i;
		for (i = 0; i < s.length(); i++) {
			consecutiveCnt++;

			if ((i + 1) >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
				ans += "" + s.charAt(i) + consecutiveCnt;
				consecutiveCnt = 0;
			}
		}
		return ans.length() < s.length() ? ans : s;
	}

	// version B -- use StringBuilder to fasten up the exponential time
	// concatenations
	// O(n)
	public static String concatB(String s) {
		StringBuilder ans = new StringBuilder();
		int consecutiveCnt = 0;
		int i;
		for (i = 0; i < s.length(); i++) {
			consecutiveCnt++;

			if ((i + 1) >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
				ans.append(s.charAt(i));
				ans.append(consecutiveCnt);
				consecutiveCnt = 0;
			}
		}
		return ans.length() < s.length() ? ans.toString() : s;
	}

	// version C -- calculate first the actual length of the final, and create the
	// exact string
	// O(n)
	public static String concatC(String s) {
		int finalLength = compression(s);
		if (finalLength >= s.length())
			return s;

		StringBuilder ans = new StringBuilder(finalLength);
		int consecutiveCnt = 0;
		int i;
		for (i = 0; i < s.length(); i++) {
			consecutiveCnt++;

			if ((i + 1) >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
				ans.append(s.charAt(i));
				ans.append(consecutiveCnt);
				consecutiveCnt = 0;
			}
		}
		return ans.toString();
	}

	public static int compression(String s) {
		int consecutiveCnt = 0;
		int length = 0;
		int i;

		for (i = 0; i < s.length(); i++) {
			consecutiveCnt++;

			if ((i + 1) >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
				length += 1 + String.valueOf(consecutiveCnt).length();
				consecutiveCnt = 0;
			}
		}
		return length;
	}

	public static void main(String[] args) {
		String s = "aaabbbbcdd";
		// String ans = concatA(s);
		// String ans = concatB(s);
		String ans = concatC(s);
		System.out.println(ans);
	}
}
