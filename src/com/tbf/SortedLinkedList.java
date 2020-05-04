package com.tbf;

import java.util.Comparator;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class SortedLinkedList<T> implements Iterable<T> {
	Logger log = Logger.getLogger(PortfolioReportDepreciated.class);;
	private int size;
	private Node<T> head;
	private Comparator<T> comp;

	public SortedLinkedList(Comparator<T> comp) {
		head = null;
		this.size = 0;
		this.comp = comp;
	}

	@Override
	public Iterator<T> iterator() {
		Logger log = Logger.getLogger(PortfolioReportDepreciated.class);
		return new Iterator<T>() {
			Node<T> curr = head;

			@Override
			public boolean hasNext() {
				if (curr == null)
					return false;
				else
					return true;
			}

			@Override
			public T next() {
				T item = curr.getItem();
				curr = curr.getNext();
				return item;
			}

			@Override
			public void remove() {
				curr.setItem(null);
			}
		};
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

	private Node<T> getNode(int position) {
		if (position > size - 1 || position < 0) {
			log.error("Invalid position is provided.");
			return null;
		} else {
			Node<T> node = this.head;
			for (int i = 0; i < position; i++) {
				node = node.getNext();
			}
			return node;
		}
	}

	public void addToList(T item) {
		if (size == 0) {
			this.insert(item, 0);
		} else {
			int i = 0;
			while (i < size && comp.compare(item, this.getItem(i)) > 0) {
				i++;
			}
			insert(item, i);
		}
		this.size++;
	}

	private void insert(T item, int position) {
		if (position > size || position < 0) {
			log.error("Invalid position is provided.");
		} else if (position == 0) {
			if (size > 0) {
				Node<T> next = this.getNode(position);
				Node<T> current = new Node<T>(item);
				this.head = current;
				current.setNext(next);
			} else {
				Node<T> current = new Node<T>(item);
				this.head = current;
			}
		} else if (position == size) {
			Node<T> previous = this.getNode(position - 1);
			Node<T> current = new Node<T>(item);
			previous.setNext(current);
		} else {
			Node<T> previous = this.getNode(position - 1);
			Node<T> current = new Node<T>(item);
			current.setNext(previous.getNext());
			previous.setNext(current);
		}
	}

	public void remove(int position) {
		if (position > size - 1 || position < 0) {
			log.error("Invalid position is provided.");
		} else {
			if (position == 0) {
				this.head = this.getNode(1);
			} else {
				this.getNode(position - 1).setNext(this.getNode(position).getNext());
			}
			this.size--;
		}
	}

	public T getItem(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Invalid position is provided.");
		}
		Node<T> node = this.head;
		for (int i = 0; i < position; i++) {
			node = node.getNext();
		}
		return node.getItem();
	}

	public void print() {
		Node<T> node = this.head;
		for (int i = 0; i < this.size; i++) {
			System.out.println(node.getItem());
			node = node.getNext();
		}
	}

}
