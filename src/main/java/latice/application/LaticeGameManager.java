package latice.application;

import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import latice.model.CurrentPlayer;
import latice.model.Referee;
import latice.model.constant.Constants;
import latice.model.hashmap.GameBoard;
import latice.model.hashmap.GameBoardFX;
import latice.visual.PlayerFX;
import latice.visual.RackFX;
import latice.visual.TileFX;


public class LaticeGameManager extends Application {
	
	private static double sceneWidth = 1200;
	private static double sceneHeight = 800;
	private int scoreToMove = 2;
	private double lastTurn = 20;
	
	private VBox vboxrackP1 = new VBox();
	private VBox vboxrackP2 = new VBox();
	
	private static BorderPane root = new BorderPane();
	static Scene scene = new Scene(root, sceneWidth, sceneHeight);
	
	private Label labelscoreP1 = new Label();
	private Label labelscoreP2 = new Label();
	
	private Label labelTileP1 = new Label();
	private Label labelTileP2 = new Label();
	
	private Label labelNameP1 = new Label();
	private Label labelNameP2 = new Label();
	
	private GameBoard gameBoard = new GameBoard();
	
	private Button btnsuivantP1 = new Button("Finir le tour !");
	private Button btnsuivantP2 = new Button("Finir le tour !");
	
	private Button btnChangeRackP1 = new Button("Changer de rack");
	private Button btnChangeRackP2 = new Button("Changer de rack");
	
	private Button btnActionSupP1 = new Button("Acheter une action");
	private Button btnActionSupP2 = new Button("Acheter une action");
	
	private Label nombreToursRestants = new Label();
	
	private VBox gameBoardAndTurns = new VBox();
	
	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		List<TileFX> tile = TileFX.createAllTilesFX();
		List<TileFX> mixedTile = TileFX.mixTilesFX(tile);
		List<TileFX> stackP1 = TileFX.splitP1FX(mixedTile);
		List<TileFX> stackP2 = TileFX.splitP2FX(mixedTile);
		List<TileFX> rackP1 = RackFX.createRackFX(stackP1);
		List<TileFX> rackP2 = RackFX.createRackFX(stackP2);
		
		PlayerFX player1 = new PlayerFX(Menu.fieldPlayer1.getText(),stackP1, rackP1);
		PlayerFX player2 = new PlayerFX(Menu.fieldPlayer2.getText(),stackP2,rackP2);
		PlayerFX firstPlayer = PlayerFX.choosePlayer(player1, player2);
		CurrentPlayer.setCurrentPlayer(firstPlayer);
		
		primaryStage.getIcons().add(new Image(Constants.ImgLogo));
		
		labelscoreP1.setText(player1.getScore().toString());
		labelscoreP2.setText(player2.getScore().toString());
		
		labelTileP1.setText(String.valueOf(player1.getStack().size()));
		labelTileP2.setText(String.valueOf(player2.getStack().size()));	
		
		Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
		labelNameP1.setText(player1.getUsername());
		labelNameP1.setFont(font);
		labelNameP1.setTextFill(Color.WHEAT);
		
		labelNameP2.setText(player2.getUsername());
		labelNameP2.setFont(font);
		labelNameP2.setTextFill(Color.WHEAT);
		
		labelscoreP1.setFont(font);
		labelscoreP1.setTextFill(Color.AQUAMARINE);
		
		labelscoreP2.setFont(font);
		labelscoreP2.setTextFill(Color.AQUAMARINE);
		
		labelTileP1.setFont(font);
		labelTileP1.setTextFill(Color.DEEPPINK);
		
		labelTileP2.setFont(font);
		labelTileP2.setTextFill(Color.DEEPPINK);
		
		refreshTurns();
		nombreToursRestants.setFont(font);
		nombreToursRestants.setTextFill(Color.ALICEBLUE);
		
		gameBoard.generateGameBoard();
		
		GridPane gameboard = GameBoardFX.generateGridPane(gameBoard);
		BorderPane.setMargin(gameBoardAndTurns, new Insets(0, 0, 0, 20));
		
		vboxrackP1 = RackFX.createRackVbox(player1);
		vboxrackP2 = RackFX.createRackVbox(player2);
		
		btnActionSupP2.setDisable(true);
		btnActionSupP1.setDisable(true);
		
