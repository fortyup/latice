package latice.controller;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import latice.model.BoxType;
import latice.model.Color;
import latice.model.CurrentPlayer;
import latice.model.Position;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.constant.Constants;
import latice.model.hashmap.GameBoard;
import latice.visual.BoxFX;
import latice.visual.PlayerFX;
import latice.visual.TileFX;

public class DndImageViewController {

	public static void manageSourceDragAndDrop(ImageView imgPlayer, Color color, Shape shape, VBox vboxrack, PlayerFX player ) {
		
		imgPlayer.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
	        Dragboard db = imgPlayer.startDragAndDrop(TransferMode.MOVE);
	        ClipboardContent content = new ClipboardContent();
	        content.putImage(imgPlayer.getImage());
	        content.putString(shape.toString() + "_" + color.toString() );
	        db.setContent(content);
	        event.consume();
		}
	    });
		imgPlayer.setOnDragDone(new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	        	
	           if (event.getTransferMode() == TransferMode.MOVE) {
	        	   imgPlayer.setImage(null);
	        	   VBox.setMargin(imgPlayer, null);

	            }
	             event.consume(); 

	        }
	    });
		VBox.setMargin(imgPlayer, new Insets(0,0,10,0));
		vboxrack.getChildren().addAll(imgPlayer);
	}
	
	public static void manageTargetDragAndDrop(ImageView imgBox, GameBoard gameBoard, Position position, BoxType typeBox) {
		
		List<BoxFX> boxesArround = DndChecking.getBoxAround(gameBoard,position);
		
		imgBox.setOnDragOver(new EventHandler<DragEvent>() {
			
	        public void handle(DragEvent event) {
	        	
	        	Dragboard db = event.getDragboard();
	        	
	        	PlayerFX player = CurrentPlayer.getCurrentPlayer();
				boolean ableToPlay = player.isAbleToPlay();
	        	boolean sameCollorOrShapeNear = DndChecking.sameCollorOrShapeNear(db, boxesArround);
	        	boolean gameBegin = gameBoard.getBox(new Position(Constants.FOUR,Constants.FOUR)).getTile()==Tile.NO;
	        	boolean moveAllowedToStart = event.getGestureSource() != imgBox && event.getDragboard().hasImage() && typeBox==BoxType.MOON && gameBoard.getBox(position).getTile()==Tile.NO && ableToPlay;
	        	boolean moveAllowed = event.getGestureSource() != imgBox && event.getDragboard().hasImage() && gameBoard.getBox(position).getTile()==Tile.NO && sameCollorOrShapeNear && ableToPlay;
	        	if (gameBegin) {
					if (moveAllowedToStart)  {
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
	        	} else if (moveAllowed ) {
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
	            event.consume();  
	            }
	    });
	
		imgBox.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
	        public void handle(DragEvent event) {
	
	            Dragboard db = event.getDragboard();
	
	            boolean success = false;
	            
	            if (db.hasImage()) {
	               final String SEPARATEUR = "_";
	               String[] shapeNColor = db.getString().split(SEPARATEUR);
	               String shape = shapeNColor[Constants.ZERO];
	               String color = shapeNColor[Constants.ONE];
	        	   imgBox.setImage(db.getImage());
	        	   Integer scoreMove = DndScoreController.countingScore(boxesArround,typeBox);
	        	   TileFX tileInTransition = new TileFX(Color.valueOf(color),Shape.valueOf(shape),new Image("file:Images/" + db.getString() + ".png",Constants.SIZE,Constants.SIZE,true,true));
	        	   gameBoard.getBox(position).setTile(tileInTransition);
	        	   PlayerFX currentplayer = CurrentPlayer.getCurrentPlayer();
	        	   currentplayer.getStack().remove(tileInTransition); 
	        	   currentplayer.addPoint(scoreMove);
	        	   currentplayer.setAbleToPlay(false);
	        	   success = true;
	            }
	            event.setDropCompleted(success);
	            event.consume(); 
	
	        }
	    });
		
		imgBox.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					//
				}
				event.consume();
			}
		});
		imgBox.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					//
				}
				event.consume();
			}
		});
	}
}
