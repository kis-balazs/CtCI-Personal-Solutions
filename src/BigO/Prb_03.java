package BigO;

//Additional Problems
//VI. 3

public class Prb_03 {
	// O(1) -- simple calculations, no loops
	public static int mod(int a, int b) {
		if (b <= 0)
			return -1; // error
		int div = a / b;
		return a - div * b;
	}

	public static void main(String[] args) {
		int s = mod(5, 3);
		System.out.println(s);
	}
}