		if (firstPlayer == player1) {
			disableP2(player2);
		} else {
			disableP1(player1);
		}
		
		btnsuivantP1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	CurrentPlayer.setCurrentPlayer(player2);
            	Referee.addTurn();
            	disableP1(player1);
            	gameEnds(player1, player2);
            	labelTileP1.setText(String.valueOf(player1.getStack().size()));
            	player2.setAbleToPlay(true);
            	refreshTurns();
            }
        });
		btnsuivantP2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	CurrentPlayer.setCurrentPlayer(player1);
            	Referee.addTurn();
            	disableP2(player2);
            	gameEnds(player1, player2);
            	labelTileP2.setText(String.valueOf(player2.getStack().size()));	
            	player1.setAbleToPlay(true);
            	refreshTurns();
            }
        });
		
		btnChangeRackP1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (!player2.isAbleToPlay()) {
            		Alert dialog = new Alert(AlertType.INFORMATION);
            		dialog.setTitle(" PROBLEME ! ");
        			dialog.setHeaderText("Vous avez déja joué !");
        			dialog.setContentText("Veuillez acheter une action supplémentaire ou passer votre tour !!");

        			dialog.showAndWait();
            	} else {
	            	labelscoreP1.setText(player1.getScore().toString());
	            	giveRackPlayer(stackP1, player1);
	            	vboxrackP1 = RackFX.createRackVbox(player1);
	            	putVBoxP1();
	            	CurrentPlayer.setCurrentPlayer(player2);
	            	Referee.addTurn();
	            	disableP1(player2);
	            	gameEnds(player1, player2);
	            	labelTileP1.setText(String.valueOf(player1.getStack().size()));
	            	player2.setAbleToPlay(true);
	            	refreshTurns();
            	}
            }
        });
		
		btnChangeRackP2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (!player2.isAbleToPlay()) {
            		Alert dialog = new Alert(AlertType.INFORMATION);
            		dialog.setTitle(" PROBLEME ! ");
        			dialog.setHeaderText("Vous avez déja joué !");
        			dialog.setContentText("Veuillez acheter une action supplémentaire ou passer votre tour !!");
        			dialog.showAndWait();
            	} else {
	        		labelscoreP2.setText(player2.getScore().toString());
	        		giveRackPlayer(stackP2, player2);
	            	vboxrackP2 = RackFX.createRackVbox(player2);    	
	            	putVBoxP2();
	            	CurrentPlayer.setCurrentPlayer(player1);
	            	Referee.addTurn();
	            	disableP2(player2);
	            	gameEnds(player1, player2);
	            	labelTileP2.setText(String.valueOf(player2.getStack().size()));	
	            	player1.setAbleToPlay(true);
	            	refreshTurns();
            	}
            }
        });
		
		btnActionSupP1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
        		player1.setAbleToPlay(true);
        		player1.removePoint(scoreToMove);
        		if (player1.getScore()<scoreToMove) {
        			btnActionSupP1.setDisable(true);
        		}
            }
        });
		
		btnActionSupP2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
        		player2.setAbleToPlay(true);
        		player2.removePoint(scoreToMove);
        		if (player2.getScore()<scoreToMove) {
        			btnActionSupP2.setDisable(true);
        		}
            }
        });
		
		putVBoxP1();
		
		putVBoxP2();
		
		gameBoardAndTurns.getChildren().addAll(gameboard,nombreToursRestants);
		root.setCenter(gameBoardAndTurns);
		VBox.setMargin(nombreToursRestants, new Insets(20,0,0,0));
		gameBoardAndTurns.setAlignment(Pos.CENTER);
		
		BackgroundImage bckImg = new BackgroundImage (new Image(Constants.ImgFond,1920,900,true,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background bGround = new Background(bckImg);
		
		root.setBackground(bGround);
		
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void putVBoxP1() {
		VBox vboxP1 = new VBox();
		vboxP1.getChildren().addAll(this.labelNameP1, this.labelscoreP1,this.vboxrackP1,this.btnsuivantP1,this.btnChangeRackP1,this.btnActionSupP1,this.labelTileP1);
		VBox.setMargin(btnChangeRackP1, new Insets(0,0,10,0));
		VBox.setMargin(btnsuivantP1, new Insets(0,0,10,0));
		VBox.setMargin(labelNameP1, new Insets(0,0,10,0));
		VBox.setMargin(labelscoreP1, new Insets(0,0,10,0));
		VBox.setMargin(labelTileP1, new Insets(10,0,10,0));
		vboxP1.setAlignment(Pos.CENTER);
		vboxrackP1.setAlignment(Pos.CENTER);
		BorderPane.setMargin(vboxP1, new Insets(100, 50, 80, 50));
		root.setLeft(vboxP1);
	}

	private void giveRackPlayer(List<TileFX> stackPlayer, PlayerFX player) {
		List<TileFX> mixedStackPlayer = TileFX.mixTilesFX(stackPlayer);
    	List<TileFX> rackPlayer = RackFX.createRackFX(mixedStackPlayer);
    	player.setRack(rackPlayer);
	}
	
	private void putVBoxP2() {
		VBox vboxP2 = new VBox();
		vboxP2.getChildren().addAll(this.labelNameP2, this.labelscoreP2,this.vboxrackP2,this.btnsuivantP2,this.btnChangeRackP2,this.btnActionSupP2,this.labelTileP2);
		VBox.setMargin(btnChangeRackP2, new Insets(0,0,10,0));
		VBox.setMargin(btnsuivantP2, new Insets(0,0,10,0));
		VBox.setMargin(labelNameP2, new Insets(0,0,10,0));
		VBox.setMargin(labelscoreP2, new Insets(0,0,10,0));
		VBox.setMargin(labelTileP2, new Insets(10,0,10,0));
		vboxP2.setAlignment(Pos.CENTER);
		vboxrackP2.setAlignment(Pos.CENTER);
		BorderPane.setMargin(vboxP2, new Insets(100, 50, 80, 50));
		root.setRight(vboxP2);
	}

	private void disableP1(PlayerFX player1) {
		this.btnsuivantP1.setDisable(true);
		this.btnsuivantP2.setDisable(false);
		this.btnChangeRackP2.setDisable(false);
		this.btnChangeRackP1.setDisable(true);
		if (CurrentPlayer.getCurrentPlayer().getScore() >= scoreToMove) {
			this.btnActionSupP2.setDisable(false);
		}
		this.btnActionSupP1.setDisable(true);
		this.vboxrackP1.setVisible(false);
		this.vboxrackP2.setVisible(true);
		this.labelscoreP1.setText(player1.getScore().toString());
	}

	private void disableP2(PlayerFX player2) {
		this.btnsuivantP2.setDisable(true);
		this.btnsuivantP1.setDisable(false);
		this.btnChangeRackP2.setDisable(true);
		this.btnChangeRackP1.setDisable(false);
		if (CurrentPlayer.getCurrentPlayer().getScore() >= scoreToMove) {
			this.btnActionSupP1.setDisable(false);
		}
		this.btnActionSupP2.setDisable(true);
		this.vboxrackP1.setVisible(true);
		this.vboxrackP2.setVisible(false);
		this.labelscoreP2.setText(player2.getScore().toString());
	}

	private void gameEnds(PlayerFX player1, PlayerFX player2) {
		String gagnant;
		if (Referee.getCurrentTurn() == lastTurn ) {
			this.btnsuivantP2.setDisable(true);
			this.btnsuivantP1.setDisable(true);
			if (player2.getStack().size() > player1.getStack().size()) {
				gagnant= "Le joueur gagnant est " + player1.getUsername();
			} else if (player2.getStack().size() < player1.getStack().size()) {
				gagnant= "Le joueur gagnant est " + player2.getUsername();
			} else {
				gagnant = "Il y a égalité, " + player2.getUsername() + " et " + player1.getUsername() + " ont utilisé le même nombre de tuiles !";
			}
			Alert dialog = new Alert(AlertType.WARNING);
			dialog.setTitle(" FIN DE PARTIE ! ");
			dialog.setHeaderText("La partie est terminée !!");
			dialog.setContentText(gagnant);
			dialog.showAndWait();
    	}
	}
	
	private void refreshTurns() {
		this.nombreToursRestants.setText("Nombre de tours restants : " + (20 - Referee.getCurrentTurn()));
	}
}
