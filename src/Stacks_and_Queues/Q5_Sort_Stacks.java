package Stacks_and_Queues;

public class Q5_Sort_Stacks {
	/*
	 * smaller items top; can use only one additional stack, performing basic
	 * operations
	 * 
	 * if one can utilize unlimited : implement qsort, mergesort; mergesort = two
	 * additional stacks, divide stack into two, rec. sort each stack, merge back.
	 * needs 2 additional stacks per level; qsort = 2 add. stacks, divide the stack
	 * in two, based on pivot, these rec. sorted, and merged back together. needs 2
	 * add. stacks per level
	 */

	// pop off top, save in a variable, and put into the right place in the other
	// stack, after moving everything bigger
	// O(n ^ 2) -- linear space stack
	public static void sortStack(MyStack<Integer> s) {
		MyStack<Integer> r = new MyStack<Integer>();

		while (s.isEmpty() == false) {
			int tmp = s.top();
			while (r.isEmpty() == false && r.peek() > tmp)
				s.push(r.top());
			r.push(tmp);
		}
		while (r.isEmpty() == false)
			s.push(r.top());
	}

	public static void main(String[] args) {
		MyStack<Integer> s = new MyStack<Integer>();
		s.push(5);
		s.push(3);
		s.push(6);
		s.push(9);
		sortStack(s);

		while (s.isEmpty() == false)
			System.out.println(s.top());
	}

}
