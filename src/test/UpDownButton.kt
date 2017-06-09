package test

import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.input.MouseEvent

class UpDownButton(text: String, upImage: String, downImage: String) : Button(text) {

    init {
        contentDisplay = ContentDisplay.CENTER

        style = "-fx-border-color: transparent; " +
                "-fx-border-width: 0; " +
                "-fx-background-radius: 0; " +
                "-fx-background-color: transparent; " +
                "-fx-graphic: url(" + upImage + "); "

        setOnMousePressed { _: MouseEvent ->
            style = "-fx-border-color: transparent; " +
                    "-fx-border-width: 0; " +
                    "-fx-background-radius: 0; " +
                    "-fx-background-color: transparent; " +
                    "-fx-graphic: url(" + downImage + "); "
        }

        setOnMouseReleased { _: MouseEvent ->
            style = "-fx-border-color: transparent; " +
                    "-fx-border-width: 0; " +
                    "-fx-background-radius: 0; " +
                    "-fx-background-color: transparent; " +
                    "-fx-graphic: url(" + upImage + "); "
        }
    }

}
