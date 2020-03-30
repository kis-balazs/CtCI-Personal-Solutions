package BigO;

//Examples and Exercises
//Example 11

public class Ex_13 {
	// O(n) -- decreasing n by 1 each step
	public static int factorial(int n) {
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
}
