package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
	
	@Disabled @Test
	void testModel() {
		fail("Not yet implemented");
	}

	// convertCoords() overloaded methods
	
	@Test
	void testConvertCoords_StringToIntArray() {
		int[] expected, actual;
		
		expected = new int[] {0,0};
		actual = Model.convertCoords("A1");
		assertArrayEquals(expected, actual);
		
		expected = new int[] {7,7};
		actual = Model.convertCoords("H8");
		assertArrayEquals(expected, actual);	
		
		expected = new int[] {3,6};
		actual = Model.convertCoords("G4");
		assertArrayEquals(expected, actual);
	}

	@Test
	void testConvertCoords_IntArrayToString() {
		String expected, actual;
		
		expected = "A1";
		actual = Model.convertCoords(new int[] {0,0});
		assertEquals(expected, actual);
		
		expected = "H8";
		actual = Model.convertCoords(new int[] {7,7});
		assertEquals(expected, actual);
		
		expected = "F4";
		actual = Model.convertCoords(new int[] {3,5});
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
		
		expected = new String[] {"Black Name Unset", "White Name Unset"};
		actual = model.getTeamNames();
		assertArrayEquals(expected, actual);
	}
	
	// setTeamNames()
	
	@Test
	void testSetTeamNames() {
		model.setTeamNames("Black Team Re-set", "White Team Re-set");
		String[] expected = new String[] {"Black Team Re-set", "White Team Re-set"};
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
		//TODO when i learn how to assert equality between two objects contents
		Hashtable<int[], APiece> testBoard = new Hashtable<int[], APiece>(32);
		testBoard.put(Model.convertCoords("E4"), new Rook(Team.WHITE));
		testBoard.put(Model.convertCoords("F5"), new Rook(Team.WHITE));
		
		int expectedSize = testBoard.size();
		int actualSize = model.getBoard().size();
		assertEquals(expectedSize, actualSize);
	
		assertEquals(testBoard, model.getBoard());
		//assertSame(testBoard, model.getBoard());

	}

}
