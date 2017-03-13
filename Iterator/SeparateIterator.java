package Iterator;

import java.util.NoSuchElementException;
import List.ListInterface;

public class SeparateIterator<T> implements Iterator<T> {
	private ListInterface<T> list;
	private int nextPosition;
	private boolean wasNextCalled;
	
	public SeparateIterator(ListInterface<T> myList){
		list = myList;
		nextPosition = 0;
		wasNextCalled = false;
	}//end constructor

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return nextPosition < list.getLength();
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(hasNext()){
			wasNextCalled = true;
			nextPosition++;
			return list.getEntry(nextPosition);
		}
		else
			throw new NoSuchElementException("Illegal call to next();" + "iterator is after end of list.");
	}//end next

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		if (wasNextCalled) {
			list.remove(nextPosition);
			nextPosition--;
			
			wasNextCalled = false;
		} 
		else
			throw new IllegalStateException("Illegal call to remove() " + "next() was not called.");
	}//end remove	
}
