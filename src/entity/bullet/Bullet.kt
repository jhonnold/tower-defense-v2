package entity.bullet

import entity.Entity
import entity.enemy.Enemy
import gui.Game.Companion.TILE_SIZE
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.transform.Rotate

abstract class Bullet(x: Double, y: Double, internal var enemy: Enemy, internal var speed: Double, internal var damage: Int) : Entity(x, y) {
    internal var rotationAngle: Double = 0.0

    internal abstract var img: Image
    internal var flames: Image? = null

    init {

        val dx = enemy.x - x
        val dy = enemy.y - y

        val angle = Math.atan2(dy, dx)

        rotationAngle = Math.toDegrees(angle) + 90
    }

    override fun draw(gc: GraphicsContext) {

        val xi = x - TILE_SIZE / 2
        val yi = y - TILE_SIZE / 2

        val dy = TILE_SIZE.toDouble() * .40 + Math.sin(Math.toRadians(rotationAngle + 180))

        var r: Rotate

        if (flames != null) {
            r = Rotate(rotationAngle + 180, x, y)
            gc.save()
            gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)
            gc.drawImage(flames, xi, yi - dy, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
            gc.restore()
        }

        r = Rotate(rotationAngle, x, y)
        gc.save()
        gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)
        gc.drawImage(img, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        gc.restore()
    }

    open fun move() {

        if (!enemy.isDead) {
            val dx = enemy.x - x
            val dy = enemy.y - y

            val angle = Math.atan2(dy, dx)

            rotationAngle = Math.toDegrees(angle) + 90

            x += speed * Math.cos(angle)
            y += speed * Math.sin(angle)
        } else {
            val angle = Math.toRadians(rotationAngle - 90)

            x += speed * Math.cos(angle)
            y += speed * Math.sin(angle)
        }

    }

    fun doDamage() {
        enemy.takeDamage(damage)
    }

    fun collided(): Boolean {

        if (enemy.isDead) {
            return false
        }

        return distance(enemy) <= 5
    }

    fun offScreen(): Boolean {
        return x < -TILE_SIZE || x > TILE_SIZE * 17 || y < -TILE_SIZE || y > 17 * TILE_SIZE
    }

}
