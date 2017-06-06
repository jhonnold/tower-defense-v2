package entity.tower

import entity.bullet.Bullet
import entity.bullet.MissileBullet
import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.transform.Rotate
import main.Main

class PowerTower(x: Int, y: Int) : Tower(x, y) {
    override var img: Image = Image(IMAGE_URL)
    override var baseImg: Image = Image(BASE_IMAGE_URL)

    private val halfImg: Image

    private var bulletCount = 2

    init {
        halfImg = Image(HALF_IMAGE_URL)
        blankImg = Image(BLANK_IMAGE_URL)

        range = RANGE
        damage = 5
        shotDelay = 100
    }

    override fun fire(e: Enemy): Bullet {
        lastShotTime = Main.CURRENT_GAME_TICK
        lastEnemy = e

        val dy = TILE_SIZE.toDouble() * .2 * Math.sin(Math.toRadians(rotationAngle))
        val dx = TILE_SIZE.toDouble() * .2 * Math.cos(Math.toRadians(rotationAngle))

        if (bulletCount == 2) {
            bulletCount--
            return MissileBullet(x + dx, y + dy, e)
        } else {
            bulletCount++
            return MissileBullet(x - dx, y - dy, e)
        }
    }

    override fun draw(gc: GraphicsContext) {

        val xi = x - TILE_SIZE / 2
        val yi = y - TILE_SIZE / 2

        gc.drawImage(baseImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())

        val r = Rotate(rotationAngle, x, y)
        gc.save()
        gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)

        if (!canFire && bulletCount == 2) {
            gc.drawImage(blankImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        } else if (!canFire && bulletCount == 1) {
            gc.drawImage(halfImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        } else {
            gc.drawImage(img, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        }

        gc.restore()

    }

    companion object {

        val PRICE = 25
        val RANGE = TILE_SIZE * 3
        val BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile181.png"
        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile205.png"
        val HALF_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile205_5.png"
        val BLANK_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile228.png"
    }

}
