package Stacks_and_Queues;

import java.util.*;

public class Q3_Stack_of_Plates {
	/*
	 * SetOfStacks + popAt
	 */

	public static class Stack {
		public class Node {
			public Node above;
			public Node below;
			public int value;

			public Node(int value) {
				this.value = value;
			}
		}

		private int capacity;
		public Node top;
		public Node bottom;
		public int size = 0;

		public Stack(int capacity) {
			this.capacity = capacity;
		}

		public boolean isFull() {
			return capacity == size;
		}

		public void join(Node above, Node below) {
			if (below != null)
				below.above = above;
			if (above != null)
				above.below = below;
		}

		public boolean push(int v) {
			if (size >= capacity)
				return false;
			size++;
			Node n = new Node(v);
			if (size == 1)
				bottom = n;
			join(n, top);
			top = n;
			return true;
		}

		public int pop() {
			if (top == null)
				throw new EmptyStackException();
			Node t = top;
			top = top.below;
			size--;
			return t.value;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public int removeBottom() {
			Node b = bottom;
			bottom = bottom.above;
			if (bottom != null)
				bottom.below = null;
			size--;
			return b.value;
		}
	}

	// push -- behave as a normal push, but careful to the filling of it, and create
	// a new stack to continue to push
	// popAt -- if one top is removed, the bottom of the next should replace the
	// first one top, and a rollover so and so forth
	static public class SetOfStacks {
		ArrayList<Stack> al = new ArrayList<Stack>();
		public int capacity;

		public SetOfStacks(int capacity) {
			this.capacity = capacity;
		}

		public Stack getLastStack() {
			if (al.size() == 0) {
				return null;
			}
			return al.get(al.size() - 1);
		}

		public void push(int v) {
			Stack last = getLastStack();
			if (last != null && !last.isFull()) { // add to last
				last.push(v);
			} else { // must create new stack
				Stack stack = new Stack(capacity);
				stack.push(v);
				al.add(stack);
			}
		}

		public int pop() {
			Stack last = getLastStack();
			if (last == null)
				throw new EmptyStackException();
			int v = last.pop();
			if (last.size == 0) {
				al.remove(al.size() - 1);
			}
			return v;
		}

		public int popAt(int index) {
			return leftShift(index, true);
		}

		// O(n)
		public int leftShift(int index, boolean removeTop) {
			Stack stack = al.get(index);
			int rem;
			if (removeTop)
				rem = stack.pop();
			else
				rem = stack.removeBottom();
			if (stack.isEmpty()) {
				al.remove(index);
			} else if (al.size() > index + 1) {
				int v = leftShift(index + 1, false);
				stack.push(v);
			}
			return rem;
		}

		public boolean isEmpty() {
			Stack last = getLastStack();
			return last == null || last.isEmpty();
		}
	}

	public static void main(String[] args) {
		int cSubstack = 5;
		SetOfStacks set = new SetOfStacks(cSubstack);
		for (int i = 0; i < 34; i++) {
			set.push(i);
		}
		for (int i = 0; i < 35; i++) {
			System.out.println("Popped " + set.pop());
		}

	}
}
