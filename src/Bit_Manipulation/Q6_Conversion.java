package Bit_Manipulation;

public class Q6_Conversion {

	// VERSION A - get how many digits are different in the two numbers
	// using XOR for each bit
	public static int bitSwapReqA(int n, int m) {
		int cnt = 0;
		for (int b = n ^ m; b != 0; b >>= 1)
			cnt += b & 1;
		
		return cnt;
	}
	
	// VERSION B - cont flip the least significant bit and cnt how long
	// it takes b to get to 0, by using c & (c - 1)
	public static int bitSwapReqB(int n, int m) {
		int cnt = 0;
		for (int b = n ^ m; b != 0; b = b & (b - 1))
			cnt++;
		
		return cnt;
	}
	
	public static void main(String[] args) {
		// System.out.println(bitSwapReqA(29, 15)); // 2
		// System.out.println(bitSwapReqA(8, 7)); // 4
		
		System.out.println(bitSwapReqB(29, 15)); // 2
		System.out.println(bitSwapReqB(8, 7)); // 4
	}

}
