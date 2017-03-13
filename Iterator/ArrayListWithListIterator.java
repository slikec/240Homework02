package Iterator;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayListWithListIterator <T> implements ListWithListIteratorInterface<T> {
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayListWithListIterator(){
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayListWithListIterator(int initialCapacity){
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
		if (capacity > DEFAULT_CAPACITY) 
			throw new IllegalStateException("Attempt to create " + "a iterator exeeds allowed max of " + DEFAULT_CAPACITY);
	}
	
	
	public ListIterator<T> getIterator(){
		return new ListIteratorForArrayList();
	}
	
	public Iterator<T> iterator(){
		return getIterator();
	}
	
	private class ListIteratorForArrayList implements ListIterator<T>{
		private int nextIndex;
		private boolean isRemoveOrSetLega;
		private Move lastMove;
		
		private ListIteratorForArrayList(){
			nextIndex = 1;
			boolean isRemoveOrSetLegal = false;
			lastMove = null;
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
				lastMove = Move.NEXT;
				boolean isRemoveOrSetLegal = true;
				nextIndex++;
				return nextEntry;
			}
			else
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
		}

		@Override
		public void remove() {
			boolean isRemoveOrSetLegal;
			// TODO Auto-generated method stub
			if (isRemoveOrSetLegal) {
				isRemoveOrSetLegal = false;
				if (lastMove.equals(Move.NEXT)) {
					ArrayListWithListIterator.this.remove(nextIndex - 1);
					nextIndex--;
				}
				else{
					assert lastMove.equals(Move.PREVIOUS);
					ArrayListWithListIterator.this.remove(nextIndex);
				}
			} 
			else 
				throw new IllegalStateException("Illegal call to remove(); " + "next() or prevous() was not called, OR" +
												"add() to remove() called since then.");
		}
	

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return (nextIndex > 1) && ( nextIndex <= numberOfEntries + 1);
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			if(hasPrevious()){
				lastMove = Move.PREVIOUS;
				isRemoveOrSetLegal = true;
				T previousEntry = list[nextIndex - 1];
				nextIndex--;
				return previousEntry;
			}
			else
				throw new NoSuchElementException("Illegal call to previous(); " + "iterator is before beginning of list.");
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			int result;
			if(hasNext())
				result = nextIndex - 1;
			else
				result = numberOfEntries;
			return result;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			int result;
			if(hasPrevious())
				result = nextIndex -2;
			else
				result = -1;
			return result;
		}

		@Override
		public void add(T newEntry) {
			// TODO Auto-generated method stub
			boolean isRemoveOrSetLegal = false;
			ArrayListWithListIterator.this.add(nextIndex, newEntry);
			nextIndex++;
		}

		@Override
		public void set(T newEntry) {
			boolean isRemoveOrSetLegal;
			// TODO Auto-generated method stub
			if(isRemoveOrSetLegal){
				if(lastMove.equals(Move.NEXT))
					list[nextIndex - 1] = newEntry;
				else{
					assert lastMove.equals(move.PREVIOUS);
					list[nextIndex] = newEntry;
				}
			}
			else
				throw new IllegalStateException("Illegal call to set();" + "next() or previous() was not called, OR" + 
														"add() or remove() called since then.");
		}
		
		
	}
	
}
