package latice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player {

	private String username;
	
	public Integer score;
	
	private List<Tile> stack;
	
	private List<Tile> rack;
	
	private static boolean ableToPlay;

	public Player(String username, List<Tile> stack, List<Tile> rack) {
		this.username=username;
		this.setScore(0);
		this.stack=stack;
		this.rack=rack;
	}

	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<Tile> getStack() {
		return stack;
	}



	public void setStack(List<Tile> stack) {
		this.stack = stack;
	}



	public List<Tile> getRack() {
		return rack;
	}



	public void setRack(List<Tile> rack) {
		this.rack = rack;
	}

	@Override
	public String toString() {
		return "Player [username=" + username + ", stack=" + stack + ", rack=" + rack + "]";
	}
	
	public static Player choosePlayer(Player player1, Player player2) {
		List<Player> listPlayer = new ArrayList<>();
		listPlayer.add(player1);
		listPlayer.add(player2);
		
		Random random = new Random();
		int nb = random.nextInt(2);
		return listPlayer.get(nb);
			
		}
	
	public void addAPoint(Player player) {
		player.setScore(player.getScore()+1);
	}
	
	public void removeAPoint(Player player) {
		player.setScore(player.getScore()-1);
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
