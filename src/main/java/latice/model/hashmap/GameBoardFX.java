package latice.model.hashmap;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.controller.DndImageViewController;
import latice.model.BoxType;
import latice.model.Position;
import latice.model.constant.Constants;

public class GameBoardFX {
	
	private static int firstColumn = 0;
	public static GridPane generateGridPane(GameBoard gameBoard) {
			
		GridPane board = new GridPane();
		board.setGridLinesVisible(true);
		board.setVgap(1);
		board.setHgap(1);
		
		
		for (int i = firstColumn; i < Constants.COLUMNS; i++) {
			for (int j = 0; j < Constants.ROWS; j++) {
				ImageView imgBox = new ImageView(gameBoard.getBox(new Position(i,j)).getImg());
				BoxType typeBox = gameBoard.getBox(new Position(i,j)).getType();
				board.add(imgBox, i, j);
				Position position = new Position(i,j);
				DndImageViewController.manageTargetDragAndDrop(imgBox, gameBoard, position, typeBox);
			}
		}
		
		return board;
	}
	
}

