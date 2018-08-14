/***********************************************************
 * Name: David Kanney
 * Directory ID: dkanney
 * UID: 113039065
 * Section: 0101
 * *********************************************************
 * This class is a representation of a (non-null) node in a generic polymorphic
 * binary search tree. Each non-null or "non-empty" node of this tree has a key
 * of generic type K, a value of generic type V, and two references ("left" and
 * "right") that reference two child nodes. These child nodes are also
 * NonEmptyTree objects themselves. This class contains basic binary search
 * tree methods and operations (add, delete, size, and a toString method), as
 * well as pathToRoot method and a keyCollection method (which stores all of
 * the keys in a tree in a list). Nearly all methods in this class were written
 * using recursion and without checking for null (since null in this case would
 * be the opposite of an NonEmptyTree, an EmptyTree).
 */
package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import tests.ListToString;

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private K ref;
	private V data;
	private Tree<K, V> left, right;

	// This constructor will initialize a new NonEmptyTree object and assign it
	// a key, value, and a left and right child reference. The left and right
	// children of a newly instantiated NonEmptyTree object will be empty.
	NonEmptyTree(K key, V value) {
		ref = key;
		data = value;
		left = EmptyTree.getInstance();
		right = EmptyTree.getInstance();
	}

	// This method adds the parameter key and value to the object of the method,
	// returning the object after the key and value have been added.
	public NonEmptyTree<K, V> add(K key, V value) {
		if (key.compareTo(ref) == 0)
			data = value;

		else if (key.compareTo(ref) < 0)
			left = left.add(key, value);

		else if (key.compareTo(ref) > 0)
			right = right.add(key, value);

		return this;
	}

	// This method returns 1 + the size of the left subtree + the size of the
	// right subtree. (Every NonEmptyTree adds a 1 while every EmptyTree
	// adds a 0.)
	public int size() {
		return (1 + left.size() + right.size());
	}

	// This method recursively compares the parameter key to the current
	// object's key in order to find the value represented by that key.
	public V lookup(K key) {
		V item; // This variable stores the value we're searching for.

		if (key.compareTo(ref) == 0) // The key has been found.
			item = data;

		else if (key.compareTo(ref) < 0) // The key is less than the parameter,
			item = left.lookup(key); // so we look in the left subtree.

		else
			// The key is greater than the parameter,
			item = right.lookup(key); // so we look in the right subtree.

		return item;
	}

	// This method returns the number of leaves in a NonEmptyTree by returning
	// 1 every time a Tree's left and right children have no leaves.
	public int numLeaves() {
		if (left.numLeaves() == 0 && right.numLeaves() == 0) {
			return 1;
		}

		return (left.numLeaves() + right.numLeaves());
	}

	// This method returns a subtree of the current tree's keys in between
	// the range specified in the parameter (fromKey <= key <= toKey).
	public Tree<K, V> subTree(K fromKey, K toKey) {
		Tree<K, V> subT = EmptyTree.getInstance();

		if ((this.ref.compareTo(fromKey) >= 0)
				&& (this.ref.compareTo(toKey) <= 0)) {
			subT = new NonEmptyTree<K, V>(this.ref, this.data);

			if (this.ref.compareTo(fromKey) < 0)
				((NonEmptyTree<K, V>) subT).right = right.subTree(fromKey,
						toKey);

			else if (this.ref.compareTo(fromKey) > 0)
				((NonEmptyTree<K, V>) subT).left = left.subTree(fromKey, toKey);

		} else if (this.ref.compareTo(fromKey) > 0)
			return left.subTree(fromKey, toKey);

		else if (this.ref.compareTo(fromKey) < 0)
			return right.subTree(fromKey, toKey);

		return subT;
	}

	// This method instantiates a new linked list to be used by its helper
	// method.
	public List<K> pathToRoot(K key) {
		LinkedList<K> list = new LinkedList<K>();
		return pathToRoot(key, list);
	}

	// This method is a helper method for the pathToRoot(K key) method. It
	// compares the the parameter key to the current object's key to determine
	// which path to take to the root of the tree. Then, all of these
	// NonEmptyTree objects are added to a list, and the list is returned.
	private List<K> pathToRoot(K key, LinkedList<K> list) {
		if (ref.compareTo(key) < 0) {
			((NonEmptyTree<K, V>) right).pathToRoot(key, list);
			list.add(ref);

		} else if (ref.compareTo(key) > 0) {
			((NonEmptyTree<K, V>) left).pathToRoot(key, list);
			list.add(ref);

		} else
			list.add(ref);

		return list;
	}

	// This method instantiates a new linked list and then calls its helper
	// method.
	public Collection<K> keyCollection() {
		Collection<K> list = new LinkedList<K>();
		return keyCollection(list);
	}

	// This method adds the current key to its parameter list, and then adds
	// all of the lists from the left and right subtrees to the parameter list.
	private Collection<K> keyCollection(Collection<K> list) {
		list.add(this.ref);
		list.addAll(left.keyCollection());
		list.addAll(right.keyCollection());
		return list;
	}

	// This method returns the largest value from the NonEmptyTree, which is
	// the right-most value. When the method recursively ends up calling the
	// EmptyTree version of the method, an exception is thrown and caught.
	// The key of the last NonEmptyTree is then returned.
	public K max() throws EmptyTreeException {
		try {
			return right.max();
		} catch (EmptyTreeException e) {
			return ref;
		}
	}

	// This method returns the smallest value from the NonEmptyTree, which is
	// the left-most value. When the method recursively ends up calling the
	// EmptyTree version of the method, an exception is thrown and caught.
	// The key of the last NonEmptyTree is then returned.
	public K min() throws EmptyTreeException {
		try {
			return left.min();
		} catch (EmptyTreeException e) {
			return ref;
		}
	}

	// This method searches for the parameter key in the Tree. Once it is found,
	// the method then looks for either the largest value from the left subtree
	// or the smallest value from the right subtree to replace the deleted
	// NonEmptyTree object with.
	public Tree<K, V> delete(K key) {
		if (ref.compareTo(key) < 0) {
			right = right.delete(key);
			return this;

		} else if (ref.compareTo(key) > 0) {
			left = left.delete(key);
			return this;

		} else {
			try { // Checks the largest left NonEmptyTree subtree
				ref = left.max();
				data = left.lookup(left.max());
				left = left.delete(left.max());
			} catch (EmptyTreeException eLeft) {
				try { // Checks the smallest right NonEmptyTree subtree
					ref = right.min();
					data = right.lookup(right.min());
					right = right.delete(right.min());
				} catch (EmptyTreeException eRight) {
					return EmptyTree.getInstance(); // If there are no left or
					// right NonEmptyTrees, an EmptyTree is returned.
				}
			}
		}
		return this;
	}

	// This toString() method recursively calls itself on the object's left and
	// right subtrees, returning the key/value pair from each NonEmptyTree.
	@Override()
	public String toString() {
		// The tertiary operator is used to shorten the method and improve
		// readability (instead of using 2-4 if/else statements), instead of
		// returning everything in one line.
		String leftString = (left.toString().equals("") ? "" : left.toString()
				+ " ");
		String rightString = (right.toString().equals("") ? "" : " "
				+ right.toString());
		String keyValuePair = (ref.toString() + "/" + data.toString());
		String result = leftString + keyValuePair + rightString;

		return result;
	}
}
