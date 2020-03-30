package resources;

public class MergeSort {
	public static void mergeSort(int[] array) {
		int[] helper = new int[array.length];
		mergeSort(array, helper, 0, array.length - 1);
	}

	// O(nlogn) -- ascending
	public static void mergeSort(int[] array, int[] helper, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(array, helper, low, mid); // left
			mergeSort(array, helper, mid + 1, high); // right
			merge(array, helper, low, mid, high); // merge the 2 sub-arrays
		}
	}

	public static void merge(int[] array, int[] helper, int low, int mid, int high) {
		// place array in the helper
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

		int helperLeft = low;
		int helperRight = mid + 1;
		int current = low;

		// smaller from left and right back to the array
		while (helperLeft <= mid && helperRight <= high) {
			// left smaller
			if (helper[helperLeft] <= helper[helperRight]) {
				array[current] = helper[helperLeft];
				helperLeft++;
			}
			// right smaller
			else {
				array[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}

		// remaining from left => in the array
		int remaining = mid - helperLeft;
		for (int i = 0; i <= remaining; i++) {
			array[current + i] = helper[helperLeft + i];
		}
	}

}
