package BigO;

//Additional Problems
//VI. 12

import resources.MergeSort;
import resources.BinarySearch;

public class Prb_10 {
	// O(lengthB * log(lengthB) + lengthA * log(lengthB)) -- mergesort being blogb
	// and a times binary search being logb
	public static int intersection(int[] a, int[] b) {
		MergeSort.mergeSort(b);
		int intersect = 0;

		for (int it : a) {
			if (BinarySearch.binarySearch(b, it) >= 0) {
				intersect++;
			}
		}
		return intersect;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 5, 7 };
		int[] b = new int[] { 1, 2, 3, 5 };

		int s = intersection(a, b);
		System.out.println(s);
	}
}
