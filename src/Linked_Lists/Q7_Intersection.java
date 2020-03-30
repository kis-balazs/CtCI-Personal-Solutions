package Linked_Lists;

public class Q7_Intersection {
	/*
	 * 2 SLL's, determine intersection!!
	 */

	// getting the two linked lists the same length just eliminate the excess from
	// the longer one; parallel with this find tails, and check if there could be or
	// intersection; if yes, in case of not immediate return, traverse until
	// identical; in the point of split, the intersection node is found
	// O(n)
	public static LinkedList findIntersection(LinkedList l1, LinkedList l2) {
		if (l1 == null || l2 == null)
			return null;

		Result res1 = getTailAndLength(l1);
		Result res2 = getTailAndLength(l2);

		if (res1.tail.data != res2.tail.data)
			return null;

		LinkedList shorter = res1.length < res2.length ? l1 : l2;
		LinkedList longer = res1.length < res2.length ? l2 : l1;

		longer = getKthNode(longer, Math.abs(res1.length - res2.length));

		while (shorter.data != longer.data) {
			shorter = shorter.next;
			longer = longer.next;
		}
		return shorter;
	}

	public static Result getTailAndLength(LinkedList l) {
		if (l == null)
			return null;

		int size = 1;
		LinkedList tail = l;
		while (tail.next != null) {
			size++;
			tail = tail.next;
		}

		return new Result(tail, size);
	}

	public static LinkedList getKthNode(LinkedList l, int k) {
		LinkedList curr = l;
		int i = 0;
		while (i < k && curr != null) {
			curr = curr.next;
			i++;
		}
		return curr;
	}

	static class Result {
		LinkedList tail;
		int length;

		public Result(LinkedList t, int l) {
			tail = t;
			length = l;
		}
	}

	public static void main(String[] args) {
		int[] a1 = new int[] { 3, 1, 5, 9, 7, 2, 1 };
		int[] a2 = new int[] { 4, 6, 7, 2, 1 };

		LinkedList l1 = new LinkedList(a1[0]);
		LinkedList l2 = new LinkedList(a2[0]);
		LinkedList head1 = l1;
		LinkedList head2 = l2;

		LinkedList next;

		int i;
		for (i = 1; i < a1.length; i++) {
			next = new LinkedList(a1[i]);
			l1.setNext(next);
			l1 = next;
		}

		for (i = 1; i < a2.length; i++) {
			next = new LinkedList(a2[i]);
			l2.setNext(next);
			l2 = next;
		}

		System.out.println("List1 : " + head1.print());
		System.out.println("List2 : " + head2.print());

		try {
			System.out.println("The intersection of the lists : " + findIntersection(head1, head2).data);
		} catch (NullPointerException e) {
			System.out.println("No intersection");
		}
	}
}
