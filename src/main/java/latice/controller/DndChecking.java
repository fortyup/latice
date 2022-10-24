package latice.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.Dragboard;
import latice.model.Color;
import latice.model.Position;
import latice.model.Shape;
import latice.model.constant.Constants;
import latice.model.hashmap.GameBoard;
import latice.visual.BoxFX;

public class DndChecking {

    private static int firstColumn = 0;
	private static int notAnyTile = 0;
	private static int shapePart = 0;
	private static int lastColumn = 8;
	private static int colorPart = 1;
	private static int nextTo = 1;
	private static int oneMoreTile = 1;
	
	public static List<BoxFX> getBoxAround(GameBoard gameBoard, Position position) {
		List<BoxFX> boxes = new ArrayList<>();
		if (position.column() > firstColumn ) {
    		boxes.add(gameBoard.getBox(new Position(position.column()-nextTo,position.row())));
    	}
		
		if (position.row() > firstColumn ) {
    		boxes.add(gameBoard.getBox(new Position(position.column(),position.row()-nextTo)));
        }
		
		if (position.column() < lastColumn) {
			boxes.add(gameBoard.getBox(new Position(position.column()+nextTo,position.row())));
		}
		
		if (position.row() < lastColumn) {
			boxes.add(gameBoard.getBox(new Position(position.column(),position.row()+nextTo)));
		}
		return boxes;
	}
	public static boolean sameCollorOrShapeNear(Dragboard db, List<BoxFX>boxesArround) {
		
		Integer numberOfNullTiles = notAnyTile;
		final String SEPARATEUR = "_";
        String[] shapeNColor = db.getString().split(SEPARATEUR);
        String color = shapeNColor[colorPart];
        String shape = shapeNColor[shapePart];
        
        for (BoxFX boxFX : boxesArround) {
        	if (boxFX.getTile()!=null) {
                if (boxFX.getTile().getShape()!=Shape.valueOf(shape) && boxFX.getTile().getColor()!=Color.valueOf(color)) {
                	return false;
                } 
        	} else {
                    numberOfNullTiles = numberOfNullTiles + oneMoreTile;
                }
            }
            if (numberOfNullTiles==boxesArround.size()) {
                return false;
            }
            return true;
	}
	
}
