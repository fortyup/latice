package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import latice.model.Rack;
import latice.model.Tile;

class TestRack {
	
	@Test
	void testIfRackIsCreatedForPlayer1() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> first = Tile.splitP1(tile);
		List<Tile> rackPlayer1 = Rack.createRack(first);
		assertEquals(5,rackPlayer1.size());

	}
	
	@Test
	void testIfRackIsCreatedForPlayer2() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		List<Tile> rackPlayer2 = Rack.createRack(second);
		assertEquals(5,rackPlayer2.size());

	}

	@Test
	void testIfStackAndRackIsUpdateForPlayer1() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> first = Tile.splitP1(tile);
		List<Tile> rackPlayer1 = Rack.createRack(first);
		assertEquals(5,rackPlayer1.size());
		assertEquals(36,first.size());

	}
	
	@Test
	void testIfStackAndRackIsUpdateForPlayer2() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		List<Tile> rackPlayer2 = Rack.createRack(second);
		assertEquals(5,rackPlayer2.size());
		assertEquals(36,second.size());

	}
	
	@Test
	void testIfRackIsCleared() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		List<Tile> rackPlayer2 = Rack.createRack(second);
		rackPlayer2 = Rack.clearRack(rackPlayer2, second);
		assertEquals(0,rackPlayer2.size());
	}
	
	@Test
	void testIfRackIsAlreadyCleared() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		List<Tile> rackPlayer2 = Rack.createRack(second);
		rackPlayer2 = Rack.clearRack(rackPlayer2, second);
		rackPlayer2 = Rack.clearRack(rackPlayer2, second);
		assertEquals(0,rackPlayer2.size());
	}
	
	@Test
	void testIfStackIsUpdate() {
		List<Tile> tile = Tile.createAllTiles();
		List<Tile> second = Tile.splitP2(tile);
		List<Tile> rackPlayer2 = Rack.createRack(second);
		rackPlayer2 = Rack.clearRack(rackPlayer2, second);
		assertEquals(41,second.size());
	}
}
