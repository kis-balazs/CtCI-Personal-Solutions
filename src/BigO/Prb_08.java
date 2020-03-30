package BigO;

//Additional Problems
//VI. 10

public class Prb_08 {
	// O(logn) -- number with d digits can have value up to 10^d
	// n = 10^d => d = logn
	public static int sumDigits(int n) {
		int sum = 0;
		while (n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}

	public static void main(String[] args) {
		int s = sumDigits(4355);
		System.out.println(s);
	}
}
