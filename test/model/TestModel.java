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
import model.ModelEnums.Layout;
import model.ModelEnums.StoredStrings;
import model.ModelEnums.Team;
import model.pieces.APiece;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;

class TestModel {

	private Model model;

	@BeforeEach
	void setUp() throws Exception {
		model = new Model(Layout.UNIT_TESTS);
	}

	///////////////////////
	// Constructor		//
	/////////////////////

	@Test
	void testModel() {
		assertEquals(Layout.UNIT_TESTS, model.getLayout());
	}

	///////////////////////
	// Gets n Sets		//
	/////////////////////

	// captured field

	@Disabled @Test
	void testGetCaptured() {
		fail("Not yet implemented");
	}

	// teamNames field
	
	@Test
	void testGetSetTeamNames_Valid() {
		String[] expected, actual;
		expected = new String[] { StoredStrings.PLAYER_1_NAME.toString(), StoredStrings.PLAYER_2_NAME.toString() };
		actual = model.getTeamNames();
		assertArrayEquals(expected, actual);
		
		String team1ExpectedName = "Black Team Re-set";
		String team2ExceptedName = "White Team Re-set";
		model.setTeamNames(team1ExpectedName, team2ExceptedName);
		expected = new String[] { team1ExpectedName, team2ExceptedName };
		actual = model.getTeamNames();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testSetTeamNames_Inalid() {
		assertThrows(IllegalArgumentException.class, ()->{
			model.setTeamNames("", "A Name");
		});
		assertThrows(IllegalArgumentException.class, ()->{
			model.setTeamNames("A Name", "");
		});
		assertThrows(NullPointerException.class, ()->{
			model.setTeamNames(null, "A Name");
		});
		assertThrows(NullPointerException.class, ()->{
			model.setTeamNames("A Name", null);
		});
	}

	// layout field
	
	@Test
	void testGetLayout() {
		assertEquals(Layout.UNIT_TESTS, model.getLayout());
	}

	// board field
	
	@Test
	void testGetBoard() {
		Hashtable<String, APiece> actualBoard = model.getBoard();
		Hashtable<String, APiece> expectedBoard = new Hashtable<String, APiece>(32);
		expectedBoard.put("F6", new Rook(Team.WHITE));
		expectedBoard.put("D7", new Knight(Team.WHITE));

		// check sizes are equal
		int expectedSize = expectedBoard.size();
		int actualSize = model.getBoard().size();
		assertEquals(expectedSize, actualSize);

		Iterator<String> keys = expectedBoard.keySet().iterator();

		while (keys.hasNext()) {
			String key = keys.next();
			String expectedPieceRepresentation = String.valueOf(expectedBoard.get(key));
			String actualPieceRepresentation = String.valueOf(actualBoard.get(key));
			if (!expectedPieceRepresentation.equals(actualPieceRepresentation)) {
				fail("expected \"" + expectedPieceRepresentation + "\" but was \"" + actualPieceRepresentation + "\"");
			}
		}

	}

	///////////////////////
	// Methods			//
	/////////////////////

	// convertCoords()

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

}
