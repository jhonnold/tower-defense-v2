package main;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Created by zomby on 5/10/17 @ 5:55 PM.
 */
public class Controller {
	
	@FXML
	private AnchorPane leftPane;
	
	@FXML
	private AnchorPane rightPane;

    public AnchorPane getLeftPane() {
    	return leftPane;
    }
    
    public AnchorPane getRightPane() {
    	return rightPane;
    }

    @FXML
    public void initialize() {
        assert leftPane != null : "fx:id=\"leftPane\" was null check your FXML ";
        assert rightPane != null : "fx:id=\"rightPane\" was null check your FXML ";
    }
}
