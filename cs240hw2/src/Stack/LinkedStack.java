package Stack;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {

	private Node<T> topNode;
	
	public void LinedStack(){
		topNode = null;
	}
	
	@Override
	public void push(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry, topNode);
		topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		 assert topNode != null;
		 topNode = topNode.getNextNode();
		 return top;
	}

	@Override
	public T peek() {
		 if (isEmpty())
			 throw new EmptyStackException();
			 else
			 return topNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public void clear() {
		topNode = null;
	}
	
}
