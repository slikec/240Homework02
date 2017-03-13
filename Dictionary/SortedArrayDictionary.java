package Dictionary;

import java.util.Arrays;
import java.util.Iterator;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>{
	
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	public SortedArrayDictionary(){
		this(DEFAULT_CAPACITY);
	}//end default constructor
	
	public SortedArrayDictionary(int initialCapacity){
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
			if((keyIndex < numberOfEntries) && key.equals(dictionary[keyIndex].getKey())){
				result = dictionary[keyIndex].getValue();
				dictionary[keyIndex].setValue(value);
			}//end if
			else{
				makeRoom(keyIndex);
				dictionary[keyIndex] = new Entry<>(key, value);
				numberOfEntries++;
				ensureCapacity();
			}//end else
			return result;
		}//end else
	}//end add

	private int locateIndex(K key) {
		// TODO Auto-generated method stub
		int index = 0;
		while((index < numberOfEntries) && key.compareTo(dictionary[index].getKey()) > 0)
			index++;
		
		return index;
	}//end locateIndex

	private void makeRoom(int keyIndex) {
		// TODO Auto-generated method stub
		int index = numberOfEntries;
		while (index > keyIndex) {
			dictionary[index] = dictionary[index - 1];
			index--;
		}//end while
	}//end makeRoom
	
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		int keyIndex = locateIndex(key);
		V result = dictionary[keyIndex].getValue();
		while (keyIndex < numberOfEntries) {
			dictionary[keyIndex] = dictionary[keyIndex + 1];
			keyIndex++;
		}//end while
		numberOfEntries --;

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
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return new KeyIterator();
	}//end getKeyIterator
	
	private class KeyIterator implements Iterator<K>{

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
		
	}

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
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numberOfEntries <= 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}

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
	
}//end SortedArrayDictionary
