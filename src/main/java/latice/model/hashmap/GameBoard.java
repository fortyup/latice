package latice.model.hashmap;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import latice.model.BoxType;
import latice.model.Position;
import latice.model.Tile;
import latice.model.constant.Constants;
import latice.visual.BoxFX;

public class GameBoard {

	private static int firstColumnOrRow = 0;
	private static int lastColumnOrRow = 9;
	private static int lastBox = 8;
	private static int middleColumnOrRow = 4;
	private static int diagonalColumnOrRow = 6;
	private static int theSunsOnDiagonals = 3;
	private static int tileNear = 1;


	public final Map<Position, BoxFX> boxes;
	
	public GameBoard() {
		this.boxes = new HashMap<>();
	}
	
	public Map<Position, BoxFX> boxes() {
		return boxes;
	}
	
	public Map<Position,BoxFX> generateGameBoard() {

		
		for (int i = firstColumnOrRow; i < theSunsOnDiagonals; i++) {
			this.boxes.put(new Position(i,i), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		}
		for (int i = diagonalColumnOrRow; i < lastColumnOrRow; i++) {
			this.boxes.put(new Position(i,i), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		}
		
		for (int i = firstColumnOrRow; i < theSunsOnDiagonals; i++) {
			this.boxes.put(new Position(i,lastColumnOrRow-i-tileNear), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		}
		
		for (int i = diagonalColumnOrRow; i < lastColumnOrRow; i++) {
			this.boxes.put(new Position(i,lastColumnOrRow-i-tileNear), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		}
		
		this.boxes.put(new Position(firstColumnOrRow,middleColumnOrRow), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		this.boxes.put(new Position(middleColumnOrRow,firstColumnOrRow), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		this.boxes.put(new Position(lastBox,middleColumnOrRow), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		this.boxes.put(new Position(middleColumnOrRow,lastBox), new BoxFX(Tile.NO, new Image(Constants.ImgSun,Constants.SIZE,Constants.SIZE,true,true), BoxType.SUN));
		
		this.boxes.put(new Position(middleColumnOrRow,middleColumnOrRow), new BoxFX(Tile.NO, new Image(Constants.ImgMoon,Constants.SIZE,Constants.SIZE,true,true), BoxType.MOON));
		
		for (int i = firstColumnOrRow; i < lastColumnOrRow; i++) {
			for (int j = firstColumnOrRow; j < lastColumnOrRow; j++) {
				if (this.getBox(new Position(i,j)) == null)
					this.boxes.put(new Position(i,j), new BoxFX(Tile.NO, new Image(Constants.ImgBasic,Constants.SIZE,Constants.SIZE,true,true), BoxType.BASIC));
			}
		}	
		return boxes;
	}
	
	public boolean isBoxAt(Position position) {
		return (this.boxes.containsKey(position));
	}

	public boolean isEmpty() {
		return (this.boxes.isEmpty());
	}

	public void clear() {
		this.boxes.clear();
	}

	public int howManyBoxesOnBoard() {
		return this.boxes.size();
	}

	public BoxFX boxAt(Position position) {
		return this.boxes.get(position);
	}

	public void removeBoxAt(Position position) {
		this.boxes.remove(position);
	}
	
	public BoxFX getBox(Position position) {
		for (Map.Entry<Position, BoxFX> entry : boxes.entrySet()) {
			if (entry.getKey().column().equals(position.column()) && entry.getKey().row().equals(position.row()) ) {
				return entry.getValue();
			}
		}
		return null;
	}
}
