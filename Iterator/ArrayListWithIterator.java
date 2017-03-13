package Iterator;

import java.util.NoSuchElementException;

public class ArrayListWithIterator<T> implements ListWithIteratorInterface<T> {
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayListWithIterator(){
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayListWithIterator(int initialCapacity){
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempList = (T[])new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
		}

	private void checkCapacity(int capacity) {
		// TODO Auto-generated method stub
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose capacity exeds allowed maximum of " + MAX_CAPACITY);
	}//end checkCapacity

	@Override
	public void add(T newEntry) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void add(int newPosition, T newEntry) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new IteratorForArrayList();
	}

	@Override
	public Iterator<T> getIterator() {
		// TODO Auto-generated method stub
		return iterator();	
	}
	
	private class IteratorForArrayList implements Iterator<T> {
		private int nextIndex;
		private boolean wasNextCalled;
		
		private IteratorForArrayList() {
			nextIndex = 1;
			wasNextCalled = false;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextIndex <= numberOfEntries;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(hasNext()){
				wasNextCalled = true;
				T nextEntry = list[nextIndex];
				nextIndex++;
				return nextEntry;
			}
			else
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
		}//end next

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(wasNextCalled){
				ArrayListWithIterator.this.remove(nextIndex - 1);
				nextIndex--;
				wasNextCalled = false;
			}
			else
				throw new IllegalStateException("Illegal call to remove();" + "next() was not called.");
		}
	}
	
}

