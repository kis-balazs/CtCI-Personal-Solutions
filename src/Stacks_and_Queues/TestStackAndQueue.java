package Stacks_and_Queues;

public class TestStackAndQueue {
	public static void main(String[] args) {
		System.out.println("Stack");
		MyStack<Integer> st = new MyStack<Integer>();
		
		st.push(2);
		st.push(3);
		System.out.println("Top is : " + st.peek());
		int x = st.top();
		System.out.println("top is, take out : " + x);
		System.out.println("Top is : " + st.peek());
		System.out.println("The stack is empty. " + st.isEmpty());
		
		System.out.println("\nQueue");
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		q.add(3);
		q.add(4);
		q.add(5);
		System.out.println("first in : " + q.peek());
		System.out.println("first one out : " + q.remove());
		System.out.println("next first in : " + q.peek());
		System.out.println("The queue is empty. " + q.isEmpty());
	}
}
