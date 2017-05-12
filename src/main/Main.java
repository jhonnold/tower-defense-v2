package main;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

	// Canvas for the game
	private Game game;

	private boolean running = false;

	// Game Loop Variables
	private final int TICKS_PER_SECOND = 100;
	private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("tower-defense");

		// Load the scene from FXML
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		// Move the game into the leftPane
		AnchorPane leftPane = ((Controller) fxmlLoader.getController()).getLeftPane();
		game = new Game(1024, 640);
		leftPane.getChildren().add(game);

		// Set the frame rate to ~60 FPS
		Timeline animationLoop = new Timeline();
		animationLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent event) -> {
			game.repaint();
		});

		animationLoop.getKeyFrames().add(kf);

		primaryStage.show();

		// Start the Game Loop and the animation loops
		new Thread(this, "Game Loop").start();
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
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
