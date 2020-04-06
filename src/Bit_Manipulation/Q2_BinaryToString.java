package Bit_Manipulation;

public class Q2_BinaryToString {

	// VERSION A - compare the number to . 5, . 25, ...
	public static String printBinaryA(double n) {
		if (n >= 1 || n <= 0)
			return "ERROR";
		
		StringBuilder bin = new StringBuilder();
		bin.append("0.");
		while (n > 0) {
			// limit of 32 digits, as given requirements
			if (bin.length() >= 32)
				return "ERROR";
			
			if (n >= 0.5) {
				bin.append(1);
				n /= 2;
			} else
				bin.append(0);
		}
		return bin.toString();
	}
		
	// VERSION B - print the decimal part, mult w/ 2 and check if 2n >= 1
	// basically shifting the decimal part
	public static String printBinaryB(double n) {
		// verify bounds
		if (n >= 1 || n <= 0)
			return "ERROR";
		
		StringBuilder bin = new StringBuilder();
		bin.append("0.");
		while (n > 0) {
			// limit of 32 digits, as given requirements
			if (bin.length() >= 32)
				return "ERROR";
		
			double r = n * 2;
			if (r >= 1) {
				bin.append(1);
				n = r - 1;
			} else {
				bin.append(0);
				n = r;
			}
		}
		return bin.toString();
	}
	
	public static void main(String[] args) {
		// System.out.println(printBinaryA(0.625)); // 0.101
		// System.out.println(printBinaryA(0.75)); // 0.11
		
		// System.out.println(printBinaryB(0.625)); // 0.101
		// System.out.println(printBinaryB(0.75)); // 0.11
	}

}
