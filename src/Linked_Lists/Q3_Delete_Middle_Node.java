package Linked_Lists;

public class Q3_Delete_Middle_Node {
	/*
	 * only node given; SLL; node in the middle, anything besides first and last
	 */

	// only the node is given so just copy the list from the next of the node over
	// to the current node and then delete node
	// o(1)
	public static void deleteNode(LinkedList l) {
		if (l == null || l.next == null)
			return;
		LinkedList next = l.next;
		l.data = next.data;
		l.next = next.next;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList(0);
		LinkedList head = l;
		LinkedList next = l;
		int i;
		for (i = 0; i < 8; i++) {
			next = new LinkedList(i + 1);
			l.setNext(next);
			l = next;
		}
		System.out.println("Initial list : " + head.print());
		deleteNode(head.next.next.next);
		System.out.println("After delete : " + head.print());
	}
}
