package Linked_Lists;

import java.util.HashSet;

public class Q1_Remove_Dups {
	/*
	 * LinkedList
	 */

	// version A -- to know duplicates, must save somewhere(HashSet). If duplicate,
	// remove, and continue(can do in one pass, since LL)
	// O(n)
	public static void deleteDupsA(LinkedList l) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedList prev = null;
		while (l != null) {
			if (set.contains(l.data)) {
				prev.next = l.next;
			} else {
				set.add(l.data);
				prev = l;
			}
			l = l.next;
		}
	}

	// version B -- no buffer allowed; use 2 pointers: current - iterating through
	// LL, and runner check subsequent duplicates from current, skip duplicates,
	// load back to current, continue
	// O(n ^ 2) - O(1) space
	public static void deleteDupsB(LinkedList l) {
		LinkedList curr = l;
		while (curr != null) {
			LinkedList runner = curr;
			while (runner.next != null) {
				if (runner.next.data == curr.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			curr = curr.next;
		}
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList(0);

		LinkedList head = l;
		LinkedList second = l;
		int i;
		for (i = 1; i < 8; i++) {
			second = new LinkedList(i % 4);
			l.setNext(second);
			second.setPrev(l);
			l = second;
		}
		System.out.println("The list :");
		System.out.println(head.print());
		//deleteDupsA(head);
		deleteDupsB(head);
		System.out.println("After deleting duplicates :");
		System.out.println(head.print());
	}
}
