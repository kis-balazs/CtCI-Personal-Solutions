package Linked_Lists;

public class Q2_Return_Kth_To_Last {
	/*
	 * recursivity, non-recursivity => advantages, disadvantages SLL
	 */

	// version A -- if length is knows, just iterate through it, until find (length
	// - k)'th element
	// O(n)
	public static LinkedList kthToLastA(LinkedList l, int length, int k) {
		if (l == null)
			return null;
		int i = 0;
		while (i < (length - k)) {
			l = l.next;
			i++;
		}
		return l;
	}

	// version B -- recurse through the list, if at the end, passes back a counter
	// set to 0, each parent call adds 1 to the counter; counter equals k, that is
	// the k'th from last; Can use pointer and pass by reference the index value
	// O(n) -- space because recursive call
	public static int kthToLastB(LinkedList l, int k) {
		if (l == null)
			return 0;
		int index = kthToLastB(l.next, k) + 1;
		if (index == k) {
			System.out.println("The " + k + "th last is (B): " + l.data);
		}
		return index;
	}

	// version C -- create a wrapper class, and same way as version B, calculate
	// index, and see which is equal to k, that will be the k'th to last item
	// O(n) -- space, because recursive call
	static class Index {
		public int value = 0;
	}

	public static LinkedList kthToLastC(LinkedList l, int k) {
		Index index = new Index();
		return kthToLastC(l, k, index);
	}

	public static LinkedList kthToLastC(LinkedList l, int k, Index index) {
		if (l == null)
			return null;
		LinkedList node = kthToLastC(l.next, k, index);
		index.value++;
		if (index.value == k)
			return l;
		return node;
	}

	// version D -- iterative solution; use 2 pointers, and give the first one a
	// 'lead' of k elements, and then iterate until the first one goes along the
	// list, and the second will be exactly on the k'th to last position
	// O(n)
	public static LinkedList kthToLastD(LinkedList l, int k) {
		LinkedList p1 = l;
		LinkedList p2 = l;
		int i;
		for (i = 0; i < k; i++) {
			p1 = p1.next;
		}

		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList(0);

		LinkedList head = l;
		LinkedList next;
		int i;
		for (i = 0; i < 8; i++) {
			next = new LinkedList(i + 1);
			l.setNext(next);
			// not really needed to be DLL here
			next.setPrev(l);
			l = next;
		}
		System.out.println("The list : \n" + head.print());
		System.out.println("The 3rd to last (A): " + kthToLastA(head, 9, 3).data);
		kthToLastB(head, 7);
		System.out.println("The 4rd to last (C): " + kthToLastC(head, 4).data);
		System.out.println("The 2rd to last (D): " + kthToLastD(head, 2).data);
	}
}
