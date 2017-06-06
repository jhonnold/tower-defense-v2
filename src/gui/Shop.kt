package gui

import com.jfoenix.controls.JFXButton
import entity.tower.SpeedTower
import entity.tower.PowerTower
import entity.tower.RangeTower
import entity.tower.SimpleTower
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import main.Main

import java.io.IOException

class Shop(pane: AnchorPane) {

    private var game: Game? = null
    private var main: Main? = null

    private var fxmlLoader: FXMLLoader? = null

    init {

        try {
            fxmlLoader = FXMLLoader(javaClass.getResource("shop.fxml"))
            val root = fxmlLoader!!.load<Parent>()
            pane.children.add(root)

            val controller = fxmlLoader!!.getController<ShopController>()

            val simpleTower = controller.simpleTowerButton
            val speedTower = controller.speedTowerButton
            val rangeTower = controller.rangeTowerButton
            val powerTower = controller.powerTowerButton

            simpleTower!!.setOnAction { e: ActionEvent ->
                if (game!!.money >= SimpleTower.PRICE) {
                    game!!.setSelectedTower(SimpleTower(0, 0))
                }
            }

            speedTower!!.setOnAction { e: ActionEvent ->
                if (game!!.money >= SpeedTower.PRICE) {
                    game!!.setSelectedTower(SpeedTower(0, 0))
                }
            }

            rangeTower!!.setOnAction { e: ActionEvent ->
                if (game!!.money >= RangeTower.PRICE) {
                    game!!.setSelectedTower(RangeTower(0, 0))
                }
            }

            powerTower!!.setOnAction { e: ActionEvent ->
                if (game!!.money >= PowerTower.PRICE) {
                    game!!.setSelectedTower(PowerTower(0, 0))
                }
            }


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun setGameListener(game: Game) {
        this.game = game
    }

    fun setMainListener(main: Main) {
        this.main = main
    }

}
