package Arrays_and_Strings;

public class Q4_Palindrome_Permutation {
	/*
	 * palindrome => same from fw, bw; almost all characters even count, and at most
	 * one odd count(so even length = all even, odd length = at most one even)
	 */

	// version A -- use a hash table to count all appearances of characters, and see
	// the count, to ensure at most one is odd
	// O(n)
	public static boolean isPermutationA(String str) {
		int[] table = buildCharFTable(str);
		return checkMaxOneOdd(table);
	}

	// check if not more than one has odd count from the table
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table) {
			if (count % 2 == 1) {
				if (foundOdd)
					return false;
				foundOdd = true;
			}
		}
		return true;
	}

	// map each character to a number; non-letter character gets mapped to -1
	public static int getCharNumber(Character c) {
		int a = 0;
		int z = 25;
		int val = (int) (c - 'a');
		if (a <= val && val <= z)
			return val - a;
		return -1;
	}

	// count characters, use the hash table
	public static int[] buildCharFTable(String str) {
		int[] table = new int[26];
		for (char c : str.toCharArray()) {
			int x = getCharNumber(c);
			if (x != -1)
				table[x]++;
		}
		return table;
	}

	// version B -- simplified version of A, only one function of checking at most
	// one odd, a thin layer better than A; replaceAll, and just sub 'a' -> mapped
	// on the alphabet
	// O(n)
	public static boolean isPermutationB(String str) {
		int countOdd = 0;
		int[] table = new int[26];
		String strNew = str.replaceAll(" ", "");
		for (char c : strNew.toCharArray()) {
			int x = (int) (c - 'a');
			// getCharNumber(c);
			if (x != -1) {
				table[x]++;
				if (table[x] % 2 == 1)
					countOdd++;
				else
					countOdd--;
			}
		}
		return countOdd <= 1;
	}

	// version C -- use a single integer(0 - 26) to see even/odd of the whole
	// string; check if only one digit is 1 : X & (X - 1) if 0
	// O(n)
	public static boolean isPermutationC(String str) {
		int bitVector = createBitVector(str);
		return bitVector == 0 || checkExactlyOneBitOn(bitVector);
	}

	public static int createBitVector(String str) {
		int bitVector = 0;
		for (char c : str.toCharArray()) {
			int x = getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}

	public static int toggle(int vect, int x) {
		if (x < 0)
			return vect;

		int mask = 1 << x;
		if ((vect & mask) == 0)
			vect |= mask;
		else
			vect &= ~mask;
		return vect;
	}

	public static boolean checkExactlyOneBitOn(int vect) {
		return (vect & (vect - 1)) == 0;
	}

	public static void main(String[] args) {
		String s = "tact coa";
		System.out.println(s + " : " + isPermutationA(s));
		System.out.println(s + " : " + isPermutationB(s));
		System.out.println(s + " : " + isPermutationC(s));
	}
}
