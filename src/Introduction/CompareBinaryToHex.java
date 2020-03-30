package Introduction;

//Appropriate Code Reuse

public class CompareBinaryToHex {
	public static int digitToValue(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else if (c >= 'A' && c <= 'F') {
			return 10 + c - 'A';
		} else if (c >= 'a' && c <= 'f') {
			return 10 + c - 'a';
		}
		return -1;
	}

	// could've calculated separately, but generic conversion of the number to a
	// base is more useful
	public static int convertFromBase(String number, int base) {
		// non-existent base
		if (base < 2 || (base > 10 && base != 16))
			return -1;
		int value = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = digitToValue(number.charAt(i));
			if (digit < 0 || digit >= base) {
				return -1;
			}
			int exp = number.length() - 1 - i;
			value += digit * Math.pow(base, exp);
		}
		return value;
	}

	public static boolean compareBinaryToHex(String binary, String hex) {
		int bin = convertFromBase(binary, 2);
		int hexa = convertFromBase(hex, 16);
		if (bin < 0 || hexa < 0)
			return false;
		else
			return bin == hexa;
	}

	public static void main(String[] args) {
		boolean value = compareBinaryToHex("11101001", "E9");
		System.out.println(value);
	}
}
