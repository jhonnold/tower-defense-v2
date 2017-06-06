package entity.bullet

import entity.enemy.Enemy
import javafx.scene.image.Image

class RangeBullet(x: Double, y: Double, enemy: Enemy) : Bullet(x, y, enemy, RangeBullet.SPEED, RangeBullet.DAMAGE) {
    override var img: Image = Image(IMAGE_URL)

    init {
        flames = Image(FLAMES_IMAGE_URL)
    }

    override fun move() {

        super.move()

        speed += .05

        if (speed > 9.8) {
            speed = 9.8
        }
    }

    companion object {

        val DAMAGE = 1
        val SPEED = 1.0
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile252.png"
        val FLAMES_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile295.png"
    }

}
