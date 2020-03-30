package Linked_Lists;

public class TestLinkedList {
	public static void main(String[] args) {
		int[] arr = new int[] { 4, 5, 6, 7, 8, 9, 10, 6 };
		LinkedList[] l1 = new LinkedList[arr.length];

		int i;
		for (i = 0; i < arr.length; i++) {
			l1[i] = new LinkedList(arr[i]);
			if (i != 0)
				l1[i].setPrev(l1[i - 1]);
		}
		
		System.out.println("The list is : " + l1[0].print());
		
		arr = new int[] { 0, 1, 2, 3, 2, 1, 0 };
		LinkedList l2 = new LinkedList(arr[0]);

		LinkedList head = l2;
		LinkedList next = l2;
		for (i = 1; i < arr.length; i++) {
			next = new LinkedList(arr[i]);
			l2.setNext(next);
			l2 = next;
		}

		System.out.println("The list : " + head.print());
	}
}
