package Queue;

public class ArrayQueue<T> implements QueueInterface<T>{
	private T[] queue; //Circular array of queue entries and one unused location
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	
	public ArrayQueue(){
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayQueue(int initialCapacity){
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
	} // end constructor
	
	@Override
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}//end enqueue

	@Override
	public T dequeue() {
		checkInitialization();
		 if (isEmpty())
		 throw new EmptyQueueException();
		 else
		 {
		 T front = queue[frontIndex];
		 queue[frontIndex] = null;
		 frontIndex = (frontIndex + 1) % queue.length;
		 return front;
		 }
	}

	@Override
	public T getFront() {
		checkInitialization();
		 if (isEmpty())
			 throw new EmptyQueueException();
			 else
			 return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
	return frontIndex == ((backIndex+1) % queue.length);
	}//end isEmpty

	@Override
	public void clear() {
		if (!isEmpty()) {
			dequeue();
		} // end if 
		frontIndex = 0;
		backIndex = queue.length - 1;
	}//end clear
	
	private void checkCapacity(int capacity) {
		if (capacity > DEFAULT_CAPACITY) 
			throw new IllegalStateException("Attempt to cre"
					+ "ate a bag exeeds allowed max of " + DEFAULT_CAPACITY);

	}
	
	private void checkInitialization() {
		if (!initialized) 
			throw new SecurityException("ArrayBag is not initialized properly.");
	}
	
	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) // If array is full,
		 { // double size of array
		 T[] oldQueue = queue;
		 int oldSize = oldQueue.length;
		 int newSize = 2 * oldSize;
		 checkCapacity(newSize-1);
		 // The cast is safe because the new array contains null entries
		 @SuppressWarnings("unchecked")
		 T[] tempQueue = (T[]) new Object[newSize];
		 queue = tempQueue;
		 for (int index = 0; index < oldSize-1; index++)
		 {
		 queue[index] = oldQueue[frontIndex];
		 frontIndex = (frontIndex + 1) % oldSize;
		 } // end for
		 frontIndex = 0;
		 backIndex = oldSize-2;
		 } // end if
		} // end ensureCapacity
	
}//end ArrayQueue

