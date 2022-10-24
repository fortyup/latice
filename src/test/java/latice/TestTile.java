package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;

class TestTile {
	
	//Faire un @BeforeEach car on utilise la méthode createAllTiles dans tout nos tests
	
	@Test
	void testThatChecksIfAllTilesAreCreated() {
		List<Tile> tile = Tile.createAllTiles();
		assertEquals(72,tile.size());
	}


	@Test
	void testThatChecksIfAllTilesAreSplittedForP1() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> first = Tile.splitP1(tile);
		assertEquals(36,first.size());
		
	}
	
	@Test
	void testThatChecksIfAllTilesAreSplittedForP2() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		assertEquals(36,second.size());
	}

	@Test
	void testThatChecksIfAllTilesAreMixed() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> mixed = Tile.mixTiles(tile);
		assertNotEquals(tile,mixed.size());
	}
	
	@Test
	public void testEquals() {
		Tile t1 = new Tile(Color.RED, Shape.TURTLE);
		Tile t2 = new Tile(Color.RED, Shape.TURTLE);
		assertTrue(t1.equals(t2));
	}
}