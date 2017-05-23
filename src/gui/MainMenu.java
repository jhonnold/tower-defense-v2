package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import main.Main;

public class MainMenu extends BorderPane {
	
	public MainMenu(Main m) {
		
		setPrefSize(1024, 640);
		
		Button startButton = new Button("Start");
		startButton.setOnAction((ActionEvent e) -> {
			m.startGame();
		});
		
		setCenter(startButton);
	}
	
}
