package Linked_Lists;

public class Q8_Loop_Detection {
	/*
	 * circular LL
	 */

	// use the technique of the Slow(1 by 1)/Fast(2 by 2) runners' technique;
	// collision guaranteed, because after each circle(once entered the loop, only
	// in the loop) the Fast gets one closer to the Slow, from behind; when
	// collision, move the Slow at the head of the list, and run both of the 1 by 1,
	// and the collision will be the loop-node
	// O(n)
	public static LinkedList findLoopNode(LinkedList l) {
		LinkedList slow = l;
		LinkedList fast = l;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow.data == fast.data)
				break;
		}

		if (fast == null || fast.next == null)
			return null;

		slow = l;
		while (slow.data != fast.data) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 5, 6, 7, 8, 9, 10, 6 };
		LinkedList[] l = new LinkedList[arr.length];

		int i;
		for (i = 0; i < arr.length; i++) {
			l[i] = new LinkedList(arr[i]);
			if (i != 0)
				l[i].setPrev(l[i - 1]);
		}
		l[arr.length - 1].next = l[2];

		// just can't print the list. double 'next' collision from the normal LL and the
		// loop end back into the list
		//System.out.println("The list is : " + l[0].print());
		try {
			System.out.println("The loop node is : " + findLoopNode(l[0]).data);
		} catch (NullPointerException e) {
			System.out.println("No loop node");
		}
	}

}
