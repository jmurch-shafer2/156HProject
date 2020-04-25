package com.tbf;

public class Node<T> {
	private Node<T> next;
	private T item;

	public Node(T item) {
		this.item = item;
		this.next = null;
	}

	public T getItem() {
		return item;
	}
	
	/**
	 * Used for implementing the Iterator class within 
	 * SortedLinked List
	 * @param item
	 */
	public void setItem(T item) {
		this.item = item;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
