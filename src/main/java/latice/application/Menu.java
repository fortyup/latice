package latice.application;
	
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import latice.model.constant.Constants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Menu extends Application {
	
	static TextField fieldPlayer1 = new TextField();
	
	static TextField fieldPlayer2 = new TextField();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(Constants.ImgLogo));
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Label latice = new Label("LATICE");
		latice.setStyle("-fx-text-fill: white; -fx-border-width: 0 0 1 0; -fx-underline: true; -fx-font-size : 20px");
        VBox logo = new VBox();
		logo.getChildren().add(latice);
        logo.setAlignment(Pos.CENTER);
		
		VBox vboxbtn = new VBox();
		vboxbtn.setAlignment(Pos.CENTER);
		vboxbtn.setSpacing(50);
        vboxbtn.setPadding(new Insets(10, 0, 10, 0));
		Button btnPlay = new Button("Jouer");
		btnPlay.setStyle("-fx-background-color: null; -fx-text-fill: white; -fx-font-size : 5em");
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Label namePlayer1 = new Label("Joueur 1 : ");	
				
				Label namePlayer2 = new Label("Joueur 2 : ");

				VBox secondaryLayout = new VBox();
				HBox player1 = new HBox();
				player1.getChildren().add(namePlayer1);
				player1.getChildren().add(fieldPlayer1);
				player1.setAlignment(Pos.CENTER);
				secondaryLayout.getChildren().add(player1);
				HBox player2 = new HBox();
				player2.getChildren().add(namePlayer2);
				player2.getChildren().add(fieldPlayer2);
				player2.setAlignment(Pos.CENTER);
				secondaryLayout.getChildren().add(player2);
				Button btnValid = new Button("Valider");
				btnValid.setAlignment(Pos.CENTER);
				
				btnValid.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if ("".equals(fieldPlayer1.getText())) {
							fieldPlayer1.setText("Player1");
						}
						if ("".equals(fieldPlayer2.getText())) {
							fieldPlayer2.setText("Player2");
						}
						Stage stage = (Stage) btnValid.getScene().getWindow();
					    stage.close();
					    LaticeGameManager laticeGameManager = new LaticeGameManager();
						try {
							laticeGameManager.start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
				
				secondaryLayout.getChildren().add(btnValid);

				Scene secondScene = new Scene(secondaryLayout, 300, 100);

				Stage newWindow = new Stage();
				newWindow.setTitle("Choix des pseudos");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(primaryStage.getX() + 200);
				newWindow.setY(primaryStage.getY() + 100);

				newWindow.show();
			}
		});
		
		Button btnRule = new Button("Règles");
		btnRule.setStyle("-fx-background-color: null; -fx-text-fill: white; -fx-font-size : 5em");
		
		Button btnLeft = new Button("Quitter");
		btnLeft.setStyle("-fx-background-color: null; -fx-text-fill: white; -fx-font-size : 5em");
		btnLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) btnLeft.getScene().getWindow();
			    stage.close();

			}
		});
		
		btnRule.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WebView browser = new WebView();
		        WebEngine webEngine = browser.getEngine();
		        webEngine.load("https://latice.com/how/");
			}
		});
	
		vboxbtn.getChildren().add(btnPlay);
		vboxbtn.getChildren().add(btnRule);
		vboxbtn.getChildren().add(btnLeft);
		
		VBox vboxcredit = new VBox();
		vboxcredit.setAlignment(Pos.CENTER);
		Label credit = new Label("Par Bayram GOKCEN & Maxime CAPEL");
		credit.setStyle("-fx-border-width: 0 0 1 0; -fx-underline: true; -fx-text-fill: white; -fx-font-size : 15px");
		Label sae = new Label("SAÉ S2.01/S2.02 LATICE");
		sae.setStyle("-fx-border-width: 0 0 1 0; -fx-underline: true; -fx-text-fill: white; -fx-font-size : 15px");
		vboxcredit.getChildren().add(credit);
		vboxcredit.getChildren().add(sae);
		
		File f = new File("bgLatice.mp4");
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(true);
        MediaView mv = new MediaView(mp);
        Pane test = new Pane(mv);
		
		BorderPane root = new BorderPane();
		
		StackPane rroot = new StackPane();
		rroot.getChildren().add(test);
		rroot.getChildren().add(root);
		
		Scene scene = new Scene(rroot, 1200, 800);
		root.setTop(logo);
		root.setCenter(vboxbtn);
		root.setBottom(vboxcredit);
		
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
