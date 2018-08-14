/***********************************************************
 * Name: David Kanney
 * Directory ID: dkanney
 * UID: 113039065
 * Section: 0101
 * *********************************************************/
package tests;

import org.junit.*;

import tree.EmptyTree;
import tree.EmptyTreeException;
import tree.SearchTreeMap;
import tree.Tree;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class StudentTests {

	// This method tests the toString() method once on an unchanged tree, and
	// then again after adding a new value.
	@Test
	public void testToString() {
		Tree<Integer, Integer> tree = intTree();

		assertEquals(tree.toString(), "3/25 6/9 9/4 12/36");

		tree.add(0, 0);

		assertEquals(tree.toString(), "0/0 3/25 6/9 9/4 12/36");
	}

	// This method tests the numLeaves() method on pre-made trees, and then
	// again once the trees have been changed.
	@Test
	public void testNumLeaves() {
		Tree<Integer, Integer> tree1 = intTree();
		Tree<Integer, String> tree2 = stringTree();

		assertEquals(tree1.numLeaves(), 2);
		assertEquals(tree2.numLeaves(), 4);

		tree2.add(7, "testVal1");
		tree2.add(9, "testVal2");

		assertEquals(tree2.numLeaves(), 5);
	}

	// This method tests the subTree() method from the EmptyTree class and the
	// NonEmptyTree class.
	@Test
	public void testSubTree() {
		Tree<Integer, String> tree = stringTree();
		Tree<Integer, String> subTree = EmptyTree.getInstance();
		subTree = tree.subTree(6, 20);

		assertEquals("", subTree.subTree(1, 0).toString());
		assertEquals("8/Power of 3 16/Power of 4", subTree.toString());
	}

	// This method tests the min() and max() methods by calling the methods on
	// a tree, converting the returned values to string form, and then
	// comparing them to the correct values.
	@Test
	public void testMinAndMax() {
		Tree<Integer, String> tree1 = stringTree();
		Tree<Integer, Integer> tree2 = intTree();

		try {
			assertEquals(tree1.min().toString(), "0");
			assertEquals(tree1.max().toString(), "32");
			assertEquals(tree2.min().toString(), "3");
			assertEquals(tree2.max().toString(), "12");
		} catch (EmptyTreeException e) {
			// If an exception is thrown, the test will fail.
		}
	}

	//This method tests the put() method from SearchTreeMap.class but putting
	// elements into a map, and then calling the get() method on the map to
	// confirm that they were successfully added the to map.
	@Test
	public void testPut() {
		SearchTreeMap<Integer, String> map = new SearchTreeMap<Integer, String>();

		map.put(10, "Ten");
		map.put(11, "Eleven");

		assertEquals("Ten", map.get(10));
		assertEquals("Eleven", map.get(11));
	}

	/* ***************************
	 * 
	 * Private Utility Methods
	 * 
	 * ***************************
	 */

	private static Tree<Integer, Integer> intTree() {
		Tree<Integer, Integer> tree = EmptyTree.getInstance();

		tree = tree.add(9, 4);
		tree = tree.add(6, 9);
		tree = tree.add(3, 25);
		tree = tree.add(12, 36);

		return tree;
	}

	private static Tree<Integer, String> stringTree() {
		Tree<Integer, String> tree = EmptyTree.getInstance();

		tree = tree.add(4, "Power of 2");
		tree = tree.add(16, "Power of 4");
		tree = tree.add(1, "Power of 0");
		tree = tree.add(2, "Power of 1");
		tree = tree.add(0, "Zero");
		tree = tree.add(8, "Power of 3");
		tree = tree.add(32, "Power of 5");

		return tree;
	}

	private static SearchTreeMap<Integer, String> testMap() {
		SearchTreeMap<Integer, String> map = new SearchTreeMap<Integer, String>();

		map.put(10, "Ten");
		map.put(8, "Eight");
		map.put(2, "Two");
		map.put(4, "Four");
		map.put(7, "Seven");

		return map;
	}

}
