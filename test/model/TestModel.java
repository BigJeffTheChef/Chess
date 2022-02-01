package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import junit.framework.AssertionFailedError;
import model.Enums.Layout;
import model.Enums.Team;
import model.pieces.APiece;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;

class TestModel {

	private Model model;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		model = new Model(Layout.UNIT_TESTS);
	}

	// Constructor

	@Disabled
	@Test
	void testModel() {
		fail("Not yet implemented");
	}

	// convertCoords() overloaded methods

	@Test
	void testConvertCoords_StringToIntArray() {
		int[] expected, actual;

		expected = new int[] { 0, 0 };
		actual = Model.convertCoords("A1");
		assertArrayEquals(expected, actual);

		expected = new int[] { 7, 7 };
		actual = Model.convertCoords("H8");
		assertArrayEquals(expected, actual);

		expected = new int[] { 3, 6 };
		actual = Model.convertCoords("G4");
		assertArrayEquals(expected, actual);
	}

	@Test
	void testConvertCoords_IntArrayToString() {
		String expected, actual;

		expected = "A1";
		actual = Model.convertCoords(new int[] { 0, 0 });
		assertEquals(expected, actual);

		expected = "H8";
		actual = Model.convertCoords(new int[] { 7, 7 });
		assertEquals(expected, actual);

		expected = "F4";
		actual = Model.convertCoords(new int[] { 3, 5 });
		assertEquals(expected, actual);
	}

	// getCaptured()

	@Test
	void testGetCaptured() {
		fail("Not yet implemented");
	}

	// getTeamNames()

	@Test
	void testGetTeamNames() {
		String[] expected, actual;

		expected = new String[] { "Black Name Unset", "White Name Unset" };
		actual = model.getTeamNames();
		assertArrayEquals(expected, actual);
	}

	// setTeamNames()

	@Test
	void testSetTeamNames() {
		model.setTeamNames("Black Team Re-set", "White Team Re-set");
		String[] expected = new String[] { "Black Team Re-set", "White Team Re-set" };
		String[] actual = model.getTeamNames();
		assertArrayEquals(expected, actual);
	}

	// getLayout()

	@Test
	void testGetLayout() {
		Layout actual, expected;
		expected = Layout.UNIT_TESTS;
		actual = model.getLayout();
		assertEquals(expected, actual);
	}

	@Test
	void testGetBoard() {
		Hashtable<int[], APiece> actualBoard = model.getBoard();
		Hashtable<int[], APiece> expectBoard = new Hashtable<int[], APiece>(32);
		expectBoard.put(new int[] { 5, 6 }, new Rook(Team.WHITE));
		expectBoard.put(new int[] { 6, 5 }, new Knight(Team.WHITE));


		// check sizes are equal
		int expectedSize = expectBoard.size();
		int actualSize = model.getBoard().size();
		assertEquals(expectedSize, actualSize);

		//		Iterator<int[]> expectedKeys = expectBoard.keySet().iterator();
		//		Iterator<int[]> actualKeys = actualBoard.keySet().iterator();
		Object[] keys = expectBoard.keySet().toArray();
		Object[] keys2 = actualBoard.keySet().toArray();
		
		for (int i = 0; i < keys.length && i < keys2.length; i++) {
			System.out.println(Arrays.toString((int[]) keys[i]));
			System.out.println(Arrays.toString((int[]) keys2[i]));
		}
		
//		for (int i = 0; i < keys.length; i++) {
//			int[] key = (int[]) keys[i];
//			System.out.println(Arrays.toString(key));
//			System.out.println(expectBoard.get(key).toString());
//			System.out.println(actualBoard.get(key).toString());
//		}


		//		for (int i = 0; expectedKeys.hasNext(); i++) {
		//			int[] e = expectedKeys.next();
		////			int[] a = actualKeys.next();
		////			if (!Arrays.toString(a).equals(Arrays.toString(e))) {
		////				System.out.println("not equal!");
		////			}
		//			
		//			
		//			System.out.println(expectBoard.get(Arrays.copyOf(e, e.length)));
		//			System.out.println(actualBoard.get(Arrays.copyOf(e, e.length)));
		//			System.out.println("-----");
		//		}
		//		for (int[] key : expectBoard.keySet()) {
		//			System.out.println("key " + Arrays.toString(key));
		//			System.out.println("value " + expectBoard.get(key));
		//		}
		//		System.out.println("---");
		//		for (int[] key : actualBoard.keySet()) {
		//			System.out.println("key " + Arrays.toString(key));
		//			System.out.println("value " + actualBoard.get(key));
		//		}
		//		System.out.println("-----------------------------------");
		//
		//		for (int[] key : expectBoard.keySet()) {
		//			System.out.println("key " + Arrays.toString(key));
		//			System.out.println("expected value: " + expectBoard.get(key));
		//			System.out.println("actual value: " + actualBoard.get(key));
		//
		//		}

		//		// check key:value pairs are equal
		//		for (int[] key : expectBoard.keySet()) {
		//			// get the APieces
		//			APiece expectedPiece = expectBoard.get(key);
		//			APiece actualPiece = actualBoard.get(key);
		//			System.out.println(Arrays.toString(key));
		//			// if either is null then something has went wrong for sure
		//			if (expectedPiece == null || actualPiece == null) {
		//				fail("One of or both of the APiece's was null! expectedPiece=" + expectedPiece + ", actualPiece=" + actualPiece);
		//			}
		//
		//			// get a non-unique string representation of each APiece
		//			String expectedStr = expectedPiece.pieceTypeToString();
		//			String actualStr = actualPiece.pieceTypeToString();
		//			if (!expectedStr.equals(actualStr)) {
		//				fail("values's of key " + Arrays.toString(key) + " were not equal [expected=" + expectBoard.get(key) + ", actual="
		//						+ actualBoard.get(key));
		//			}
		//		}

		//assertEquals(expectBoard, model.getBoard());
		//assertSame(testBoard, model.getBoard());

	}

}
