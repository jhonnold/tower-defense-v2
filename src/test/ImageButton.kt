package test

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent

class ImageButton(upImage: String, downImage: String) : ImageView() {

    init {
        val up = Image(upImage, 128.0, 128.0, true, true)
        val down = Image(downImage, 128.0, 128.0, true, true)

        image = up

        setOnMousePressed { e: MouseEvent -> image = down }

        setOnMouseReleased { e: MouseEvent -> image = up }
    }
}
