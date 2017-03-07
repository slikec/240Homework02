package Dequeue;


public final class LinkedDeque<T> implements DequeInterface<T>, java.io.Serializable {
	private DLNode firstNode;
	private DLNode lastNode;
	
	public LinkedDeque(){
		firstNode = null;
		lastNode = null;
	}

	@Override
	public void addToFront(T newEntry) {
		DLNode newNode = new DLNode(null, newEntry, firstNode);
		
		if(isEmpty())
			lastNode = newNode;
		else
			firstNode.setPreviousNode(newNode);
		
		firstNode = newNode;
	}//end addToFront

	@Override
	public void addToBack(T newEntry) {
		DLNode newNode = new DLNode(lastNode, newEntry, null);
		if(isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
		lastNode = newNode;
	}//edn addToBack

	@Override
	public T removeFront() {
		T front = getFront();
		assert firstNode != null;
		firstNode = firstNode.getNextNode();
		
		if(firstNode == null){
			lastNode = null;
		}
		else{
			firstNode.setPreviousNode(null);
		}
		return front;
	}//removeFront

	@Override
	public T removeBack() {
		T back = getBack();
		assert lastNode != null;
		lastNode = lastNode.getPreviousNode();
		
		if(lastNode == null){
			firstNode = null;
		}
		else{
			lastNode.setNextNode(null);
		}
		return back;
	}//end removeBack

	@Override
	public T getFront() {
		T front = null;
		
		if(!isEmpty())
			front = firstNode.getData();
		
		return front;
	}

	@Override
	public T getBack() {
		T back = null;
		
		if(!isEmpty())
			back = lastNode.getData();
		
		return back;
	}

	@Override
	public boolean isEmpty() {
		return(firstNode == null) &&(lastNode == null);
	}//end isEmpty

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}//end clear
	
	private class DLNode implements java.io.Serializable{
		private T data;
		private DLNode next;
		private DLNode previous;
	}//end DLNode
	
}//end LinedDeque
