package BigO;

//Additional Problems
//VI. 2

public class Prb_02 {
	// O(b) -- recursive follow-up, decr b by 1 each step
	public static int power(int a, int b) {
		if (b < 0)
			return 0; // error
		else if (b == 0)
			return 1;
		else
			return a * power(a, b - 1);
	}

	public static void main(String[] args) {
		int s = power(3, 3);
		System.out.println(s);
	}
}
