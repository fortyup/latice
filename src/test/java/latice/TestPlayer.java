package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import latice.model.Player;
import latice.model.Tile;

public class TestPlayer {
	@Test
	void testIfAPlayerIsCorrectlyChoose() {
		Player player1 = new Player("player1", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player2 = new Player("player2", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player = Player.choosePlayer(player1, player2);
		assertTrue(player.equals(player1) || player.equals(player2));
	}
	
	@Test
	void testIfAPointIsAddToAPlayer() {
		Player player1 = new Player("player1", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player2 = new Player("player2", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player = Player.choosePlayer(player1, player2);
		player.addAPoint(player);
		assertEquals(1, player.score);
	}
	
	@Test
	void testIfAPointIsRemoveToAPlayer() {
		Player player1 = new Player("player1", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player2 = new Player("player2", new ArrayList<Tile>(), new ArrayList<Tile>());
		Player player = Player.choosePlayer(player1, player2);
		player.addAPoint(player);
		player.removeAPoint(player);
		assertEquals(0, player.score);
	}

}
