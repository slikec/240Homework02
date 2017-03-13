package Iterator;

import List.ListInterface;

public interface ListWithIteratorInterface<T> extends ListInterface<T>, Iterable<T> {
	public Iterator<T> getIterator();
}//end ListWithIteratorInterface
