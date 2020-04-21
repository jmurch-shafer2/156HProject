package com.tbf;

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
		if (position > size - 1 || position < 0) {
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
		if(size == 0) {
			this.insert(item, 0);
		} else {
			for (int i = 0; i < this.size; i++) {
				if (this.sortBy.equals("OwnerName")) {
					if (Comparator.sortByOwnerName(item, this.getNode(i).getPortfolio()) > 0) {
						System.out.println(i);
						this.insert(item, i);
						break;
					}
				} else if (this.sortBy.equals("Value")) {
					if (Comparator.sortByValue(item, this.getNode(i).getPortfolio()) > 0) {
						this.insert(item, i);
						break;
					}
				} else if (this.sortBy.equals("Manager")) {
					if (Comparator.sortByManager(item, this.getNode(i).getPortfolio()) > 0) {
						this.insert(item, i);
						break;
					}
				} else {
					System.out.println("you dun messed up son");
				}
			}
		}
	}

	private void insert(Portfolio item, int position) {
//		System.out.println(position);
		if (position == 0) {
			if(size > 0) {
				Node next = this.getNode(position);
				Node current = new Node(item);
				this.head = current;
				current.setNext(next);
			} else {
				Node current = new Node(item);
				this.head = current;
			}
		} else if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		} else if (position == size - 1) {
			Node previous = this.getNode(position - 1);
			Node current = new Node(item);
			previous.setNext(current);
		} else {
			Node next = this.getNode(position);
			Node previous = this.getNode(position - 1);
			Node current = new Node(item);
			previous.setNext(current);
			current.setNext(next);
		}
		this.size++;
	}

	public void remove(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		} else {
			if (position == 0) {
				this.head = this.getNode(1);
			} else {
				this.getNode(position - 1).setNext(this.getNode(position).getNext());
			}
			this.size--;
		}
	}

	public Portfolio getPortfolio(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		}
		Node node = this.head;
		for (int i = 0; i < position; i++) {
			node = node.getNext();
		}
		return node.getPortfolio();
	}

	public void print() {
		Node node = this.head;
		for (int i = 0; i < this.size; i++) {
			node.getPortfolio().print();
			node = node.getNext();
		}
	}

//	public ADT ADTFromArrayList(ArrayList<Portfolio> list) {
//		return null;
//	}
}
