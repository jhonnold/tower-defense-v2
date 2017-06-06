package entity.bullet

import entity.enemy.Enemy
import javafx.scene.image.Image

class MissileBullet(x: Double, y: Double, enemy: Enemy) : Bullet(x, y, enemy, MissileBullet.SPEED, MissileBullet.DAMAGE) {
    override var img: Image = Image(IMAGE_URL)

    init {
        flames = Image(FLAMES_IMAGE_URL)
    }

    companion object {

        val DAMAGE = 3
        val SPEED = 4.0
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile251.png"
        val FLAMES_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile295.png"
    }


}	
