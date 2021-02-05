import java.util.Iterator;

import java.util.NoSuchElementException;

public class DLinkedList<E> {

	private class Node

	{
		E item;          
		Node next;      
		Node prev;
		Node(E d)
		{
			this.item = d;
			next = null;
			prev = null;
		}
	}
	private Node head; // first node that is not seen
	private Node tail; //the end node it is seen

	public DLinkedList() {
		head = new Node(null);
		tail = head;
	}

	/**
	 * Checks whether the list is empty.
	 * @return empty
	 */
	public boolean isEmpty()
	{
		return head == tail;
	}

	/**
	 * Places the item in the first position of the list.
	 * @param item
	 * @throws IndexOutOfBoundsException
	 */
	public void addFirst(E item)  
	{
		Node node = new Node(item);

		if (isEmpty()) {
			head.next = node;
			node.prev = head;
			tail = node;
		}
		else {
			// single and multi
			head.next.prev = node;
			node.prev = head;
			node.next = head.next;
			head.next = node;
		}
	}

	/**
	 * The item that is given will be placed in the index given. For example, if the given index is 7, then the given item x should be placed in between index 6 and 8 (dummy nodes should not be included).
	 * @param index - position of the element
	 * @param item
	 * @throws NoSuchElementException
	 * @throws IndexOutOfBoundsException
	 */

	public void add(int index, E item)  

	{
		Node current = head;
		int counter = 0;
		while (current != null) {

			Node node = new Node(item);

			if (index == counter) {
				Node pointer = current.next; // easier to fit in the new node at the given index
				current.next = node;
				node.prev = current;       // x y x, where x are the dummy nodes, and y is the position

				if (current == tail) { //account for when current is tail
					tail = node;
				}
				else {
					pointer.prev = node;
				}
				node.next = pointer;
				return;
			}
			else {
				current = current.next; //moves up an index pos
				counter++; //increment
			}
		}
		throw new IndexOutOfBoundsException(); //Index
	}


	/**
	 * Places the item in the last position of the list. Note: this would mean that the last item is the new tail.
	 * @param item
	 * @throws IndexOutOfBoundsException
	 */
	public void addLast(E item)
	{
		Node node = new Node(item); //just add to the end
		tail.next = node;
		node.prev = tail;
		tail = node;   //new tail

	}

	/**
	 * Returns the item in the first position of the list. Note: the head is not a position, treat as invisible.
	 * @return
	 */
	public E getFirst()
	{
		if (this.isEmpty()) {  // if empty
			throw new NoSuchElementException();
		}
		Node current = head.next;
		return current.item;

	}

