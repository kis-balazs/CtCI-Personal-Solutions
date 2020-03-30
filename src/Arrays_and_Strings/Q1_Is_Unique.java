package Arrays_and_Strings;

public class Q1_Is_Unique {
	/*
	 * ASCII alphabet -> 128 characters, extended => doubled size
	 */

	// version A -- used additional data structures
	// save each occurrence of each character, and if already saved, just return
	// false
	// O(n)
	public static boolean isUniqueCharactersA(String str) {
		if (str.length() > 128)
			return false;
		boolean[] charSet = new boolean[128];
		int i;
		for (i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (charSet[val])
				return false;
			charSet[val] = true;
		}
		return true;
	}

	// version B -- didn't use any additional data structures
	// verify first character unicity => reducing memory by 8(a - z characters), bit
	// vector
	// O(n)
	public static boolean isUniqueCharactersB(String str) {
		int seek = 0;
		int i;
		for (i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((seek & (1 << val)) > 0)
				return false;
			seek |= (1 << val);
		}
		return true;
	}
	// without data structures
	// => compares each with each O(n ^ 2)
	// => sort and linear O(nlogn)

	public static void main(String[] args) {
		String[] strs = new String[] { "almb", "attila", "valami" };
		int i;
		for (i = 0; i < strs.length; i++) {
			System.out.println("A : " + strs[i] + ": " + isUniqueCharactersA(strs[i]));
			System.out.println("B : " + strs[i] + ": " + isUniqueCharactersB(strs[i]));
		}
	}
}
