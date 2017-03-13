package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedListWithIterator(){
		initializeDataFields();
	}
	
	@Override
	public void add(T newEntry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int newPosition, T newEntry) {
		// TODO Auto-generated method stub
		
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
	public Iterator.Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator.Iterator<T> getIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initializeDataFields() {
		// TODO Auto-generated method stub
		firstNode = null;  
		numberOfEntries = 0;
	}
	
	private class IteratorForLinkedList implements Iterator<T> {
		private Node nextNode;
		
		private IteratorForLinkedList() {
			nextNode = firstNode;
		}

		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				Node returnNode = nextNode;
				nextNode = nextNode.getNextNode();
				return returnNode.getData();
			} 
			else
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
			
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported by this iterator.");
		}
	} // end IteratorForLinkedList
	
	public class Node {
		private T data;
		private Node next;
		
		public Node(T newEntry) {
			this(newEntry, null);
		}
		
		public Node(T newEntry, Node nextNode) {
			data = newEntry;
			next = nextNode;
		}
		
		private void setData(T newEntry) {
			data = newEntry;
		}
		
		private void setNextNode(Node newNode) {
			next = newNode;
		}
		
		private T getData() {
			return data;
		}
		
		private Node getNextNode() {
			return next;
		}
	} // end DLNode
	
}
