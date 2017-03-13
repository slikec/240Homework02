package Iterator;

import List.ListInterface;

public interface ListWithListIteratorInterface<T> extends Iterable<T>, ListInterface<T>{
	public  ListIterator<T> getIterator();
}
