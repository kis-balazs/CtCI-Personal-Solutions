package Linked_Lists;

public class Q5_Sum_Lists {
	/*
	 * addition, LL order of the digits of the numbers
	 */

	// part A -- reversed order of the digits; addition, and from head to tail just
	// add, add to the next addition the carry from the previous addition
	// O(n)
	public static LinkedList addListsA(LinkedList l1, LinkedList l2, int carry) {
		if (l1 == null && l2 == null && carry == 0)
			return null;

		LinkedList res = new LinkedList();
		int value = carry;
		if (l1 != null) {
			value += l1.data;
		}
		if (l2 != null) {
			value += l2.data;
		}
		res.data = value % 10;
		if (l1 != null || l2 != null) {
			LinkedList next = addListsA(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
			res.setNext(next);
		}
		return res;
	}

	// version B -- numbers are stored in the right order; solve the different
	// length with padding with 0's; recursive call should return result and carry;
	// keep in a wrapper class
	// O(n)
	static class PartialSum {
		public LinkedList sum = null;
		public int carry = 0;
	}

	public static LinkedList addListsB(LinkedList l1, LinkedList l2) {
		int len1 = length(l1);
		int len2 = length(l2);

		if (len1 < len2)
			l1 = padList(l1, len2 - len1);
		else
			l2 = padList(l2, len1 - len2);

		PartialSum sum = addListsHelper(l1, l2);
		if (sum.carry == 0) {
			return sum.sum;
		} else {
			LinkedList res = insertBefore(sum.sum, sum.carry);
			return res;
		}
	}

	public static LinkedList padList(LinkedList l, int padding) {
		LinkedList head = l;
		int i;
		for (i = 0; i < padding; i++) {
			head = insertBefore(head, 0);
		}
		return head;
	}

	public static LinkedList insertBefore(LinkedList l, int data) {
		LinkedList node = new LinkedList(data);
		if (l != null)
			node.next = l;
		return node;
	}

	public static int length(LinkedList l) {
		int length = 0;
		while (l != null) {
			length++;
			l = l.next;
		}
		return length;
	}

	public static PartialSum addListsHelper(LinkedList l1, LinkedList l2) {
		if (l1 == null && l2 == null) {
			PartialSum sum = new PartialSum();
			return sum;
		}
		PartialSum sum = addListsHelper(l1.next, l2.next);

		int val = sum.carry + l1.data + l2.data;
		LinkedList full_res = insertBefore(sum.sum, val % 10);

		sum.sum = full_res;
		sum.carry = val / 10;
		return sum;

	}

	public static void main(String[] args) {
		int[] a1 = new int[] { 6, 1, 7 };
		int[] a2 = new int[] { 2, 1, 4 };

		LinkedList l1 = new LinkedList(a1[a1.length - 1]);
		LinkedList l2 = new LinkedList(a2[a2.length - 1]);
		LinkedList head1 = l1;
		LinkedList head2 = l2;

		LinkedList next;

		int i;
		for (i = 1; i < a1.length; i++) {
			next = new LinkedList(a1[a1.length - i - 1]);
			l1.setNext(next);
			l1 = next;
		}

		for (i = 1; i < a2.length; i++) {
			next = new LinkedList(a2[a2.length - i - 1]);
			l2.setNext(next);
			l2 = next;
		}

		System.out.println("List1 : " + head1.print());
		System.out.println("List2 : " + head2.print());
		System.out.println("Addition of the List1 & List2(read backwards) : " + addListsA(head1, head2, 0).print());
		System.out.println("Addition of the List1 & List2 : " + addListsB(head1, head2).print());
	}
}
