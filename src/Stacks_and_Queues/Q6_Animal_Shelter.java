package Stacks_and_Queues;

import java.util.LinkedList;

public class Q6_Animal_Shelter {
	/*
	 * FIFO; build-in LinkedList
	 */

	// single queue, and create dequeueAny easy, but separate dequeue complicated

	// separate queues, and use a wrapper class AnimalQ; timestamp, dequeueAny :
	// comparation between peeks of two lists
	// maybe actual timeStamp
	abstract static class Animal {
		private int ts;
		protected String name;

		public Animal(String name) {
			this.name = name;
		}

		public void setTimestamp(int timestamp) {
			this.ts = timestamp;
		}

		public int getTimestamp() {
			return this.ts;
		}

		// check in case of the two heads the timestamp
		public boolean isOlder(Animal a) {
			return this.ts < a.getTimestamp();
		}
	}

	static class AnimalQ {
		LinkedList<Dog> dogs = new LinkedList<Dog>();
		LinkedList<Cat> cats = new LinkedList<Cat>();
		int ts = 0;

		public void enqueue(Animal a) {
			// mark by coming timestamp
			a.setTimestamp(ts++);

			// insert in the proper LL
			if (a instanceof Dog)
				dogs.addLast((Dog) a);
			else
				cats.addLast((Cat) a);
		}

		public Dog dequeueDogs() {
			return dogs.poll();
		}

		public Cat dequeueCats() {
			return cats.poll();
		}

		public Animal dequeueAny() {
			if (dogs.isEmpty() == true)
				return (Cat) cats.poll();
			else if (cats.isEmpty() == true)
				return (Dog) dogs.poll();

			Dog lastDog = dogs.peek();
			Cat lastCat = cats.peek();
			if (lastDog.isOlder(lastCat))
				return dequeueDogs();
			else
				return dequeueCats();
		}

	}

	public static class Dog extends Animal {
		public Dog(String name) {
			super(name);
		}
	}

	public static class Cat extends Animal {
		public Cat(String name) {
			super(name);
		}
	}

	public static void main(String[] args) {
		AnimalQ a = new AnimalQ();

		a.enqueue(new Dog("Lajka"));
		a.enqueue(new Dog("Vakarcs"));
		a.enqueue(new Cat("Sziamiau"));
		a.enqueue(new Dog("Piszkiri"));
		a.enqueue(new Cat("Cirmos"));

		System.out.println("Any from shelter : " + a.dequeueAny().name);
		System.out.println("A cat from shelter : " + a.dequeueCats().name);
		System.out.println("A dog from shelter : " + a.dequeueDogs().name);
		System.out.println("Any from shelter : " + a.dequeueAny().name);
	}
}
