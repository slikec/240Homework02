package List;

import java.util.Arrays;


public class VectorsList<T> implements ListInterface<T>{
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 100;
	
	public VectorsList(){
		this(DEFAULT_CAPACITY);
	}//end default constructor

	public VectorsList(int initialCapacity) {
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempList= (T[])new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	}
		public void add(T newEntry){
			checkInitialization();
			list[numberOfEntries + 1] = newEntry;
			numberOfEntries++;
			ensureCapacity();
		}

	@Override
	public void add(int newPosition, T newEntry) {
		checkInitialization();
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
		if (newPosition <= numberOfEntries) makeRoom(newPosition);
		          list[newPosition] = newEntry;
		          numberOfEntries++;
		          ensureCapacity(); // Ensure enough room for next add
		}
		else
		throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	}//end add

	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		
		for(int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}//end makeRoom

	@Override
	public T remove(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
		assert !isEmpty();
		T result = list[givenPosition]; // Get entry to be removed
		// Move subsequent entries toward entry to be removed, // unless it is last in list
		if (givenPosition < numberOfEntries)
		      removeGap(givenPosition);
		   numberOfEntries--;
		return result;
		}// Return reference to removed entry
		else
		throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	}//end remove

	
	
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries); int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
		list[index] = list[index + 1];
	}//end removeGap

	@Override
	public void clear() {
		while (!isEmpty());
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
		assert !isEmpty();
		T originalEntry = list[givenPosition]; list[givenPosition] = newEntry;
		return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}//end replace

	@Override
	public T getEntry(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
		assert !isEmpty();
		return list[givenPosition]; }
		else
		throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}

	@Override
	public T[] toArray() {
		checkInitialization();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for(int index = 0; index < numberOfEntries; index++){
			result[index] = list[index + 1];
		}//end for
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		checkInitialization();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries)) {
			if (anEntry.equals(list[index])) 
				found = true;
			index++;
		} // end while
		return found;
	}//end contains

	@Override
	public int getLength() {
		return numberOfEntries;
	}//end getLength

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}//end isEmpty
	
	private void ensureCapacity(){
		int capacity = list.length - 1;
		if(numberOfEntries <= capacity){
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list = Arrays.copyOf(list,  newCapacity + 1);
		}//end if
	}//end ensureCapacity


	private void checkCapacity(int newCapacity) {
		if (newCapacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose" + "capacity exeeds allowed" + "maximum of" + MAX_CAPACITY);		
	}//end checkCapacity

	private void checkInitialization() {
		if (!initialized) 
			throw new SecurityException("VectorList is not initialized properly.");
	}
}//end VectorsList
