package latice.application;

import java.util.List;
import latice.model.Rack;
import latice.model.Player;
import latice.model.Tile;

public class LaticeApplicationConsole {
	
	public static void main(String[] args) {
		//Initialisation du jeu
		List<Tile> tile = Tile.createAllTiles();
		System.out.println("Création de toutes les tuiles");
		System.out.println("");
		Tile.displayTiles(tile);
		System.out.println("");
		
		
		System.out.println("Mélange de toutes les tuiles");
		System.out.println("");
		List<Tile> mixedTile = Tile.mixTiles(tile);
		Tile.displayTiles(mixedTile);
		System.out.println("");
		
		
		System.out.println("Séparation de toutes les tuiles");
		System.out.println("");
		List<Tile> stackPlayer1 = Tile.splitP1(tile);
		List<Tile> stackPlayer2 = Tile.splitP2(tile);
		System.out.println("Création de la pile du joueur 1");
		Tile.displayTiles(stackPlayer1);
		System.out.println("");
		System.out.println("Création de la pile du joueur 2");
		Tile.displayTiles(stackPlayer2);
		System.out.println("");
		
		
		System.out.println("Creation des rack");
		List<Tile> rackPlayer1 = Rack.createRack(stackPlayer1);
		System.out.println("");
		System.out.println("Rack player1 :");
		Tile.displayTiles(rackPlayer1);
		System.out.println("");
		List<Tile> rackPlayer2 = Rack.createRack(stackPlayer2);
		System.out.println("Rack player2 :");
		Tile.displayTiles(rackPlayer2);
		
		
		Player player1 = new Player("player1",stackPlayer1, rackPlayer1);
		Player player2 = new Player("player2",stackPlayer2,rackPlayer2);
		System.out.println("");
		System.out.println("Il y a " + rackPlayer2.size() + " tuiles dans le rack.");
		System.out.println("Il reste " + stackPlayer2.size() + " tuiles dans la pile.");
		System.out.println("");
		System.out.println("Affichage aléatoire des joueurs : ");
		Player.choosePlayer(player1,player2);
		System.out.println("");
		System.out.println("Vider le rack : ");
		rackPlayer2 = Rack.clearRack(rackPlayer2, stackPlayer2);
		Tile.displayTiles(rackPlayer2);
		System.out.println("Il y a " + rackPlayer2.size() + " tuiles dans le rack.");
		System.out.println("Il reste " + stackPlayer2.size() + " tuiles dans la pile.");
		rackPlayer2 = Rack.createRack(stackPlayer2);
		System.out.println("");
		System.out.println("Rack player2 :");
		Tile.displayTiles(rackPlayer2);
		
		
		
		
		
	}
		
		
}
			