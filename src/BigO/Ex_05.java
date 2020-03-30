package BigO;

//Recursive Runtimes

public class Ex_05 {
	// O(branch ^ depth) -- usually for recursive ones,
	// because of the tree structure of the decomposition
	public static int f(int n) {
		if (n <= 0)
			return 1;
		else
			return f(n - 1) + f(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(f(4));
	}
}