package Bit_Manipulation;

public class Q5_Debugger {
	
	// bits on the same location in n & (n - 1) should be different IN EACH position
	// so 1 & 0 are OK this means (1), 0 also ok -> (0, 1)
	// every number with ONE 1 and trailing zeros for n, from which subtracting one will
	// give 100..0 & 011..1 => 0, this function checks for powers of true AND 0
	// (0, 1, 2, 4, 8, 16, ...)
	public static boolean powerOf2(int n) {
		return (n & (n - 1)) == 0;
	}
	
	public static void main(String args[]) {
		for (int i = 0; i <= 16; i++) {
			System.out.println(i + ", " + powerOf2(i));
		}
	}
}