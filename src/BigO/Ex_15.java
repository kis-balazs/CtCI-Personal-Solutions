package BigO;

//Examples and Exercises
//Example 13

public class Ex_15 {
	// O(2 ^ n) -- thinking of the general recursive pattern of O(branch^depth)
	public static int fib(int n) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 6; i++) {
			System.out.println(i + ": " + fib(i));
		}
	}
}