package latice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.BoxType;
import latice.model.Color;
import latice.model.Position;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.constant.Constants;
import latice.model.hashmap.GameBoard;
import latice.visual.BoxFX;

class GameBoardTest {

	private static final Tile RED_FEATHER = new Tile(Color.RED,Shape.FEATHER);

	private static final Position FIRST_POSITION = new Position(0, 0);
	private static final Position LAST_POSITION = new Position(Constants.ROWS, Constants.COLUMNS);
	
	GameBoard gameBoard;

	@BeforeEach
	public void cleanGameBoards() {
		gameBoard = new GameBoard();
	}

	@Test
	void return_false_when_position_is_without_box() {
		gameBoard.boxes.put(FIRST_POSITION, new BoxFX(RED_FEATHER, null, BoxType.SUN));
		assertTrue(gameBoard.isBoxAt(FIRST_POSITION));

		assertTrue(gameBoard.getBox(FIRST_POSITION).isTileAt());

		assertFalse(gameBoard.isBoxAt(LAST_POSITION));
	}

	@Test
	void the_gameBoard_is_empty_no_boxes_at_the_beginning_of_game() {
		
		assertTrue(gameBoard.isEmpty());
		
	}
	
	@Test
	void return_the_number_of_boxes() {
		gameBoard.boxes.put(FIRST_POSITION, new BoxFX(RED_FEATHER, null, BoxType.SUN));
		assertEquals(1, gameBoard.howManyBoxesOnBoard());
		
	}
	
	@Test
	void the_returned_box_is_the_good_one() {
		BoxFX box = new BoxFX(RED_FEATHER, null, BoxType.SUN);
		gameBoard.boxes.put(FIRST_POSITION,box);
		assertEquals(box, gameBoard.getBox(FIRST_POSITION));
		
	}
	
	@Test
	void the_chosen_box_is_removed() {
		BoxFX box = new BoxFX(RED_FEATHER, null, BoxType.SUN);
		gameBoard.boxes.put(FIRST_POSITION,box);
		gameBoard.removeBoxAt(FIRST_POSITION);
		assertTrue(gameBoard.isEmpty());
		
	}
	
	@Test
	void the_boxes_are_returned() {
		BoxFX box = new BoxFX(RED_FEATHER, null, BoxType.SUN);
		HashMap<Position, BoxFX> boxesTest = new HashMap<>();
		boxesTest.put(FIRST_POSITION, box);
		gameBoard.boxes.put(FIRST_POSITION,box);
		assertEquals(boxesTest,gameBoard.boxes());
		
	}
	
	@Test
	void return_the_box_at_the_good_position() {
		BoxFX box = new BoxFX(RED_FEATHER, null, BoxType.SUN);
		gameBoard.boxes.put(FIRST_POSITION,box);
		assertEquals(box,gameBoard.boxAt(FIRST_POSITION));
		
	}
	
	@Test
	void the_boxes_are_cleared() {
		BoxFX box = new BoxFX(RED_FEATHER, null, BoxType.SUN);
		gameBoard.boxes.put(FIRST_POSITION,box);
		gameBoard.clear();
		assertTrue(gameBoard.isEmpty());
		
	}

}