package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private JFXButton startButton;

    @FXML
    private JFXButton quitButton;

    public JFXButton getStartButton() {
        return startButton;
    }

    public JFXButton getQuitButton() {
        return quitButton;
    }

    @FXML
    public void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was null check your FXML ";
        assert quitButton != null : "fx:id=\"quitButton\" was null check your FXML ";
    }

}