	/**
	 * The value in the given index is returned.
	 * @param index
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index)
	{
		Node current = head.next;

		int counter = 0;
		while (current != null) {
			if (index == counter) {
				return current.item;
			}
			else {
				current = current.next;
				counter++; //increment
			}
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Returns the item in the last position of the list. Note: the tail is a position, do no treat as invisible.
	 * @return
	 * @throws NoSuchElementException
	 */
	public E getLast()  //similar to getFirst
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		Node current = tail;
		return current.item;
	}

	/**

	 * Trades in the node at a given index in the list with a certain item. Then returns the preceding item of that index.
	 * @param index
	 * @param item
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int index, E item)
	{
		Node current = head.next;
		int counter = 0;

		while (current != null) {
			if (index == counter) {
				E temp = current.item;
				current.item = item;  
				return temp;
			}
			else {
				current = current.next;
				counter++;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	/**

	 * Checks if the list has the given item.
	 * @param item
	 * @return
	 */
	public boolean contains(E item)
	{
		Node current = head.next;
		while (current != null) {
			if (current.item.equals(item)) {
				return true;
			}
			else {
				current = current.next;
			}
		}
		return false;  
	}

	/**
	 * All the elements in the list are removed.
	 */
	public void clear()
	{
		Node current = head.next;
		while (current != null) {
			head.next = null;
			tail = head;
			current.prev = null;
			Node temp = current.next;
			current.next = null;
			current = temp;
		}
	}

	/**
	 * The string will display [1 2 3 4] for a non-empty list and [] for an empty list.
	 * @return
	 */

	public String toString() //given
	{
		String str = "";
		Node current = head.next;        

		while (current != null) {
			str += current.item + " ";
			current = current.next;
		}
		str = "[" + str.trim() + "]";
		return str;
	}

	/**
	 * This will display a reversed order. For example, [1 2 3 4] --> [4 3 2 1] for a non-empty list.
	 * @return
	 */
	public String toStringRev()

	{
		String str = "";
		Node current = tail;        
		while (current != head) {
			str += current.item + " ";
			current = current.prev;
		}
		str = "[" + str.trim() + "]";
		return str;

	}
	/**
	 * An iterator goes through the whole list and it will add hasNext, Next and Remove.
	 * @return
	 */
	public Iterator<E> iterator()  
	{
		ListIterator iter = new ListIterator();
		return iter;
	}
	private class ListIterator implements Iterator<E>

	{
		private Node currentPos;
		boolean flag = false;
		public ListIterator()
		{
			currentPos = head.next;
		}
		@Override
		public boolean hasNext() {
			return currentPos != null;

		}
		@Override
		public E next() {
			if (hasNext()) {
				E item = currentPos.item;
				currentPos = currentPos.next;
				flag = true;
				return item;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove()
		{
			Node currentPos = head.next;
			if (flag == true) {
				Node currPrev = currentPos.prev;
				Node currNext = currentPos.next;
				currPrev.next = currNext;

				if (currNext != null) {
					currNext.prev = currPrev;
				}
				else {
					tail = currPrev;	
				}
				currentPos.next = null;
				currentPos.prev = null;
			}
			else {
				throw new IllegalStateException();
			}
			flag = false;
		}
	}

	/**
	 * Uses the iterator to check if the item is on the list.
	 * @param item
	 * @return
	 */
	public boolean containsIter(E item)
	{
		Iterator<E> iter = iterator();
		while(iter.hasNext()) {
			E store = iter.next();
			if (store.equals(item)) {
				return true;
			}
		}
		return false;  
	}
	/**
	 * Similarly to remove all except the iterator runs through the whole list.
	 * @param item
	 * @return
	 */
	public boolean removeAllIter(E item) {
		Iterator<E> iter = this.iterator();
		boolean flag = false;

		while (iter.hasNext()) {
			E store = iter.next();
			if (store.equals(item)) {
				iter.remove();
				flag = true;		
			}
		}
		if (flag == false) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * Removes the item in the first position of the list.
	 * @throws NoSuchElementException
	 */
	E removeFirst() {

		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		Node current = head.next;
		Node pointer = current.next;
		if (current.next != null) {
			pointer.prev = head;
			head.next = pointer;
			current.next = null;
			current.prev = null;	
		}
		if (current == tail) {
			head = tail;
		}
		return current.item;
	}

	/**
	 * Removes the item in the last position of the list.
	 * @param item
	 * @throws NoSuchElementException
	 */

	E removeLast() {

		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		E store = tail.item;
		Node pointer = tail.prev;
		pointer.next = null;
		tail.prev = null;
		tail = pointer;

		return store;
	}

	/**
	 * The item that is given will be removed in the index given.
	 * @param index - position of the element
	 * @throws IndexOutOfBoundsException

	 */
	E remove(int index) {
		Node current = head.next;
		int counter = 0;
		while(current != null) {
			if (counter == index) {	
				Node currPrev = current.prev;
				Node currNext = current.next;
				currPrev.next = currNext;
				if (currNext != null) { 
					currNext.prev = currPrev;		
				}
				else {	
					tail = currPrev;		
				}
				current.next = null;
				current.prev = null;
				return current.item;

			}
			current = current.next;
			counter++;
		}

		throw new IndexOutOfBoundsException();
	}

	/**
	 * The item that is given will be removed out of the whole list until that item is not there.
	 * @param item
	 */
	boolean removeAll(E item) 
	{
		Node current = head.next;
		boolean flag = false;
		while (current != null) {
			Node currPrev = current.prev;
			Node currNext = current.next;
			if (current.item.equals(item)) {
				flag = true;

				currPrev.next = currNext;

				if (current == tail) {
					tail = currPrev;
				}
				else {
					currNext.prev = currPrev;	
				}	
				current.next = null;
				current.prev = null;
			}
			current = currNext;
		}
		if (flag == false) {
			return false;
		}
		else {
			return true;
		}
	}

}



