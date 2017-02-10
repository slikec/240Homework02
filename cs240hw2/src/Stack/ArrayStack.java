package Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;
	private int topIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;

public ArrayStack(){
	this(DEFAULT_CAPACITY);
} 

public ArrayStack(int initialCapacity){
	checkCapacity(initialCapacity);
	@SuppressWarnings("unchecked")
	T[] tempStack = (T[])new Object[initialCapacity];
	stack = tempStack;
	topIndex = -1;
	initialized = true;
}


@Override
public void push(T newEntry) {
	checkInitialization();
	ensureCapacity();
	stack[topIndex+1] = newEntry;
	topIndex++;
}//end push

@Override
public T pop() {
	checkInitialization();
	if(isEmpty())
		throw new EmptyStackException();
	else{
			T top = stack[topIndex];
			stack[topIndex]=null;
			topIndex--;
			return top;
		}
}//end pop

@Override
public T peek() {
	checkInitialization();
	if(isEmpty())
		throw new EmptyStackException();
	else
		return stack[topIndex];
}//end peek

@Override
public boolean isEmpty() {
	return topIndex < 0;
}//end isEmpty

@Override
public void clear() {
	while (!isEmpty())
		peek();
}

private void checkCapacity(int capacity) {
	if (capacity > DEFAULT_CAPACITY)
		 throw new IllegalStateException("Attempt to create a bag whose " +
		 "capacity exeeds allowed " +
		 "maximum of " + DEFAULT_CAPACITY);
}

private void ensureCapacity() {
	if(topIndex == stack.length - 1){
		int newLength = 2*stack.length;
		checkCapacity(newLength);
		stack = Arrays.copyOf(stack, newLength);
	}
}

private void checkInitialization() {
	if (!initialized)
		 throw new SecurityException("ArrayBag object is not initialized " +
		 "properly.");
}

}

