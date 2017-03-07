package List;


public class LinkedList<T> implements ListInterface<T> {
	private Node<T> firstNode;
	private int numberOfEntries;
	
	public LinkedList(){
		firstNode = null;
		numberOfEntries = 0;
	}//end default constructor
	
	public void clear(){
		initializaDataFields();
	}//end clear

	@Override
	public void add(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		if(isEmpty())
			firstNode = newNode;
		else{
			Node<T> lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}//end if
		numberOfEntries++;
		}// end add
		

	@Override
	public void add(int newPosition, T newEntry) {
		if((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
			Node<T> newNode = new Node<T>(newEntry);
			if(newPosition == 1){
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else{
				Node<T> nodeBefore = getNodeAt(newPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}//end if
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}// end add
		

	@Override
	public T remove(int givenPosition) {
		T result = null;
		if((givenPosition >= 1) && (givenPosition <+ numberOfEntries)){
			assert !isEmpty();
			if(givenPosition == 1){
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
				}
			else{
				Node<T> nodeBefore = getNodeAt(givenPosition-1);
				Node<T> nodeToRemove = nodeBefore.getNextNode();
				result = (T) nodeToRemove.getData();     // Save entry to be removed
				Node<T> nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				} // end if
			numberOfEntries--;
			return result;
			}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}//end remove

	@Override
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			Node<T> desiredNode = getNodeAt(givenPosition); T originalEntry = (T) desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}//end replace

	@Override
	public T getEntry(int givenPosition) {
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			return (T) getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}//end getEntry

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		int index = 0;
		Node<T> currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
		          result[index] = (T) currentNode.getData();
		          currentNode = currentNode.getNextNode();
		          index++;
		} // end while return result;
		return result;
	}//end toArray

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node<T> currentNode = firstNode;
		while(!found && (currentNode != null)){
			if(anEntry.equals(currentNode.getData()));
		}//end while
		return found;
	}//end contains

	@Override
	public int getLength() {
		return 0;
	}//end getLength

	@Override
	public boolean isEmpty() {
		boolean result;
		if(numberOfEntries == 0){
			assert firstNode == null;
			result = true;
		}
		else{
			assert firstNode != null;
			result = false;
		}
		return result;
	}//end isEmpty
	
	
	private void initializaDataFields(){
		firstNode = null;
		numberOfEntries = 0;
	}//end initializeDataFields
	
	private Node<T> getNodeAt(int givenPosition){
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node<T> currentNode = firstNode;
		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		assert currentNode != null;
		return currentNode;
		}//end getNodeAt
	
	//private class Node{
		
	//end Node
}//end LinkedList
