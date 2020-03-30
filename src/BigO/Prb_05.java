package BigO;

//Additional Problems
//VI. 5

public class Prb_05 {
	public static int sqrt(int n) {
		return sqrt_helper(n, 1, n);
	}

	// O(logn) -- binary search to find the square root
	public static int sqrt_helper(int n, int min, int max) {
		if (max < min)
			return -1; // not perfect square
		int guess = (min + max) / 2;
		if (guess * guess == n)
			return guess;
		else if (guess * guess < n)
			return sqrt_helper(n, guess + 1, max);
		else
			return sqrt_helper(n, min, guess - 1);
	}

	public static void main(String[] args) {
		int s = sqrt(5);
		System.out.println(s);
	}
}
