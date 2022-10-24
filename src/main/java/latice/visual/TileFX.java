package latice.visual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;
import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.constant.Constants;

public class TileFX extends Tile{

	private Image img;

	public TileFX(Color color, Shape shape, Image img) {
		super(color, shape);
		this.img = img;
	}

	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + ", img=" + img + "]";
	}
	
	//Create set (All tiles)
	public static List<TileFX> createAllTilesFX() {
		List<TileFX> tile = new ArrayList<>();
		for (Color color : Color.values()) { 
			for (Shape shape : Shape.values()) { 
				tile.add(new TileFX (color,shape,new Image("file:Images/" + shape + "_" + color + ".png",Constants.SIZE,Constants.SIZE,true,true)));
			}
		}
		for (Color color : Color.values()) { 
			for (Shape shape : Shape.values()) { 
				tile.add(new TileFX (color,shape,new Image("file:Images/" + shape + "_" + color + ".png",Constants.SIZE,Constants.SIZE,true,true)));
			}
		}
		return tile;
	}
	
	public Image getImg() {
		return img;
	}

	//Mix the set
		public static List<TileFX> mixTilesFX(List<TileFX> tile) {
			for(int i =0; i<5;i++){
				Collections.shuffle(tile);
				}
			return tile;
		}


		public static void displayTilesFX(List<TileFX> tile) {
			for (TileFX tiles : tile) {
				System.out.println(tiles.toString());
			}
		}
		
		//Create stack (1/2 tiles for each player)
		public static List<TileFX> splitP1FX(List<TileFX> tile){
			int size = tile.size();
		  
		    return new ArrayList<>(tile.subList(0, (size) / 2));
		}
			
		public static List<TileFX> splitP2FX(List<TileFX> tile){

		    int size = tile.size();
		  
		    return new ArrayList<>(tile.subList((size) / 2, size));
		}
	
}
