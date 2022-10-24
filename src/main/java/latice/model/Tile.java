package latice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tile {
	
	private static int twoForHalf = 2;
	
	public Color getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, shape);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return color == other.color && shape == other.shape;
	}

	public Shape getShape() {
		return shape;
	}

	public static final Tile NO = null;
	
	protected Color color;
	
	protected Shape shape;

	public Tile(Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "Tile [color=" + color + ", shape=" + shape + "]";
	}
	
	public static List<Tile> createAllTiles() {
		List<Tile> tile = new ArrayList<>();
		for (Color color : Color.values()) { 
			for (Shape shape : Shape.values()) { 
				tile.add(new Tile (color,shape));
			}
		}
		for (Color color : Color.values()) { 
			for (Shape shape : Shape.values()) { 
				tile.add(new Tile (color,shape));
			}
		}
		return tile;
	}
		
	public static List<Tile> mixTiles(List<Tile> tile) {
			Collections.shuffle(tile);
		return tile;
	}


	public static void displayTiles(List<Tile> tile) {
		for (Tile tiles : tile) {
			System.out.println(tiles.toString());
		}
	}
	
	public static List<Tile> splitP1(List<Tile> tile){
		int size = tile.size();
	  
	    return new ArrayList<>(tile.subList(0, (size) / twoForHalf));
	}
		
	public static List<Tile> splitP2(List<Tile> tile){

	    int size = tile.size();
	  
	    return new ArrayList<>(tile.subList((size) / twoForHalf, size));
	}
	
}
