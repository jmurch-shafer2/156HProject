package com.tbf;

public class Node {
	private Node next;
	private Portfolio item;

	public Node(Portfolio item) {
		this.item = item;
		this.next = null;
	}

	public Portfolio getPortfolio() {
		return item;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
