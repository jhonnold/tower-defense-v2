package gui

import com.jfoenix.controls.JFXButton
import javafx.fxml.FXML

class MainMenuController {

    @FXML
    var startButton: JFXButton? = null

    @FXML
    var quitButton: JFXButton? = null

    fun initialize() {
        assert(startButton != null) { "fx:id=\"startButton\" was null check your FXML " }
        assert(quitButton != null) { "fx:id=\"quitButton\" was null check your FXML " }
    }

}
