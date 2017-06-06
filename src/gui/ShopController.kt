package gui

import com.jfoenix.controls.JFXButton
import javafx.fxml.FXML

class ShopController {

    @FXML
    var simpleTowerButton: JFXButton? = null

    @FXML
    var speedTowerButton: JFXButton? = null

    @FXML
    var rangeTowerButton: JFXButton? = null

    @FXML
    var powerTowerButton: JFXButton? = null

    fun initialize() {
        assert(simpleTowerButton != null) { "fx:id=\"simpleTowerButton\" was null check your FXML " }
        assert(speedTowerButton != null) { "fx:id=\"speedTowerButton\" was null check your FXML " }
        assert(rangeTowerButton != null) { "fx:id=\"rangeTowerButton\" was null check your FXML " }
        assert(powerTowerButton != null) { "fx:id=\"powerTowerButton\" was null check your FXML " }
    }
}
