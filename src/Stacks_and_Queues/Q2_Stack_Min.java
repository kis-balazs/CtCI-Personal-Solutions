package Stacks_and_Queues;

public class Q2_Stack_Min {
	/*
	 * every time return stack min in O(1)
	 */

	// version A -- keep track of the minimum at each time; each node record what
	// the minimum beneath it is; so the min is the min of the top
	// keeping a lot of integers in any case
	static class StackWithMinA extends MyStack<NodeWithMin> {
		public void push(int value) {
			int newMin = Math.min(value, min());
			super.push(new NodeWithMin(value, newMin));
		}

		public int min() {
			if (this.isEmpty()) {
				return Integer.MAX_VALUE;
			}
			return peek().min;
		}
	}

	static class NodeWithMin {
		public int value;
		public int min;

		public NodeWithMin(int value, int min) {
			this.value = value;
			this.min = min;
		}
	}

	// version B -- additional stack to keep track of the minimum, keeping a few
	// values in the stack as minimum
	static class StackWithMinB extends MyStack<Integer> {
		MyStack<Integer> st;

		public StackWithMinB() {
			st = new MyStack<Integer>();
		}

		public void push(int value) {
			if (value <= min())
				st.push(value);
			super.push(value);
		}

		public Integer pop() {
			int value = super.top();
			if (value == min())
				st.top();
			return value;
		}

		public int min() {
			if (st.isEmpty())
				return Integer.MAX_VALUE;
			else
				return st.peek();
		}
	}

	public static void main(String[] args) {
		StackWithMinA stack = new StackWithMinA();
		StackWithMinB stack2 = new StackWithMinB();

		int[] array = { 6, 3, 5 };
		for (int value : array) {
			stack.push(value);
			stack2.push(value);
			System.out.print(value + ", ");
		}
		System.out.println('\n');
		for (int i = 0; i < array.length; i++) {
			System.out.println("Popped " + stack.top().value + ", " + stack2.pop());
			System.out.println("New min is " + stack.min() + ", " + stack2.min());
		}
	}
}
