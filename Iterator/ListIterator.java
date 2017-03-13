package Iterator;

public interface ListIterator<T> extends Iterator<T>{
	public boolean hasNext();
	/**
	Retrieves the next entry in the list and
	advances this iterator by one position.
	@return  A reference to the next entry in the iteration,
	         if one exists.
	@throws  NoSuchElementException if the iterator had reached the
	         end already, that is, if hasNext() is false. */
	public T next();

	public void remove();
	
	public boolean hasPrevious();
	
	public T previous();
	
	public int nextIndex();
	
	public int previousIndex();
	
	public void add(T newEntry);
	
	public void set(T newEntry);
	
}//end ListIterator
