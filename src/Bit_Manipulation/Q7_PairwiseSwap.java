package Bit_Manipulation;

public class Q7_PairwiseSwap {

	// VERSION A - odds, and then evens; odd bits with mask 0xaaaaaaaa (32bit)
	// then shift right by 1, to be on evens. Same with even (0x55555555) (32bit)
	// and swap left by 1
	public static int swapOddEvens(int n) {
		return (((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1));
	}
	
	public static void main(String[] args) {
		System.out.println(swapOddEvens(8));  // 4
		System.out.println(swapOddEvens(10)); // 5 
	}
}
