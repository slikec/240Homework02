package List;
import java.util.Arrays;

public class ArrayList<T> implements ListInterface<T>  {
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 100;
	
	public ArrayList(){
		this(DEFAULT_CAPACITY); //Call next constructor
	}//end default constructor
	
	public ArrayList(int newCapacity){
		if(newCapacity < DEFAULT_CAPACITY)
			newCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(newCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[newCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	}//end constructor
	
	public void add(T newEntry){
		checkInitialization();
		list[numberOfEntries + 1] = newEntry;//or add(numberOfEntries + 1, newEntry);
		numberOfEntries++;
		ensureCapacity();
	}//end add

	@Override
	public void add(int newPosition, T newEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		if((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
			if(newPosition <= numberOfEntries)
				makeRoom(newPosition);
			list[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity();//Ensure enough room for next add
		}
		else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	}//end add

	private void makeRoom(int newPosition) {
		// TODO Auto-generated method stub
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		
		for(int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}//end makeRoom

	@Override
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		checkInitialization();
		if((givenPosition >=1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			T result = list[givenPosition];
			
			if(givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result;//Return reference to removed entry
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	}//end remove
	
	private void removeGap(int givenPosition) {
		// TODO Auto-generated method stub
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries); 
		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty())
			remove(numberOfEntries);
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
		assert !isEmpty();
		T originalEntry = list[givenPosition]; 
		list[givenPosition] = newEntry;
		return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}//end replace

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return list[givenPosition]; 
			}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}//end getEntry

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		checkInitialization();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++){
			result[index] = list[index + 1];
		}//end for
		
		return result;
	}//end toArray

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return numberOfEntries;
	}//end getLength

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries == 0;// or GetLength() == 0
	}//end isEmpty
	
	private void ensureCapacity(){
		int capacity = list.length -1;
		if(numberOfEntries >=capacity){
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list = Arrays.copyOf(list,  newCapacity + 1);
		}//end if
	}//end ensureCapacity

	private void checkCapacity(int newCapacity) {
		if (newCapacity > MAX_CAPACITY)
			 throw new IllegalStateException("Attempt to create a bag whose " + "newCapacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
	}//end checkCapacity
	
	private void checkInitialization() {
		// TODO Auto-generated method stub
		if (!initialized)
			 throw new SecurityException("ArrayBag object is not initialized " + "properly.");
	}//end checkInitialization
	
}
