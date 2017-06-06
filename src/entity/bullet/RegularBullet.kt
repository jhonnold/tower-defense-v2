package entity.bullet

import entity.enemy.Enemy
import javafx.scene.image.Image

class RegularBullet(x: Double, y: Double, enemy: Enemy) : Bullet(x, y, enemy, RegularBullet.SPEED, RegularBullet.DAMAGE) {
    override var img: Image = Image(IMAGE_URL)

    companion object {
        val DAMAGE = 1
        val SPEED = 4.0
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile272.png"
    }

}
