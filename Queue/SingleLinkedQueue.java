package Queue;

import Stack.Node;

public final class SingleLinkedQueue <T> implements QueueInterface<T>{
	private Node<T> front;
	private Node<T> back;
	
	public SingleLinkedQueue(){
		front = null;
		back = null;
	}

	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<>(newEntry, null);
		
		if(isEmpty()){
			front = newNode;
		}
		else{
			back.setNextNode(newNode);
		}
		back = newNode;
	}

	public T dequeue() {
		T first = getFront(); // Might throw EmptyQueueException
		 assert front != null;
		 front.setData(null);
		 front = front.getNextNode();
		 if (front == null)
		 back = null;
		 return first;
	}
	
	public T getFront() {
		 if (isEmpty()){
			 throw new EmptyQueueException();
		 }
		 else{
			 return front.getData();
		 }
	}

	public boolean isEmpty() {
		return (front == null) && (back == null);
	}//end isEmpty

	public void clear() {	
		front = null;
		back = null;
	}
}


	

