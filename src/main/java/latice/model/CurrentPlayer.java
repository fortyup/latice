package latice.model;

import latice.visual.PlayerFX;

public class CurrentPlayer {

	static PlayerFX player;

	public static PlayerFX getCurrentPlayer() {
		return player;
	}

	public static void setCurrentPlayer(PlayerFX player) {
		CurrentPlayer.player = player;
	}
	
	
}
