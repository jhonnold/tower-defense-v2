package gui;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class MainMenu {
	
	private FXMLLoader fxmlLoader;
	
	public MainMenu(AnchorPane pane, Main m) {
		
		try {
			fxmlLoader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
			Parent root = fxmlLoader.load();
			pane.getChildren().add(root);
			
			MainMenuController controller = fxmlLoader.getController();
			
			JFXButton startButton = controller.getStartButton();
			JFXButton quitButton = controller.getQuitButton();
			
			startButton.setOnAction((ActionEvent e) -> {
				m.startGame();
			});
			
			quitButton.setOnAction((ActionEvent e) -> {
				Platform.exit();
				System.exit(0);
			});
			
			
		} catch (Exception e) {
			
		}
	}
	
}
