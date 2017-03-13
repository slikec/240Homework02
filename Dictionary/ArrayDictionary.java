package Dictionary;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V>{
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayDictionary(){
		this(DEFAULT_CAPACITY);
	}//end default constructor
	
	public ArrayDictionary(int initialCapacity){
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[initialCapacity];
		dictionary = tempDictionary;
		numberOfEntries = 0;
		initialized = true;
	}//end constructor

	@Override
	public V add(K key, V value) {
		// TODO Auto-generated method stub
		checkInitialization();
		if((key == null) || (value == null))
			throw new IllegalArgumentException();
		else{
			V result = null;
			int keyIndex = locateIndex(key);
			if(keyIndex < numberOfEntries){
				result = dictionary[keyIndex].getValue();
				dictionary[keyIndex].setValue(value);
			}//end if
			else{
				dictionary[numberOfEntries] = new Entry<>(key, value);
				numberOfEntries++;
				ensureCapacity();
			}//end else
			return result;
		}//end else
	}//end add

	private int locateIndex(K key) {
		// TODO Auto-generated method stub
		int index = 0;
		while((index < numberOfEntries) && !key.equals(dictionary[index].getKey()))
			index++;
		return index;
	}//end locateIndex

	@Override
	public V remove(K key){
		// TODO Auto-generated method stub
		checkInitialization();
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex < numberOfEntries){
			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex] = dictionary[numberOfEntries - 1];
			dictionary[numberOfEntries - 1] = null;
			numberOfEntries--;
		}//end if
		return result;
	}//end remove

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		checkInitialization();
		V result = null;
		int index = locateIndex(key);
		result = dictionary[index].getValue();
		return result;
	}//end getValue

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		checkInitialization();
		return dictionary[locateIndex(key)].getKey() == key;
	}//end contains

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return new KeyIterator();
	}//end getKeyIterator
	
	
	private class KeyIterator implements Iterator<K>{
		
		private KeyIterator(){
			
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public K next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void remove(){
			return;
		}
		
	}//end KeyIterator

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return new ValueIterator();
	}//end getValueIterator
	
	private class ValueIterator implements Iterator<V>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public V next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void remove(){
			return;
		}

	}//end ValueIterator

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(numberOfEntries <= 0)
			return true;
		else
			return false;
	}//end isEmpty

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}//end getSize

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!isEmpty())
			dictionary[numberOfEntries - 1].setValue(null);
			numberOfEntries--;
	}//end clear
	
	private void checkCapacity(int capacity) {
		// TODO Auto-generated method stub
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose capacity exeds allowed maximum of " + MAX_CAPACITY);
	}//end checkCapacity
	
	private void checkInitialization() {
		// TODO Auto-generated method stub
		if(!initialized)
			throw new SecurityException("ArrayDictionary object is not initialized properly.");
	}//end checkInitialization
	
	private void ensureCapacity() {
		// TODO Auto-generated method stub
		int capacity = dictionary.length -1;
		if(numberOfEntries >= capacity){
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			dictionary = Arrays.copyOf(dictionary, newCapacity + 1);
		}//end if
	}//end ensureCapacity
	
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
	
}//end ArrayDictionary
