package homework3;
import java.util.ArrayList;

public class IDLList<E> {
	private class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}

		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}

	public boolean add(int index, E elem) {
		if (index < 0) {
			System.out.println("Index cannot be negative");
			return false;
		}

		if (index > (int) size()) {
			System.out.println("Index out of bound");
			return false;
		}

		if (index == 0) {
			add(elem);
			return true;
		}

		if (index == size()) {
			append(elem);
			return true;
		}

		Node<E> temp = new Node<E>(elem, this.indices.get(index).prev, this.indices.get(index));
		this.indices.get(index).prev.next = temp;
		this.indices.get(index).prev = temp;

		this.indices.add(index, temp);
		this.size++;
		return true;
	}

	public boolean add(E elem) {
		if (size() == 0) {
			this.head = new Node<E>(elem);
			this.tail = this.head;
		} else {
			Node<E> temp = new Node<E>(elem);
			temp.next = this.head;
			this.head.prev = temp;
			this.head = temp;
		}
		this.indices.add(0, this.head);
		this.size++;
		return true;
	}

	public boolean append(E elem) {
		if (size() == 0) {
			this.tail = new Node<E>(elem);
			this.head = this.tail;
		} else {
			Node<E> temp = new Node<E>(elem);

			this.tail.next = temp;
			temp.prev = this.tail;
			this.tail = temp;
		}
		this.indices.add(this.tail);
		this.size++;
		return true;
	}

	public E get(int index) {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		}
		if (index >= (int) size()) {
			System.out.println("Index out of bound");
			return null;
		}
		return this.indices.get(index).data;
	}

	public E getHead() {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		}

		return this.indices.get(0).data;
	}

	public E getLast() {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		}
		return this.indices.get(this.indices.size() - 1).data;
	}

	public int size() {
		if (this.indices.size() != this.size) {
			System.out.println("ArrayList size and DLL size do not match, please fix code");
			return -1;
		} else {
			return this.indices.size();
		}
	}

	public E remove() {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		} else {
			Node<E> temp = this.head;
			this.head = this.head.next;
			this.head.prev = null;

			this.indices.remove(0);
			this.size--;
			return temp.data;
		}
	}

	public E removeLast() {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		} else {
			Node<E> temp = this.tail;
			this.tail = this.tail.prev;
			this.tail.next = null;

			this.indices.remove(this.indices.size() - 1);
			this.size--;
			return temp.data;
		}
	}

	public E removeAt(int index) {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return null;
		}
		if (index >= size()) {
			System.out.println("Index out of bound");
			return null;
		}

		if (index == 0) {
			return remove();
		}

		if (index == size() - 1) {
			return removeLast();
		}

		Node<E> temp = this.indices.get(index);
		Node<E> sucNode = temp.next;
		Node<E> predNode = temp.prev;

		sucNode.prev = predNode;
		predNode.next = sucNode;

		this.indices.remove(index);
		this.size--;
		return temp.data;
	}

	public boolean remove(E elem) {
		if (size() == 0) {
			System.out.println("Empty IDLL!");
			return false;
		}
		int index = 0;
		Node<E> curNode = this.head;
		while (curNode != null) {
			if (curNode.data == elem) {
				removeAt(index);

				return true;
			}
			index++;
			curNode = curNode.next;
		}
		return false;
	}

	public String toString() {
		String list = "";
		Node<E> curNode = this.head;
		while (curNode != null) {
			if (list == "") {
				list = ("" + curNode.data);
			} else {
				list = (list + "," + curNode.data);
			}
			curNode = curNode.next;
		}
		return list;
	}
}