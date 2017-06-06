package entity.bullet

import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.transform.Rotate

class DoubleBullet(x: Double, y: Double, enemy: Enemy) : Bullet(x, y, enemy, DoubleBullet.SPEED, DoubleBullet.DAMAGE) {

    init {

        img = Image(IMAGE_URL)
    }

    override fun draw(gc: GraphicsContext) {

        val xi = x - TILE_SIZE / 2
        val yi = y - TILE_SIZE / 2

        val r: Rotate = Rotate(rotationAngle, x, y)

        gc.save()
        gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)
        gc.drawImage(img, xi - TILE_SIZE.toDouble() * .1, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        gc.drawImage(img, xi + TILE_SIZE.toDouble() * .1, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        gc.restore()
    }

    companion object {

        val DAMAGE = 1
        val SPEED = 4.0
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile273.png"
    }

}
