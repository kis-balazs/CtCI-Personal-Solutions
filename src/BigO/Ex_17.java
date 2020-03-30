package BigO;

//Examples and Exercises
//Example 15

public class Ex_17 {
	// O(n)
	public static void allFib(int n) {
		int[] memo = new int[n + 1];
		for (int i = 0; i < n; i++) {
			System.out.println(i + ": " + fib(i, memo));
		}
	}

	// memoization -- the fib function takes constant amount of time because of the
	// already calculated previous values stored in array
	public static int fib(int n, int[] memo) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (memo[n] > 0)
			return memo[n];

		memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
		return memo[n];
	}

	public static void main(String[] args) {
		allFib(6);
	}
}
