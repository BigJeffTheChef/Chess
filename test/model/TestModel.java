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
		Hashtable<String, APiece> actualBoard = model.getBoard();
		Hashtable<String, APiece> expectBoard = new Hashtable<String, APiece>(32);
		expectBoard.put("F6", new Rook(Team.WHITE));
		expectBoard.put("D7", new Knight(Team.WHITE));


		// check sizes are equal
		int expectedSize = expectBoard.size();
		int actualSize = model.getBoard().size();
		assertEquals(expectedSize, actualSize);

		Iterator<String> keys = expectBoard.keySet().iterator();

		while (keys.hasNext()) {
			String key = keys.next();
			APiece expectedPiece = expectBoard.get(key);
			APiece actualPiece = actualBoard.get(key);
			if (!expectedPiece.pieceTypeToString().equals(actualPiece.pieceTypeToString())) {
				fail("expected=\"" + expectedPiece.pieceTypeToString() + "\", actual=\"" + actualPiece.pieceTypeToString() + "\"");
			}
		}

	}

}
