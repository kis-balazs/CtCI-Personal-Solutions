package Linked_Lists;

import java.util.Stack;

public class Q6_Palindrome {
	/*
	 * palindrome, LL
	 */

	// version A -- reverse and compare; enough to compare only the half, because if
	// it fits until the half, the other half is already checked by it (pretty hard
	// to implement without knowing the length tho)
	// O(n)
	public static boolean checkPalindromeA(LinkedList l) {
		LinkedList revL = reverse(l);
		return isEqual(l, revL);
	}

	public static LinkedList reverse(LinkedList l) {
		LinkedList head = null;
		while (l != null) {
			LinkedList curr = new LinkedList(l.data);
			curr.next = head;
			head = curr;
			l = l.next;
		}
		return head;
	}

	public static boolean isEqual(LinkedList l, LinkedList revL) {
		while (l != null && revL != null) {
			if (l.data != revL.data)
				return false;
			l = l.next;
			revL = revL.next;
		}
		if (l == null && revL == null)
			return true;
		else
			return false;
	}

	// version B -- use a stack to store first part of the list(keep an eye on the
	// number of elements, the mid element) and then compare to the remaining part
	// to find middle, use the fast(2 by 2) and the slow(1 by 1) runner technique
	// O(n)
	public static boolean checkPalindromeB(LinkedList l) {
		LinkedList fast = l;
		LinkedList slow = l;

		Stack<Integer> stack = new Stack<Integer>();

		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}

		// even number
		if (fast != null)
			slow = slow.next;

		while (slow != null) {
			int top = stack.pop();

			if (top != slow.data)
				return false;
			slow = slow.next;
		}
		return true;
	}

	// version C -- using the recursive pattern of going inside until middle and
	// returning the mirror-indexed elements decision is made each step from inside
	// to outside, from middle; need a class to keep call element and res to
	// comparation with return element
	// O(n)
	static class Result {
		public LinkedList node;
		public boolean res;

		public Result(LinkedList l, boolean val) {
			this.node = l;
			this.res = val;
		}
	}

	public static boolean checkPalindromeC(LinkedList l) {
		int length = length(l);
		Result p = checkPalindromeRecurse(l, length);
		return p.res;
	}

	public static Result checkPalindromeRecurse(LinkedList l, int length) {
		if (l == null || length <= 0)
			return new Result(l, true);
		else if (length == 1)
			return new Result(l.next, true);

		Result res = checkPalindromeRecurse(l.next, length - 2);
		if (res.res == false || res.node == null)
			return res;
		res.res = (l.data == res.node.data);
		res.node = res.node.next;

		return res;
	}

	public static int length(LinkedList l) {
		int length = 0;
		while (l != null) {
			length++;
			l = l.next;
		}
		return length;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 0, 1, 2, 3, 2, 1, 0 };
		LinkedList l = new LinkedList(arr[0]);

		LinkedList head = l;
		LinkedList next = l;
		int i;
		for (i = 1; i < arr.length; i++) {
			next = new LinkedList(arr[i]);
			l.setNext(next);
			l = next;
		}

		System.out.println("The list : " + head.print());
		System.out.println("Q : The list a palindrome. A : " + checkPalindromeA(head));
		System.out.println("Q : The list a palindrome. A : " + checkPalindromeB(head));
		System.out.println("Q : The list a palindrome. A : " + checkPalindromeC(head));
	}
}
