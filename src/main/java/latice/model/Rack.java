package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	
	private static int rackSize = 5;
	private static int firstTile = 1;
	private static int theTile = 0;
	private List<Tile> tiles;
	
	public Rack(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public static List<Tile> createRack(List<Tile> stack) {
		List<Tile> rack = new ArrayList<>();
		for (int i = firstTile; i <= rackSize; i++) {
			rack.add(stack.get(theTile));
		}
		return rack;
	}

	public static List<Tile> clearRack(List<Tile> rack, List<Tile> stack) {
		if (!rack.isEmpty()) {
			for (int i = firstTile; i <= rackSize; i++) {
				stack.add(rack.get(theTile));
				rack.remove(theTile);
		}
		return rack;
		}
		return rack;
	}
		
	
	public List<Tile> getTiles() {
		return tiles;
	}
		
		

}
