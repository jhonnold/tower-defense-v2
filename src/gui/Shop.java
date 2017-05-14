package gui;

import entity.SimpleTower;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Shop extends VBox {
	
	private Game game;
	
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
		
	}
	
	public void setListener(Game game) {
		this.game = game;
	}
	
	
}
