package Queue;

import stack.Node;

public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>{
	
	private Node free; // References first node in queue
	private Node back; // References node after back of queue
	private int numOfEntries;
	private static final int MAX_CAPACITY = 10;
	
	public TwoPartCircularLinkedQueue(){
		
		free = new Node();
		back = new Node();
		free.setNextNode(free);
		numOfEntries = 0;
		} // end default constructor


	public void enqueue(T newEntry) {
		free.setData(newEntry);
		if(isChainFull()){
		Node newNode = new Node(null, free.getNextNode());
		free.setNextNode(newNode);
		}//end if
		free = free.getNextNode();
	}//end enqueue


	public T dequeue() {
		T first = getFront();
		assert !isEmpty();
		back.setData(null);
		back = back.getNextNode();
		
		return first;
	}//end dequeue


	public T getFront() {
		if (isEmpty())
			 throw new EmptyQueueException();
			 else
			 return back.getData();	
		}//end getFront


	public boolean isEmpty() {
		return back == free;
	}
	
	private boolean isChainFull(){
		return back == free.getNextNode();
	}

	public void clear() {
		while (!isEmpty()) {
			dequeue();
		}
	}
	
	
	
	private class Node{
	private T data; // Queue entry
	private Node next; // Link to next node
	
	public void setNextNode(TwoPartCircularLinkedQueue<T>.Node free) {
		
	}

	public Object getNextNode() {
		return null;
	}
	}

}

