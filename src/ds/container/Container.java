package ds.container;

public interface Container<T> {
	
	public T get();
	
	public void add(T element);

	public int size();
	
	public void clear();
}
