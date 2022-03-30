package Homework5;

import java.util.Random;

public class Treap<E extends Comparable<E>> {
	
    protected static class Node<E extends Comparable<E>> {
		public E data;        
		public int priority;  
		public Node<E> left;  
		public Node<E> right; 
	
		public Node(E data, int priority) {
		    if (data == null){
		    	throw new IllegalArgumentException("Data cannot have null value!");
		    } else {
				this.data = data;
				this.priority = priority;
				this.left = this.right = null;
		    }
		}
		
		public Node<E> rotateRight() {
		    Node<E> temp = new Node<E>(this.data, this.priority);
		    temp.left = this.left.right;
		    temp.right = this.right;
		    
		    this.data = this.left.data; 
		    this.priority = this.left.priority;
		    this.left = this.left.left;
		    this.right = temp;
		    
		    return this;
		}
	
		public Node<E> rotateLeft() {
		    Node<E> temp = new Node<E>(this.data, this.priority);
		    temp.right = this.right.left;
		    temp.left = this.left;
		    
		    this.data = this.right.data;
		    this.priority = this.right.priority;
		    this.right = this.right.right;
		    this.left = temp;
		    
		    return this;
		}

		@Override
        public String toString() { return "(key=" + this.data.toString() + ", priority=" +  this.priority + ")"; }
    }

    private Random priorityGenerator; 
    private Node<E> root;
    private boolean addReturn;
    private boolean deleteReturn;

    public Treap() {
		this.priorityGenerator = new Random();
		this.root = null;
    }

    public Treap(long seed) {
		this.priorityGenerator = new Random(seed);
		this.root = null;
    }

    public boolean add(E key) {
    	return this.add(key, priorityGenerator.nextInt());
    }

    public boolean add(E key, int priority) {
		if (key == null){
		    throw new IllegalArgumentException("Can't have a null key!");
		}
		this.root = this.add(this.root, key, priority);
		return this.addReturn;
    }

    private Node<E> add(Node<E> localroot, E key, int priority) {
		if (localroot == null) { 
		    this.addReturn = true;
		    return new Node<E>(key, priority);
		}
		int comp = key.compareTo(localroot.data);
		if (comp == 0){ 
		    this.addReturn = false;
		} else {
		    if (comp < 0){ 
				localroot.left = add(localroot.left, key, priority);
				if (localroot.left.priority > localroot.priority) {
				    localroot.rotateRight();
				} 
		    } else {
				localroot.right = add(localroot.right, key, priority);
				if (localroot.right.priority > localroot.priority) {
				    localroot.rotateLeft();
				}
		    }
		}
		return localroot;
    }

    public boolean delete(E key) {
		this.root = delete(this.root, key);
		return deleteReturn;
    }

    private Node<E> delete(Node<E> localroot, E key) {
		if (localroot == null) { 
		    this.deleteReturn = false;
		    return null;
		}
		
		int comp = localroot.data.compareTo(key);
		if (comp != 0){
		    if (comp > 0) {
		    	localroot.left = delete(localroot.left, key);
		    } else {
		    	localroot.right = delete(localroot.right, key);
		    }
		} else if ( (localroot.left != null) || (localroot.right != null) ) {
		    if (localroot.left == null) {
				localroot.rotateLeft();
				localroot.left = delete( localroot.left, key);
		    } else if (localroot.right == null) {
				localroot.rotateRight();
				localroot.right = delete( localroot.right, key);
		    } else {
				if (localroot.right.priority > localroot.left.priority) {
				    localroot.rotateLeft();
				    localroot.left = delete( localroot.left, key);
				} else {
				    localroot.rotateRight();
				    localroot.right = delete( localroot.right, key);
				}
		    }
		} else {
		    this.deleteReturn = true;
		    return null;
		}
		return localroot;
    }

    public boolean find(E key) { return find(this.root, key); }

    private boolean find(Node<E> root, E key) {
		if (root == null){
		    return false;
		} else {
		    int comp = root.data.compareTo(key);
		    if (comp == 0) {
		    	return true;
		    } else {
		    	return find(comp>0 ? root.left : root.right, key);
		    }
		}
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(this.root, 1, sb);
        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = depth; i-- > 1;) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }
    
    public static void main(String[] args) throws IllegalArgumentException {
		
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4 ,19);
		testTree.add(2 ,31);	
		testTree.add(6 ,70);
		testTree.add(1 ,84);
		testTree.add(3 ,12);
		testTree.add(5 ,83);
		testTree.add(7 ,26);
		System.out.println(testTree);
	}
}