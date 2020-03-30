package BigO;

//Space Complexity

public class Ex_01 {
	// O(n) -- decr each time 1 from n
	public static int sum(int n) {
		if (n <= 0)
			return 0;
		else
			return n + sum(n - 1);
	}

	public static void main(String[] args) {
		int s = sum(5);
		System.out.println(s);
	}
}
