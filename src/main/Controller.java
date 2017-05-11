package main;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

/**
 * Created by zomby on 5/10/17 @ 5:55 PM.
 */
public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private JFXButton tower1;

    @FXML
    private JFXButton tower2;

    public Canvas getCanvas() { return canvas; }

    @FXML
    public void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was null check your FXML ";
    }
}
