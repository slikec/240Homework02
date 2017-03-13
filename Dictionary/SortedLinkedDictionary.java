package Dictionary;

import java.util.Iterator;



public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>{
	private Node firstNode;
	private int numberOfEntries;
	
	public SortedLinkedDictionary(){
		initializeDataFields();
	}//end default constructor
	
	private void initializeDataFields() {
		// TODO Auto-generated method stub
		
	}

	public V add(K key, V value){
		V result = null;
		
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while((currentNode != null) && key.compareTo(currentNode.getKey()) > 0){
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}//end while
	
		if((currentNode != null) && key.equals(currentNode.getKey())){
		result = currentNode.getValue();
		currentNode.setValue(value);
		}
		else{
			Node newNode = new Node(key, value);
			if(nodeBefore == null){
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}//end if
			else{
				newNode.setNextNode(currentNode);
				nodeBefore.setNextNode(newNode);
			}//end else
			
			numberOfEntries++;
			}
		return result;
	}//end add

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries <= 0;
	}//end isEmpty

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}//end getSize

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while (!isEmpty()) {
			remove(firstNode.getKey());
		}
	}//end clear
	
	private class Node{
		private Entry<K, V> data;
		private Node next;
		
		public Node(K key, V value){
			this(new Entry(key, value), null);
		}//end constructor
		
		public Node(Entry<K, V> newEntry, Node nextNode){
			data = newEntry;
			next = nextNode;
		}
		
		private void setData(Entry<K, V> newEntry){
			data = newEntry;
		}
		
		private void setNextNode(Node nextNode){
			next = nextNode;
		}
		private K getKey() {
			return data.getKey();
		}
		
		private void setValue(V value) {
			data.setValue(value);
		}
		
		private V getValue() {
			return data.getValue();
		}
		
		private Entry<K, V> getData() {
			return data;
		}
		
		private Node getNextNode() {
			return next;
		}
	}//end Node
	
	private class Entry<S, T>{
		private S key;
		private T value;
		
		private Entry(S searchKey, T dataValue){
			key = searchKey;
			value = dataValue;
		}//end constructor
		
		private S getKey(){
			return key;
		}//end getKey
		
		private T getValue(){
			return value;
		}//end getValue
		
		private void setValue(T newValue){
			value = newValue;
		}//end setValue
	}//end Entry
	
	
}
