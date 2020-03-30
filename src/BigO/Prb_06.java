package BigO;

//Additional Problems
//VI. 6

public class Prb_06 {
	//O(sqrt(n))
	public static int sqrt(int n) {
		for(int guess = 1; guess * guess <= n; guess++)
			if (guess * guess == n)
				return guess;
		return -1;
	}
	
	public static void main(String[] args) {
		int s = sqrt(9);
		System.out.println(s);
	}
}
