package test

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class UpDownButtonTest : Application() {

    override fun start(primary: Stage) {

        val button = UpDownButton("Hi", "file:src/test/rsc/up.png", "file:src/test/rsc/down.png")

        val bp = BorderPane()
        bp.center = button

        val scene = Scene(bp)

        primary.scene = scene
        primary.show()
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            launch(UpDownButtonTest::class.java)
        }
    }

}
