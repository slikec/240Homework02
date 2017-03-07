package SortedList;

public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T>{
	
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedSortedList(){
		firstNode = null;
		numberOfEntries = 0;
	}//end default constructor

	private Node getNodeBefore(T anEntry){
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while ( (currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0) ){
           nodeBefore = currentNode;
           currentNode = currentNode.getNextNode();
		} // end while 
		return nodeBefore;
	}
	
	@Override
	public void add(T newEntry) {
		// TODO Auto-generated method stub
		Node newNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);
		if(isEmpty() || (nodeBefore == null)){
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		else{
			Node nodeAfter = nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);
		}
		numberOfEntries++;
	}//end add

	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		Node nodeBefore = getNodeBefore(anEntry);
		if(isEmpty() || (nodeBefore == null)){
			return false;
		}
		else{
			Node nodeAfter = nodeBefore.getNextNode().getNextNode();
			nodeBefore.setNextNode(nodeAfter);
		}
		numberOfEntries--;
		return true;
	}//end remove

	@Override
	public int getPosition(T anEntry) {
		// TODO Auto-generated method stub
		Node newEntry = firstNode;
		int position = 0;
		while(newEntry.getData().compareTo(anEntry) < 0 && position < numberOfEntries){
			newEntry = newEntry.getNextNode();
			position++;
		}
		if(newEntry.getData().compareTo(anEntry) == 0)
			return position;
		else
			return -position;
	}//end getPosition

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		if (givenPosition > 0 && givenPosition <= numberOfEntries) {
			int position = 0;
			Node newEntry = firstNode;
			while (position < givenPosition - 1) {
				newEntry = newEntry.getNextNode();
				position++;
			}
			return newEntry.getData();
		} 
		else {
			return null;
		}
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		Node node = firstNode;
		int currentNum = 0;
		while(currentNum < numberOfEntries){
			if(node.getData() == anEntry){
				return true;
			}
			node = node.getNextNode();
			currentNum++;
		}
		return false;
	}

	@Override
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			T result = list[givenPosition];
			
			if(givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	}//end remove
	
	private void removeGap(int givenPosition){
	 assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
	 int removedIndex = givenPosition;
	 int lastIndex = numberOfEntries;
	 for (int index = removedIndex; index < lastIndex; index++)
		 list[index] = list[index + 1];
	} // end removeGap

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty())
			remove(numberOfEntries);
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries <= 0;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		Node node = firstNode;
		for (int i = 0; i < numberOfEntries; i++){
			result[i] = node.getData();
			node = node.getNextNode();
		}//end for
		return result;
	}
	
	private class Node{
		private T	 data;
		private Node next;
		
		private Node(T dataPortion){
			this(dataPortion, null);
		}//end constructor
		
		private Node(T dataPortion, Node nextNode){
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData(){
			return data;
		}
		
		private void setData(T newData){
			data = newData;
		}
		
		private Node getNextNode(){
			return next;
		}
		
		private void setNextNode(Node nextNode){
			next = nextNode;
		}
	}//end Node
	
}//end LinkedSOrtedList
