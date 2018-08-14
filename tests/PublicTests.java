package tests;

import tree.Tree;
import tree.EmptyTree;
import tree.EmptyTreeException;
import tree.SearchTreeMap;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class PublicTests {

	// Tests calling size() on different-sized trees.
	@Test
	public void testPublic1() {
		Tree<Integer, String> firstTree = EmptyTree.getInstance();
		Tree<Integer, String> secondTree = tree1();
		Tree<Character, Integer> thirdTree = tree3();

		assertEquals(0, firstTree.size());
		assertEquals(1, secondTree.size());
		assertEquals(13, thirdTree.size());
	}

	// Tests calling lookup() on an empty tree, and on an element that's not
	// present in a nonempty tree.
	@Test
	public void testPublic2() {
		Tree<Integer, String> firstTree = EmptyTree.getInstance();
		Tree<Integer, String> secondTree = tree2();

		assertNull(firstTree.lookup(100));
		assertNull(secondTree.lookup(100));
	}

	// Tests calling lookup() on various elements that are present in a
	// nonempty tree.
	@Test
	public void testPublic3() {
		Tree<Character, Integer> tree = tree3();

		assertEquals(1, (int) tree.lookup('h'));
		assertEquals(5, (int) tree.lookup('o'));
		assertEquals(13, (int) tree.lookup('c'));
	}

	// Tests creating a tree with no elements and one with one element,
	// calling toString() on them.
	@Test
	public void testPublic4() {
		Tree<Integer, String> firstTree = EmptyTree.getInstance();
		Tree<Integer, String> secondTree = EmptyTree.getInstance();

		secondTree = secondTree.add(10, "ten");

		assertEquals("", firstTree.toString());
		assertEquals("10/ten", secondTree.toString());
	}

	// Tests creating a tree with several elements, calling toString() on it.
	@Test
	public void testPublic5() {
		Tree<Integer, String> tree = tree2();

		assertEquals("7/seven " + "10/ten " + "12/twelve " + "15/fifteen "
				+ "16/sixteen " + "17/seventeen " + "20/twenty " + "30/thirty",
				tree.toString());
	}

	// Tests that add() modifies its current object tree.
	@Test
	public void testPublic6() {
		Tree<Character, Integer> tree = tree3();
		Tree<Character, Integer> tree2 = tree.add('s', 14);

		assertEquals(14, (int) tree.lookup('s'));
		assertEquals(14, (int) tree2.lookup('s'));
		assertEquals(tree.toString(), tree2.toString());
	}

	// Tests calling pathToRoot() on different elements of a tree (the root
	// node, a leaf node, and an interior node).
	@Test
	public void testPublic7() {
		Tree<Character, Integer> tree = tree3();

		assertEquals("h", ListToString.listToString(tree.pathToRoot('h')));
		assertEquals("n m o r y h",
				ListToString.listToString(tree.pathToRoot('n')));
		assertEquals("r y h", ListToString.listToString(tree.pathToRoot('r')));
	}

	// Tests that delete() modifies its current object tree.
	@Test
	public void testPublic8() {
		Tree<Character, Integer> tree = tree3();

		Tree<Character, Integer> tree2 = tree.delete('m');
		assertNull(tree.lookup('m'));
		assertEquals(tree.toString(), tree2.toString());
	}

	// Tests calling max() on a tree with several elements.
	@Test
	public void testPublic9() {
		Tree<Character, Integer> tree = tree3();

		try {
			assertEquals('y', (char) tree.max());
		} catch (EmptyTreeException ete) {
			// if an exception is thrown the test will fail
		}
	}

	// Tests calling min() on a tree with several elements.
	@Test
	public void testPublic10() {
		Tree<Character, Integer> tree = tree3();

		try {
			assertEquals('a', (char) tree.min());
		} catch (EmptyTreeException ete) {
			// if an exception is thrown the test will fail
		}
	}

	// Tests calling delete() on an empty tree and on an element that's not
	// present in a nonempty tree.
	@Test
	public void testPublic11() {
		Tree<Integer, String> firstTree = EmptyTree.getInstance();
		Tree<Integer, String> secondTree = tree2();

		firstTree = firstTree.delete(100);
		assertEquals("", firstTree.toString());

		assertEquals("7/seven " + "10/ten " + "12/twelve " + "15/fifteen "
				+ "16/sixteen " + "17/seventeen " + "20/twenty " + "30/thirty",
				secondTree.toString());
	}

	// Tests calling delete() on various elements of a nonempty tree.
	@Test
	public void testPublic12() {
		Tree<Integer, String> tree = tree2();

		tree = tree.delete(15);
		assertEquals("7/seven " + "10/ten " + "12/twelve " + "16/sixteen "
				+ "17/seventeen " + "20/twenty " + "30/thirty", tree.toString());

		tree = tree.delete(16);
		assertEquals("7/seven " + "10/ten " + "12/twelve " + "17/seventeen "
				+ "20/twenty " + "30/thirty", tree.toString());

		tree = tree.delete(17);
		assertEquals("7/seven " + "10/ten " + "12/twelve " + "20/twenty "
				+ "30/thirty", tree.toString());
	}

	// Tests the get(), size(), and lastKey() methods of the SearchTreeMap
	// class.
	@Test
	public void testPublic13() {
		SearchTreeMap<Character, Integer> map = sampleMap();

		assertEquals(new Integer(117), map.get('u'));
		assertEquals(new Integer(98), map.get('a'));
		assertEquals(new Integer(112), map.get('p'));

		assertNull(map.get('x'));

		assertEquals(8, map.size());

		assertEquals(new Character('y'), map.lastKey());
	}

	// private utility methods ////////////////////////////////////////////

	// returns a tree with one element, with Integer keys and String values
	private static Tree<Integer, String> tree1() {
		Tree<Integer, String> tree = EmptyTree.getInstance();

		tree = tree.add(15, "fifteen");

		return tree;
	}

	// returns a tree with several elements, with Integer keys and String values
	private static Tree<Integer, String> tree2() {
		Tree<Integer, String> tree = EmptyTree.getInstance();

		tree = tree.add(15, "fifteen");
		tree = tree.add(10, "ten");
		tree = tree.add(20, "twenty");
		tree = tree.add(7, "seven");
		tree = tree.add(12, "twelve");
		tree = tree.add(16, "sixteen");
		tree = tree.add(30, "thirty");
		tree = tree.add(17, "seventeen");

		return tree;
	}

	// returns a tree with several elements, with Character keys and Integer
	// values
	private static Tree<Character, Integer> tree3() {
		Tree<Character, Integer> tree = EmptyTree.getInstance();

		tree = tree.add('h', 1);
		tree = tree.add('y', 2);
		tree = tree.add('d', 3);
		tree = tree.add('r', 4);
		tree = tree.add('o', 5);
		tree = tree.add('m', 6);
		tree = tree.add('a', 7);
		tree = tree.add('g', 8);
		tree = tree.add('n', 9);
		tree = tree.add('e', 10);
		tree = tree.add('t', 11);
		tree = tree.add('i', 12);
		tree = tree.add('c', 13);

		return tree;
	}

	private static SearchTreeMap<Character, Integer> sampleMap() {
		SearchTreeMap<Character, Integer> map = new SearchTreeMap<Character, Integer>();

		map.put('P', 80);
		map.put('l', 108);
		map.put('a', 98);
		map.put('t', 116);
		map.put('y', 121);
		map.put('p', 112);
		map.put('u', 117);
		map.put('s', 115);

		return map;
	}

}
