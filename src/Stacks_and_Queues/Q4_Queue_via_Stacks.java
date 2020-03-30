package Stacks_and_Queues;

public class Q4_Queue_via_Stacks {
	/*
	 * 2 stacks; queue
	 */

	// FIFO <-> LIFO; peek() and pop() reversed order; easier solution : keep
	// stackNewest = normal stack; stackOldest = stackNewest 'upside down'; remove
	// oldest : dequeue from stackOldest, insert : push on stackNewest; case of
	// empty oldest : push from newest
	public static class MyQueue<T> {
		MyStack<T> stackNewest, stackOldest;

		public MyQueue() {
			stackNewest = new MyStack<T>();
			stackOldest = new MyStack<T>();
		}

		public int size() {
			return stackNewest.size() + stackOldest.size();
		}

		public void add(T value) {
			stackNewest.push(value);
		}

		// shift as put from the newest to oldest, if necessary
		// O(n)
		public void shiftStacks() {
			if (stackOldest.isEmpty()) {
				while (stackNewest.isEmpty() == false)
					stackOldest.push(stackNewest.top());
			}
		}

		public T peek() {
			shiftStacks();
			return stackOldest.peek();
		}

		public T remove() {
			shiftStacks();
			return stackOldest.top();
		}
	}

	public static void main(String[] args) {
		MyQueue<Integer> q = new MyQueue<Integer>();

		q.add(3);
		q.add(5);
		q.add(8);
		System.out.println("Size : " + q.size());
		System.out.println(q.peek());
		System.out.println(q.remove() + " " + q.peek());
		q.add(14);
		System.out.println("Size : " + q.size() + "\n" + q.remove());
	}
}
