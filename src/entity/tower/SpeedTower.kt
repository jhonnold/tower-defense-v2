package entity.tower

import entity.bullet.Bullet
import entity.bullet.DoubleBullet
import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.image.Image
import main.Main

class SpeedTower(x: Int, y: Int) : Tower(x, y) {
    override var img: Image = Image(IMAGE_URL)
    override var baseImg: Image = Image(BASE_IMAGE_URL)

    init {
        range = RANGE
        damage = 5
        shotDelay = 20
    }

    override fun fire(e: Enemy): Bullet {
        lastShotTime = Main.CURRENT_GAME_TICK
        lastEnemy = e

        val dy = TILE_SIZE.toDouble() * .3 * Math.sin(Math.toRadians(rotationAngle - 90))
        val dx = TILE_SIZE.toDouble() * .3 * Math.cos(Math.toRadians(rotationAngle - 90))

        return DoubleBullet(x + dx, y + dy, e)
    }

    companion object {

        val PRICE = 25
        val RANGE = TILE_SIZE * 3
        val BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile182.png"
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile250.png"
    }
}
