package BigO;

//Additional Problems
//VI. 1

public class Prb_01 {
	// O(b)
	public static int product(int a, int b) {
		int sum = 0;
		for (int i = 0; i < b; i++) {
			sum += a;
		}
		return (sum);
	}

	public static void main(String[] args) {
		int s = product(4, 3);
		System.out.println(s);
	}
}
