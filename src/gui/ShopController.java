package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class ShopController {

    @FXML
    private JFXButton simpleTowerButton;

    @FXML
    private JFXButton speedTowerButton;

    @FXML
    private JFXButton rangeTowerButton;

    @FXML
    private JFXButton powerTowerButton;

    public JFXButton getSimpleTowerButton() {
        return simpleTowerButton;
    }

    public JFXButton getSpeedTowerButton() {
        return speedTowerButton;
    }

    public JFXButton getRangeTowerButton() {
        return rangeTowerButton;
    }

    public JFXButton getPowerTowerButton() {
        return powerTowerButton;
    }

    @FXML
    public void initialize() {
        assert simpleTowerButton != null : "fx:id=\"simpleTowerButton\" was null check your FXML ";
        assert speedTowerButton != null : "fx:id=\"speedTowerButton\" was null check your FXML ";
        assert rangeTowerButton != null : "fx:id=\"rangeTowerButton\" was null check your FXML ";
        assert powerTowerButton != null : "fx:id=\"powerTowerButton\" was null check your FXML ";
    }
}
