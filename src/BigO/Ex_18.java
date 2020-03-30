package BigO;

//Examples and Exercises
//Example 16

public class Ex_18 {
	// O(logn) -- logN powers of 2 between 1 and n
	public static int powersOf2(int n) {
		if (n == 1) {
			System.out.println(1);
			return 1;
		} else {
			int prev = powersOf2(n / 2);
			int curr = prev * 2;
			System.out.println(curr);
			return curr;
		}
	}

	public static void main(String[] args) {
		powersOf2(100);
	}
}
