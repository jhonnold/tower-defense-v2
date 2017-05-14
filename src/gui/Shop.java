package gui;

import entity.SimpleTower;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.Main;

public class Shop extends VBox {
	
	private Game game;
	private Main main;
	
	public Shop() {
		setAlignment(Pos.CENTER);
		
		getChildren().add(new Label("Store"));
		
		Button simpleTower = new Button("Simple Tower");
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
