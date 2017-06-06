package entity.tower

import entity.Entity
import entity.bullet.Bullet
import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.transform.Rotate
import main.Main

abstract class Tower(x: Int, y: Int) : Entity(x.toDouble(), y.toDouble()) {

    internal var range: Int = 0
    internal var damage: Int = 0
    internal var shotDelay: Int = 0
    internal var lastShotTime: Long = 0
    internal var rotationAngle = 0.0

    internal var lastEnemy: Enemy? = null
    internal abstract var img: Image
    internal abstract var baseImg: Image
    internal var blankImg: Image? = null

    val canFire: Boolean
        get() = (Main.CURRENT_GAME_TICK - lastShotTime).toInt() >= shotDelay

    init {
        lastShotTime = Main.CURRENT_GAME_TICK - 100
    }

    override fun draw(gc: GraphicsContext) {

        val xi = x - TILE_SIZE / 2
        val yi = y - TILE_SIZE / 2

        gc.drawImage(baseImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())

        val r = Rotate(rotationAngle, x, y)
        gc.save()
        gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)

        if (!canFire && blankImg != null) {
            gc.drawImage(blankImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        } else {
            gc.drawImage(img, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        }

        gc.restore()

    }

    fun draw(gc: GraphicsContext, x: Int, y: Int) {
        val xi = (x - TILE_SIZE / 2).toDouble()
        val yi = (y - TILE_SIZE / 2).toDouble()

        gc.drawImage(baseImg, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        gc.drawImage(img, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
    }

    abstract fun fire(e: Enemy): Bullet

    fun updateAngle() {
        if (lastEnemy != null) {
            val dx = lastEnemy!!.x - x
            val dy = lastEnemy!!.y - y

            rotationAngle = Math.toDegrees(Math.atan2(dx, -dy))
        }
    }
}
