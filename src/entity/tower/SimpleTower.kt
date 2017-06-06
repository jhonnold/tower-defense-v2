package entity.tower

import entity.bullet.Bullet
import entity.bullet.RegularBullet
import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.image.Image
import main.Main

class SimpleTower(x: Int, y: Int) : Tower(x, y) {

    init {
        baseImg = Image(BASE_IMAGE_URL)
        img = Image(IMAGE_URL)
        range = RANGE
        damage = 1
        shotDelay = 100
    }

    override fun fire(e: Enemy): Bullet {
        lastShotTime = Main.CURRENT_GAME_TICK
        lastEnemy = e

        val dy = TILE_SIZE.toDouble() * .3 * Math.sin(Math.toRadians(rotationAngle - 90))
        val dx = TILE_SIZE.toDouble() * .3 * Math.cos(Math.toRadians(rotationAngle - 90))

        return RegularBullet(x + dx, y + dy, e)
    }

    companion object {

        val PRICE = 10
        val RANGE = TILE_SIZE * 2
        val BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile180.png"
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile249.png"
    }


}
