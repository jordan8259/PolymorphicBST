/***********************************************************
 * Name: David Kanney
 * Directory ID: dkanney
 * UID: 113039065
 * Section: 0101
 * *********************************************************
 * This class is a representation of the (singleton) null node in a generic 
 * polymorphic binary search tree. One instance of EmptyTree is created and used
 * used to represent null. This null (or "empty") node has no keys, values, or
 * children because it is empty. This class contains empty versions of the 
 * basic binary search tree methods and operations in the NonEmptyTree class
 * (since both classes are implementing the Tree<K, V> interface).
 */
package tree;

import java.util.List;
import java.util.Collection;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private static EmptyTree singleton = new EmptyTree();

	private EmptyTree() {
		// Bypasses automatic instantiation
	}

	// This method returns the instantiated EmptyTree object.
	public static EmptyTree getInstance() {
		return singleton;
	}

	// This method returns a new NonEmptyTree object with the assigned
	// parameter key and value.
	public NonEmptyTree<K, V> add(K key, V value) {
		return new NonEmptyTree<K, V>(key, value);
	}

	// This method returns 0 because an EmptyTree has no values or keys.
	public int size() {
		return 0;
	}

	// This method returns null because an EmptyTree has no values or keys to
	// search for.
	public V lookup(K key) {
		return null;
	}

	// This method returns 0 because an EmptyTree has no leaves.
	public int numLeaves() {
		return 0;
	}

	// This method returns an EmptyTree object because an EmptyTree has no
	// left or right child to form a subtree.
	public Tree<K, V> subTree(K fromKey, K toKey) {
		return singleton;
	}

	// This method returns null because an EmptyTree has no root and thus, no
	// path to said root.
	public List<K> pathToRoot(K key) {
		return null;
	}

	// This method returns null because an EmptyTree has no key/value pairs to
	// create a collection with.
	public Collection<K> keyCollection() {
		return null;
	}

	// This method throws an EmptyTreeException because an EmptyTree has no
	// maximum value.
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	// This method throws an EmptyTreeException because an EmptyTree has no
	// minimum value.
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	// This method returns an EmptyTree object.
	public Tree<K, V> delete(K key) {
		return singleton;
	}

	// This method just returns a the string representation of an EmptyTree.
	public String toString() {
		return "";
	}

}
