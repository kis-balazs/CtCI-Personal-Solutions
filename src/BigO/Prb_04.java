package BigO;

//Additional Problems
//VI. 4

public class Prb_04 {
	// O(a / b) -- count times iterating
	public static int div(int a, int b) {
		int count = 0;
		int sum = b;
		while (sum <= a) {
			sum += b;
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		int s = div(11, 3);
		System.out.println(s);
	}
}
