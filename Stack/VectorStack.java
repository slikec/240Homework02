package Stack;

import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack <T> implements StackInterface<T>{
	private Vector<T> stack;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;
	
	public VectorStack(){
		this(DEFAULT_CAPACITY);
	}
	
	public VectorStack(int initialCapacity){
		checkCapacity(initialCapacity);
		stack = new Vector<>(initialCapacity);
		initialized = true;
	}


	@Override
	public void push(T newEntry) {
		checkInitialization();
		stack.add(newEntry);
	}

	@Override
	public T pop() {
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack.remove(stack.size()-1);
	}

	@Override
	public T peek() {
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack.lastElement();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		stack.clear();
	}
	
	private void checkInitialization() {
		if (!initialized) 
			throw new SecurityException("ArrayBag is not initialized properly.");
	}
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) 
			throw new IllegalStateException("Attempt to create"
					+ " a bag exeeds allowed max of " + MAX_CAPACITY);
	}		
	
}
