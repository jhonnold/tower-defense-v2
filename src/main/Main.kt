package main

import gui.Game
import gui.MainMenu
import gui.Shop
import javafx.animation.KeyFrame
import javafx.application.Application
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import javafx.animation.Timeline
import javafx.util.Duration

/**
 * The Main file for the whole program. Launches the JavaFX application and
 * starts the Game Loop.

 * @author Jay
 */
class Main : Application(), Runnable {

    private var primary: Stage? = null
    private var leftPane: AnchorPane? = null
    private var rightPane: AnchorPane? = null

    // Canvas for the game
    private var game: Game? = null
    private var shop: Shop? = null

    private var fxmlLoader: FXMLLoader? = null

    private var running = false

    // Game Loop Variables
    private val TICKS_PER_SECOND = 100
    private val SKIP_TICKS = 1000 / TICKS_PER_SECOND
    private val MAX_FRAMESKIP = 5

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        primary = primaryStage

        primaryStage.title = "tower-defense"

        // Load the scene from FXML
        fxmlLoader = FXMLLoader(javaClass.getResource("main.fxml"))
        val root = fxmlLoader!!.load<Parent>()
        val scene = Scene(root)
        primaryStage.scene = scene
        primaryStage.setOnCloseRequest { _ ->
            Platform.exit()
            System.exit(0)
        }

        // Move the game into the leftPane
        leftPane = (fxmlLoader!!.getController<Any>() as Controller).leftPane
        MainMenu(leftPane!!, this)

        // Set the frame rate to ~60 FPS
        val animationLoop = Timeline()
        animationLoop.cycleCount = Timeline.INDEFINITE


        val kf = KeyFrame(Duration.seconds(0.017), {_ : ActionEvent ->
            if (game != null) {
                game!!.repaint()
            }
        }, null)

//        val kf = KeyFrame(Duration.seconds(0.017), { _: ActionEvent ->
//            if (game != null) {
//                game!!.repaint()
//            }
//        })

        animationLoop.keyFrames.add(kf)

        primaryStage.show()

        // Start the Game Loop and the animation loops
        animationLoop.play()
    }

    override fun run() {
        running = true

        var next_game_tick = System.currentTimeMillis().toDouble()
        var loops: Int

        while (running) {
            loops = 0
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                // This loop is to update the game. Not to draw it.
                game!!.update()
                next_game_tick += SKIP_TICKS.toDouble()
                loops++
                CURRENT_GAME_TICK++
            }
        }
    }

    fun startGame() {

        game = Game(1024, 640)
        leftPane!!.children.clear()
        leftPane!!.children.add(game)

        val levelButton = Button("Next Level")
        levelButton.layoutX = 10.0
        levelButton.layoutY = 10.0

        leftPane!!.children.add(levelButton)
        game!!.setLevelButton(levelButton)

        rightPane = (fxmlLoader!!.getController<Any>() as Controller).rightPane
        shop = Shop(rightPane!!)
        shop!!.setGameListener(game!!)
        shop!!.setMainListener(this)
        //rightPane.getChildren().add(shop);

        primary!!.sizeToScene()

        Thread(this, "Game Loop").start()
    }

    fun endGame() {

        running = false

        leftPane!!.children.clear()
        rightPane!!.children.clear()

        MainMenu(leftPane!!, this)

        primary!!.sizeToScene()
    }

    companion object {
        var CURRENT_GAME_TICK: Long = 0

        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
