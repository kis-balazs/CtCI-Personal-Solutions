package Bit_Manipulation;

public class Q1_Insertion {
	
	// SOLUTION - clear bits j <-> i (create a mask), 
	// shift m so that it matches the cleared space and merge the n and m
	public static int updateBits(int n, int m, int i, int j) {
		int ones = ~0; // seq of 1s
		
		// 1s before pos j, then 0s
		int left = ones << (j + 1);
		// 1s after pos i
		int right = ((1 << i) - 1);
		
		// create mask
		int mask = left | right;
		
		// clear bits j <-> i, and put n there
		int cleared_n = n & mask;
		int shift_m = m << i;
		
		return cleared_n | shift_m;
	}
	
	public static void main(String[] args) {
		int n = 1024; // 1000000000
		int m = 19; // 10011
		int i = 2;
		int j = 6;
		
		System.out.println(updateBits(n, m, i, j));
		// should give 1100
	}
}
