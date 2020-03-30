package Introduction;

//Modularity of the code

public class SwapMaxMin {
	//finding first min
	public static int getMinIndex(int[] array) {
		int minIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < array[minIndex])
				minIndex = i;
		}
		return minIndex;
	}

	//finding first max
	public static int getMaxIndex(int[] array) {
		int maxIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[maxIndex])
				maxIndex = i;
		}
		return maxIndex;
	}

	public static void swap(int[] array, int m, int n) {
		int temp = array[m];
		array[m] = array[n];
		array[n] = temp;
	}

	public static void swapMinMaxModular(int[] array) {
		int min = getMinIndex(array);
		int max = getMaxIndex(array);
		swap(array, min, max);
	}

	public static void swapMinMax(int[] array) {
		int minIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[minIndex]) {
				minIndex = i;
			}
		}

		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[maxIndex]) {
				maxIndex = i;
			}
		}

		int temp = array[minIndex];
		array[minIndex] = array[maxIndex];
		array[maxIndex] = temp;
	}

	public static void main(String[] args) {
		int[] aMod = new int[] { 4, 5, 3, 2, 6, 1, 7, 1, 7, 1 };
		int[] a = new int[] { 4, 5, 3, 2, 6, 1, 7, 1, 7, 1 };
		
		System.out.println("Simple :");
		swapMinMax(a);
		for(int it = 0; it < a.length; it++) {
			System.out.print(a[it] + " ");
		}
		System.out.println("\nModular :");
		swapMinMaxModular(aMod);
		for(int it = 0; it < aMod.length; it++) {
			System.out.print(aMod[it] + " ");
		}
		System.out.println();
	}
}
