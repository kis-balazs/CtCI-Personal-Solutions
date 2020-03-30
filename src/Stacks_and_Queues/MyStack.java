package Stacks_and_Queues;

import java.util.EmptyStackException;

public class MyStack<T> {
	/*
	 * LIFO; pop(), push(item), peek(), isEmpty()
	 * recursive => iterative
	 */
	private static class StackNode<T> {
		private T data;
		private StackNode<T> next;

		public StackNode(T data) {
			this.data = data;
		}
	}

	private StackNode<T> top;

	public T top() {
		if (top == null)
			throw new EmptyStackException();
		T item = top.data;
		top = top.next;
		return item;
	}

	public void push(T item) {
		StackNode<T> t = new StackNode<T>(item);
		t.next = top;
		top = t;
	}

	public T peek() {
		if (top == null)
			throw new EmptyStackException();
		return top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}
	
	//added for problem 4
	public int size() {
		StackNode<T> walker = top;
		int size = 0;
		while (walker != null)
		{
			size++;
			walker = walker.next;
		}
		return size;
	}
	
}