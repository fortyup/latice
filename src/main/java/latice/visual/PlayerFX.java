package latice.visual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerFX {

		private String username;
		
		private Integer score;
		
		private List<TileFX> stack;
		
		private List<TileFX> rack;
		
		private static boolean ableToPlay;
		
		private static int numberOfPlayer = 2;
		
		private static int scoreNull = 0;

		public PlayerFX(String username, List<TileFX> stack, List<TileFX> rack) {
			this.username=username;
			this.score=scoreNull;
			this.stack=stack;
			this.rack=rack;
			PlayerFX.ableToPlay=true;
		}

		public static PlayerFX choosePlayer(PlayerFX player1, PlayerFX player2) {
			List<PlayerFX> listPlayer = new ArrayList<>();
			listPlayer.add(player1);
			listPlayer.add(player2);
			
			Random random = new Random();
			int nb = random.nextInt(numberOfPlayer);
			return listPlayer.get(nb);	
		}
		
		public Integer getScore() {
			return score;
		}
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<TileFX> getStack() {
			return stack;
		}

		public void setStack(List<TileFX> stack) {
			this.stack = stack;
		}

		public List<TileFX> getRack() {
			return rack;
		}

		public void setRack(List<TileFX> rack) {
			this.rack = rack;
		}

		public boolean isAbleToPlay() {
			return ableToPlay;
		}
		
		public void setAbleToPlay(boolean ableToPlay) {
			PlayerFX.ableToPlay = ableToPlay;
		}

		@Override
		public String toString() {
			return "Player [username=" + username + ", stack=" + stack + ", rack=" + rack + "]";
		}
		
		public void addPoint(int scoreToAdd) {
			this.score=this.score+scoreToAdd;
		}
		
		public void removePoint(int scoreToRemove) {
			this.score=this.score-scoreToRemove;
		}
}
