package Linked_Lists;

public class LinkedList {
	public LinkedList next;
	public LinkedList prev;
	public int data;

	public LinkedList(int dt) {
		data = dt;
	}

	public LinkedList() {
	}

	public void setNext(LinkedList l) {
		next = l;
		if (l != null && l.prev != this) {
			l.setPrev(this);
		}
	}

	public void setPrev(LinkedList l) {
		prev = l;
		if (l != null && l.next != this)
			l.setNext(this);
	}

	public String print() {
		if (next != null)
			return data + " -> " + next.print();
		else
			return ((Integer) data).toString();
	}

}
