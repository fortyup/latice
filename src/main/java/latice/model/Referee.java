package latice.model;

public class Referee {

	private static int aTurn = 1;
	static int turn;
	
	public static int getCurrentTurn() {
		return turn;
	}
	
	public static void addTurn() {
		turn = turn + aTurn;
	}
}
