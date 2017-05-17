package gui;

import entity.SimpleTower;
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
		simpleTower.setStyle(
			"-fx-background-radius: 128em; "
		);
		
		simpleTower.setOnAction((ActionEvent e) -> {
			if (game.getMoney() >= 100) {
				game.buyTower(100);
				game.setSelectedTower(new SimpleTower(0, 0));
			}
		});
		
		getChildren().add(simpleTower);
		
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
