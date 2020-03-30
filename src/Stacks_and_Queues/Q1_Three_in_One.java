package Stacks_and_Queues;

import java.util.EmptyStackException;

public class Q1_Three_in_One {
	/*
	 * multiple stacks using only one array - purpose of the exercise, to embrace
	 * fixed/flexible solutions, memory waste against increased complexity
	 * implemented totally is a very complicated problem
	 */

	// version A -- divide exactly in 3 the array and use 3 separate stacks, not
	// caring about the wasted memory in case of using one full and the others
	// aren't used at all; the memory split decision could fit a special need, if
	// mentioned in the problem
	class FullStackException extends Exception {
		public FullStackException(String msg) {
			super(msg);
		}
	}

	class FixedMultiStack {
		private int noOfStacks = 3;
		private int stackCap; // fullCap / noOfStacks
		private int[] values;
		private int[] sizes;

		public FixedMultiStack(int stackSize) {
			stackCap = stackSize;
			values = new int[stackSize * noOfStacks];
			sizes = new int[noOfStacks];
		}

		public void push(int stackNum, int value) throws FullStackException {
			if (isFull(stackNum))
				throw new FullStackException("Stack is full!");
			sizes[stackNum]++;
			values[indexOfTop(stackNum)] = value;
		}

		public int pop(int stackNum) {
			if (isEmpty(stackNum))
				throw new EmptyStackException();

			int topIndex = indexOfTop(stackNum);
			int value = values[topIndex];
			values[topIndex] = 0;
			sizes[stackNum]--;
			return value;
		}

		public int peek(int stackNum) {
			if (isEmpty(stackNum))
				throw new EmptyStackException();
			return values[indexOfTop(stackNum)];
		}

		public boolean isFull(int stackNum) {
			return sizes[stackNum] == stackCap;
		}

		public boolean isEmpty(int stackNum) {
			return sizes[stackNum] == 0;
		}

		public int indexOfTop(int stackNum) {
			int offset = stackNum * stackCap;
			int size = sizes[stackNum];
			return offset - size + 1;
		}
	}

	// version B -- create a flexible splitting of the array, and if using a stack,
	// and exceeding the space, just grow capacity and shift elements if necessary;
	// using a circular array, using this specificity for the last stack, which may
	// start at the end of the array and wrap around to the beginning
	class MultiStack {
		// only keeping data about each stack, not the actual items
		private class StackInfo {
			public int start, size, capacity;

			public StackInfo(int start, int capacity) {
				this.start = start;
				this.capacity = capacity;
			}

			public boolean isWithinStackCap(int index) {
				if (index < 0 || index >= values.length)
					return false;
				return true;
			}

			public int lastCapacityIndex() {
				return adjustIndex(start + capacity - 1);
			}

			public int lastElementIndex() {
				return adjustIndex(start + size - 2);
			}

			public boolean isFull() {
				return size == capacity;
			}

			public boolean isEmpty() {
				return size == 0;
			}
		}

		private StackInfo[] info;
		private int[] values;

		public MultiStack(int noOfStacks, int defaultSize) {
			info = new StackInfo[noOfStacks];
			int i;
			for (i = 0; i < noOfStacks; i++) {
				info[i] = new StackInfo(defaultSize * i, defaultSize);
			}
			values = new int[noOfStacks * defaultSize];
		}

		public void push(int stackNum, int value) throws FullStackException {
			if (allStacksAreFull())
				throw new FullStackException("Stacks are full!");

			StackInfo stack = info[stackNum];
			if (stack.isFull())
				expand(stackNum);

			stack.size++;
			values[stack.lastElementIndex()] = value;
		}

		public int pop(int stackNum) {
			StackInfo stack = info[stackNum];
			if (stack.isEmpty())
				throw new EmptyStackException();

			int value = values[stack.lastElementIndex()];
			values[stack.lastElementIndex()] = 0;
			stack.size--;
			return value;
		}

		public int peek(int stackNum) {
			StackInfo stack = info[stackNum];
			return values[stack.lastElementIndex()];
		}

		// shift elements over by one element; if available capacity, end up shrinking
		// stack by one; if not, the next stack needs to be shifted too
		private void shift(int stackNum) {
			System.out.println("Shifting " + stackNum);
			StackInfo stack = info[stackNum];

			// if stack is full capacity, move the next stack over by one element
			if (stack.size >= stack.capacity) {
				int nextStack = (stackNum + 1) % info.length;
				shift(nextStack);
				stack.capacity++;
			}

			int index = stack.lastCapacityIndex();
			while (stack.isWithinStackCap(index)) {
				values[index] = values[previousIndex(index)];
				index = previousIndex(index);
			}

			values[stack.start] = 0;
			stack.start = nextIndex(stack.start);
			stack.capacity--;
		}

		private void expand(int stackNum) {
			shift((stackNum + 1) % info.length);
		}

		public int numberOfElements() {
			int size = 0;
			for (StackInfo si : info) {
				size += si.size;
			}
			return size;
		}

		public boolean allStacksAreFull() {
			return numberOfElements() == values.length;
		}

		// range 0 -> length - 1
		private int adjustIndex(int index) {
			int max = values.length;
			return ((index % max) + max) % max;
		}

		private int nextIndex(int index) {
			return adjustIndex(index + 1);
		}

		private int previousIndex(int index) {
			return adjustIndex(index - 1);
		}
	}
}
