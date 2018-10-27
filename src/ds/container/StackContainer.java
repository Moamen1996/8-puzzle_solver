package ds.container;

import java.util.Stack;

public class StackContainer<T> implements Container<T> {

	private Stack<T> stack;
	
	public StackContainer() {
		this.stack = new Stack<>();
	}
	
	public StackContainer(Stack<T> stack) {
		this.stack = stack;
	}
	
	@Override
	public T get() {
		return stack.pop();
	}

	@Override
	public void add(T element) {
		stack.add(element);	
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void clear() {
		this.stack.clear();
	}

}
