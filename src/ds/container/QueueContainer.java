package ds.container;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueContainer<T> implements Container<T> {
	
	private Queue<T> queue;
	
	public QueueContainer() {
		this.queue = new LinkedList<>();
	}
	
	public QueueContainer(Comparator<T> comparator) {
		this.queue = new PriorityQueue<>(comparator);
	}
	
	public QueueContainer(Queue<T> queue) {
		this.queue = queue;
	}
	
	@Override
	public T get() {
		return queue.poll();
	}

	@Override
	public void add(T element) {
		queue.add(element);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public void clear() {
		this.queue.clear();
	}

}
