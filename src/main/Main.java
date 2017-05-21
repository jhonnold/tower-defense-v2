package main;

import com.jfoenix.controls.JFXButton;
import gui.Game;
import gui.MainMenu;
import gui.Shop;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The Main file for the whole program. Launches the JavaFX application and
 * starts the Game Loop.
 * 
 * @author Jay
 *
 */
public class Main extends Application implements Runnable {

	private Stage primary;
	private AnchorPane leftPane, rightPane;
	
	// Canvas for the game
	private Game game;
	private Shop shop;

	private FXMLLoader fxmlLoader;

	private boolean running = false;

	// Game Loop Variables
	private final int TICKS_PER_SECOND = 100;
	private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;
	
	public static long CURRENT_GAME_TICK = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primary = primaryStage;
		
		primaryStage.setTitle("tower-defense");

		// Load the scene from FXML
		fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		// Move the game into the leftPane
		leftPane = ((Controller) fxmlLoader.getController()).getLeftPane();
		new MainMenu(leftPane, this);

		// Set the frame rate to ~60 FPS
		Timeline animationLoop = new Timeline();
		animationLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent event) -> {
			if (game != null) {
				game.repaint();
			}
		});

		animationLoop.getKeyFrames().add(kf);

		primaryStage.show();

		// Start the Game Loop and the animation loops
		animationLoop.play();
	}

	@Override
	public void run() {
		running = true;

		double next_game_tick = System.currentTimeMillis();
		int loops;

		while (running) {
			loops = 0;
			while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
				// This loop is to update the game. Not to draw it.
				game.update();
				next_game_tick += SKIP_TICKS;
				loops++;
				CURRENT_GAME_TICK++;
			}
		}
	}

	public void startGame() {
		
		game = new Game(1024, 640);
		leftPane.getChildren().clear();
		leftPane.getChildren().add(game);
		
		JFXButton levelButton = new JFXButton("Next Level");
		levelButton.setLayoutX(10);
		levelButton.setLayoutY(10);
		levelButton.setStyle("-fx-font: 32 'KenVector Future'; ");
		
		leftPane.getChildren().add(levelButton);
		game.setLevelButton(levelButton);
		
		rightPane = ((Controller) fxmlLoader.getController()).getRightPane();
		shop = new Shop(rightPane);
		shop.setGameListener(game);
		shop.setMainListener(this);
		//rightPane.getChildren().add(shop);
		
		primary.sizeToScene();
		
		new Thread(this, "Game Loop").start();
	}
	
	public void endGame() {
		
		running = false;
		
		leftPane.getChildren().clear();
		rightPane.getChildren().clear();
		
		new MainMenu(leftPane, this);
		
		primary.sizeToScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
