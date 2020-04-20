package com.tbf;

import java.util.ArrayList;
import java.util.Collections;

//TODO Paramatarize this

public class ADT {
	private int size;

	private Node head;

	// can take values "OwnerName","Value","Manager"
	private String sortBy = null;

	public ADT(String sortBy) {
		head = null;
		this.size = 0;
		this.sortBy = sortBy;
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		for (int i = 0; i < this.size; i++) {
			this.remove(0);
		}
		this.size = 0;
	}

	private Node getNode(int position) {
		if (position > size || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		} else {
			Node node = this.head;
			for (int i = 0; i < position; i++) {
				node = node.getNext();
			}
			return node;
		}
	}

	// TODO check that start and end things don't happen
	public void addToList(Portfolio item) {
		for (int i = 0; i < this.size; i++) {
			if (this.sortBy.equals("OwnerName")) {
				if (Comparator.sortByOwnerName(item, this.getNode(i).getPortfolio()) > 0) {
					Node next = this.getNode(i);
					Node previous = this.getNode(i - 1);
					Node current = new Node(item);
					previous.setNext(current);
					current.setNext(next);
					break;
				}
			} else if (this.sortBy.equals("Value")) {
				if (Comparator.sortByValue(item, this.getNode(i).getPortfolio()) > 0) {
					Node next = this.getNode(i);
					Node previous = this.getNode(i - 1);
					Node current = new Node(item);
					previous.setNext(current);
					current.setNext(next);
					break;
				}
			} else if (this.sortBy.equals("Manager")) {
				if (Comparator.sortByManager(item, this.getNode(i).getPortfolio()) > 0) {
					Node next = this.getNode(i);
					Node previous = this.getNode(i - 1);
					Node current = new Node(item);
					previous.setNext(current);
					current.setNext(next);
					break;
				}
			} else {
				System.out.println("you dun messed up son");
			}
		}
		size++;
	}

//	private void addToStart(Truck t) {
//		this.size++;
//		TruckListNode newHead = new TruckListNode(t);
//		newHead.setNext(this.head);
//
//		this.head = newHead;
//	}
//	private void addToEnd() {
//	
//  }

	// Nat, can you do this one?
	public void remove(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		} else {
			if (position == 0) {
				this.head = this.getTruckListNode(1);
			} else {
				this.getTruckListNode(position - 1).setNext(this.getTruckListNode(position).getNext());
			}
			this.size--;
		}
	}

	// Nat, can you do this one?
	public Truck getTruck(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		}
		TruckListNode node = this.head;
		for (int i = 0; i < position; i++) {
			node = node.getNext();
		}
		return node.getTruck();
	}

	// Nat, can you do this one?
	public void print() {
		TruckListNode node = this.head;
		for (int i = 0; i < this.size; i++) {
			node.getTruck().print();
			node = node.getNext();
		}
	}

//	public ADT ADTFromArrayList(ArrayList<Portfolio> list) {
//		return null;
//	}
}
