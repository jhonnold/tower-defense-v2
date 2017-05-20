package gui;

import entity.tower.SpeedTower;
import entity.tower.PowerTower;
import entity.tower.RangeTower;
import entity.tower.SimpleTower;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.Main;

public class Shop extends VBox {

	private Game game;
	private Main main;

	public Shop() {
		setAlignment(Pos.CENTER);

		getChildren().add(new Label("Store"));

		ImageView simpleTowerImg = new ImageView(new Image(SimpleTower.IMAGE_URL));

		Button simpleTower = new Button(null, simpleTowerImg);
		simpleTower.setStyle("-fx-background-radius: 128em; ");

		simpleTower.setOnAction((ActionEvent e) -> {
			if (game.getMoney() >= 100) {
				game.buyTower(100);
				game.setSelectedTower(new SimpleTower(0, 0));
			}
		});

		getChildren().add(simpleTower);

		ImageView speedTowerImg = new ImageView(new Image(SpeedTower.IMAGE_URL));

		Button speedTower = new Button(null, speedTowerImg);
		speedTower.setStyle("-fx-background-radius: 128em; ");

		speedTower.setOnAction((ActionEvent e) -> {
			if (game.getMoney() >= 250) {
				game.buyTower(250);
				game.setSelectedTower(new SpeedTower(0, 0));
			}
		});

		getChildren().add(speedTower);

		ImageView rangeTowerImg = new ImageView(new Image(RangeTower.IMAGE_URL));

		Button rangeTower = new Button(null, rangeTowerImg);
		rangeTower.setStyle("-fx-background-radius: 64em; ");
		
		rangeTower.setOnAction((ActionEvent e) -> {
			if (game.getMoney() >= 250) {
				game.buyTower(250);
				game.setSelectedTower(new RangeTower(0, 0));
			}
		});
		
		getChildren().add(rangeTower);
		
		ImageView powerTowerImg = new ImageView(new Image(PowerTower.IMAGE_URL));
		
		Button powerTower = new Button(null, powerTowerImg);
		powerTower.setStyle("-fx-background-radius: 64em; ");
		
		powerTower.setOnAction((ActionEvent e) -> {
			if (game.getMoney() >= 250) {
				game.buyTower(250);
				game.setSelectedTower(new PowerTower(0, 0));
			}
		});
		
		getChildren().add(powerTower);

		Button quit = new Button("Quit");
		quit.setOnAction((ActionEvent e) -> {
			main.endGame();
		});

		getChildren().add(quit);

	}

	public void setGameListener(Game game) {
		this.game = game;
	}

	public void setMainListener(Main main) {
		this.main = main;
	}

}
