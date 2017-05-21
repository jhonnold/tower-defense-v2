package gui;

import com.jfoenix.controls.JFXButton;
import entity.tower.SpeedTower;
import entity.tower.PowerTower;
import entity.tower.RangeTower;
import entity.tower.SimpleTower;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Main;

import java.io.IOException;

public class Shop {

	private Game game;
	private Main main;

	private FXMLLoader fxmlLoader;

	public Shop(AnchorPane pane) {

	    try {
            fxmlLoader = new FXMLLoader(getClass().getResource("shop.fxml"));
            Parent root = fxmlLoader.load();
            pane.getChildren().add(root);

            ShopController controller = fxmlLoader.getController();

            JFXButton simpleTower = controller.getSimpleTowerButton();
            JFXButton speedTower = controller.getSpeedTowerButton();
            JFXButton rangeTower = controller.getRangeTowerButton();
            JFXButton powerTower = controller.getPowerTowerButton();

            simpleTower.setOnAction((ActionEvent e) -> {
                if (game.getMoney() >= SimpleTower.PRICE) {
                    game.setSelectedTower(new SimpleTower(0, 0));
                }
            });

            speedTower.setOnAction((ActionEvent e) -> {
                if (game.getMoney() >= SpeedTower.PRICE) {
                    game.setSelectedTower(new SpeedTower(0, 0));
                }
            });

            rangeTower.setOnAction((ActionEvent e) -> {
                if (game.getMoney() >= RangeTower.PRICE) {
                    game.setSelectedTower(new RangeTower(0, 0));
                }
            });

            powerTower.setOnAction((ActionEvent e) -> {
                if (game.getMoney() >= PowerTower.PRICE) {
                    game.setSelectedTower(new PowerTower(0, 0));
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void setGameListener(Game game) {
		this.game = game;
	}

	public void setMainListener(Main main) {
		this.main = main;
	}

}
