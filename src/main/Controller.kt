package main

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.AnchorPane
import java.util.*
import javax.print.DocFlavor

/**
 * Created by zomby on 5/10/17 @ 5:55 PM.
 */
class Controller {

    @FXML
    var leftPane: AnchorPane? = null

    @FXML
    var rightPane: AnchorPane? = null


    fun initialize() {
        assert(leftPane != null) { "fx:id=\"leftPane\" was null check your FXML " }
        assert(rightPane != null) { "fx:id=\"rightPane\" was null check your FXML " }
    }
}
