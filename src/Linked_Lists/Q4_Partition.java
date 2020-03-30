package Linked_Lists;

public class Q4_Partition {
	/*
	 * partition of LL; stability??
	 */

	// version A -- create 2 lists in which keep lower/higher values than given x,
	// insert item from original in the correct sub-list; then merge; stable
	// needs 4 variables in order to keep the 2 sub-lists
	// approach
	// O(n)
	public static LinkedList partitionA(LinkedList l, int x) {
		// keep for separating in lists
		LinkedList beforeStart = null;
		LinkedList beforeEnd = null;
		LinkedList afterStart = null;
		LinkedList afterEnd = null;

		while (l != null) {
			LinkedList next = l.next;
			l.next = null;
			if (l.data < x) {
				// into end of before list(init)
				if (beforeStart == null) {
					beforeStart = l;
					beforeEnd = beforeStart;
				}
				// after init continue normally, putting in end
				else {
					beforeEnd.next = l;
					beforeEnd = l;
				}
			} else {
				// into end of after list(init)
				if (afterStart == null) {
					afterStart = l;
					afterEnd = afterStart;
				}
				// after init continue normally, putting in end
				else {
					afterEnd.next = l;
					afterEnd = l;
				}
			}
			l = next;
		}

		if (beforeStart == null)
			return afterStart;

		beforeEnd.next = afterStart;
		return beforeStart;
	}

	// version B -- version A shorter; a new list and insert smaller(head) and
	// bigger(tail) plus update the appropriate one
	// O(n)
	public static LinkedList partitionB(LinkedList l, int x) {
		LinkedList head = l;
		LinkedList tail = l;

		while (l != null) {
			LinkedList next = l.next;
			if (l.data < x) {
				l.next = head;
				head = l;
			} else {
				tail.next = l;
				tail = l;
			}
			l = next;
		}
		tail.next = null;

		return head;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList(0);
		LinkedList head = l;
		LinkedList next = l;
		int i;
		for (i = 0; i < 9; i++) {
			next = new LinkedList((i + 1) % 3);
			l.setNext(next);
			l = next;
		}
		System.out.println("The list : " + head.print());
		head = partitionA(head, 2);
		// head = partitionB(head, 2);
		System.out.println("The list after partitioning" + ": " + head.print());
	}
}
