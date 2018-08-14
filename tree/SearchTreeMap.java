/***********************************************************
 * Name: David Kanney
 * Directory ID: dkanney
 * UID: 113039065
 * Section: 0101
 * *********************************************************
 * This class represents a map, which is a collection of keys where each key
 * has an associated value. It has methods similar to those of the java Map
 * interface, but the class mainly acts as a wrapper around the poly-BST
 * classes.
 */
package tree;

import java.util.NoSuchElementException;
import java.util.Collection;

@SuppressWarnings("unchecked")
public class SearchTreeMap<K extends Comparable<K>, V extends Comparable<V>> {

	private Tree<K, V> treeMap = EmptyTree.getInstance();

	public void put(K key, V value) {
		treeMap = treeMap.add(key, value);
	}

	public V get(K key) {
		return treeMap.lookup(key);
	}

	public int size() {
		return treeMap.size();
	}

	public void remove(K key) {
		treeMap = treeMap.delete(key);
	}

	public K firstKey() throws NoSuchElementException {
		try {
			return treeMap.min();
		} catch (EmptyTreeException e) {
			throw new NoSuchElementException("No such element.");
		}
	}

	public K lastKey() throws NoSuchElementException {
		try {
			return treeMap.max();
		} catch (EmptyTreeException e) {
			throw new NoSuchElementException("No such element.");
		}
	}

	public Collection<K> keys() {
		return treeMap.keyCollection();
	}

}
