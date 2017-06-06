package gui

import com.jfoenix.controls.JFXButton

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import main.Main

class MainMenu(pane: AnchorPane, m: Main) {

    private var fxmlLoader: FXMLLoader? = null

    init {

        try {
            fxmlLoader = FXMLLoader(javaClass.getResource("mainmenu.fxml"))
            val root = fxmlLoader!!.load<Parent>()
            pane.children.add(root)

            val controller = fxmlLoader!!.getController<MainMenuController>()

            val startButton = controller.startButton
            val quitButton = controller.quitButton

            if (startButton != null && quitButton != null) {

                startButton.setOnAction { e: ActionEvent -> m.startGame() }

                quitButton.setOnAction { e: ActionEvent ->
                    Platform.exit()
                    System.exit(0)
                }
            }

        } catch (e: Exception) {

        }

    }

}
