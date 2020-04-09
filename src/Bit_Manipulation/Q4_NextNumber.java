package Bit_Manipulation;

public class Q4_NextNumber {

	// VERSION A - count the number of ls then increment (or decrement)
	// until you find a number with the same number of ls
	public static int ones(int n) {
		int cnt = 0;
		while (n != 0) {
			if ((n & 1) == 1)
				cnt++;
			n >>>= 1;
		}
		return cnt;
	}
	
	// O(n) + O(1) mem
	public static void nextNumberA(int n) {
		if (n == 0) {
			System.out.println("welp");
			return;
		}
		int ones = ones(n);
		
		int bigger = n + 1;
		while (ones(bigger) != ones)
			bigger++;
		System.out.println("bigger one: " + bigger);
		
		int lower = n - 1;
		while (lower > 0 && (ones(lower) != ones))
			lower--;
		if (lower != 0)
			System.out.println("lower one: " + lower);
		else
			System.out.println("any positive number fitting");
	}
	
	// VERSION B - flip rightmost non-trailing zero (and one 1), 
	// clear bits from right of it, add then 1 the times one 
	// appears on the right of the fliiped one
	// O(n) + O(1) mem
	public static int getNextA(int n) {
		// get c0 & c1 -> number of 0|1 to right of the flipped one
		int c = n;
		int c0 = 0, c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		
		int p = c0 + c1;
		// if number is 111...1000..00 -> there is no bigger number w/ same nb of ones
		if (p == 31 || p == 0)
			return -1;
		
		n |= (1 << p); 				//flip rightmost non-trailing zero
		n &= ~((1 << p) - 1); 		// clear all bits to right of p
		n |= (1 << (c1 - 1)) - 1; 	// insert (c1 - 1) ones to right
		
		return n;
	}
	
	// compute c0 - size of block zeros on left of trailing ones & c1 - ones trailing,
	// flip rightmost non-trailing one (c0 + c1)
	// clear on right of p
	// inser c1 + 1 ones immediately to right of pos p
	// O(n) + O(1) mem
	public static int getPrevA(int n) {
		int c = n;
		int c0 = 0, c1 = 0;
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		if (c == 0) 
			return -1;
		
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		
		int p = c0 + c1;
		n &= ((~0) << (p + 1)); // clear from p onwards
		
		int mask = (1 << (c1 + 1)) - 1; // seq. of (c1 + 1) ones
		n |= mask << (c0 - 1);
		
		return n;
	}
	
	// VERSION C - arithmetic operatoins yield
	// next = n + 2^c0 + 2^(c1 - 1) - 1
	// pres = n - 2^c1 - 2^(c0 - 1) + 1 
	// O(n) + O(1) mem
	public static int getNextB(int n) {
		// get c0 & c1 -> number of 0|1 to right of the flipped one
		int c = n;
		int c0 = 0, c1 = 0;
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		return n + (1 << c0) + (1 << (c1 - 1)) - 1;
	}
	
	public static int getPrevB(int n) {
		int c = n;
		int c0 = 0, c1 = 0;
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		if (c == 0) 
			return -1;
		
		while (((c & 1) == 0) && (c != 0)) {
			c0++;
			c >>= 1;
		}
		return n - (1 << c1) - (1 << (c0 - 1)) + 1;
	}
	public static void main(String[] args) {
		// VERSION A - brute
		// nextNumberA(4); // 8 & 2
		// nextNumberA(10); // 12 & 9
		
		//version B - logical
		//System.out.println(getNextA(4));
		//System.out.println(getPrevA(4));
		//System.out.println(getNextA(10));
		//System.out.println(getPrevA(10));
		
		// VERSION C - arithmetic
		System.out.println(getNextB(4));
		System.out.println(getPrevB(4));
		System.out.println(getNextB(10));
		System.out.println(getPrevB(10));
	}
}
