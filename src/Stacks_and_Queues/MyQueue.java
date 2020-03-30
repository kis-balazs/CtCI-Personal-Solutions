package Stacks_and_Queues;

import java.util.NoSuchElementException;

public class MyQueue<T> {
	/*
	 * FIFO; add(item), remove(), peek(), isEmpty()
	 * BFS, cache
	 */
	private static class QueueNode<T> {
		private T data;
		private QueueNode<T> next;
		
		public QueueNode(T data) {
			this.data = data;
		}
	}
	
	public QueueNode<T> first;
	public QueueNode<T> last;
	
	public void add(T item) {
		QueueNode<T> t = new QueueNode<T>(item);
		if (last != null)
			last.next = t;
		last = t;
		if (first == null)
			first = last;
	}
	
	public T remove() {
		if (first == null) throw new NoSuchElementException();
		T data = first.data;
		first = first.next;
		if (first == null)
			first = last;
		return data;
	}
	
	public T peek() {
		if (first == null) throw new NoSuchElementException();
		return first.data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}
