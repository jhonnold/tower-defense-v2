package entity.enemy

import entity.Entity
import gui.Game.Companion.TILE_SIZE
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.transform.Rotate

abstract class Enemy(x: Double, y: Double, internal var health: Int, internal var speed: Double) : Entity(x, y) {
    internal var reward = 1
    internal var distanceTraveled = 0
    internal var dir = ' '
    internal var rotationAngle = 0.0

    internal abstract var img: Image

    fun setDir(dir: Char) {
        this.dir = dir
    }

    fun takeDamage(damage: Int) {
        health -= damage
    }

    val isDead: Boolean
        get() = health <= 0

    fun move(grid: Array<CharArray>) {
        var gx: Int
        var gy: Int

        when (dir) {
            'N' -> {
                gx = ((x - TILE_SIZE / 2) / TILE_SIZE).toInt()
                gy = (y / TILE_SIZE).toInt()
            }
            'S' -> {
                gx = ((x - TILE_SIZE / 2) / TILE_SIZE).toInt()
                gy = (y / TILE_SIZE).toInt() - 1
            }
            'E' -> {
                gx = (x / TILE_SIZE).toInt() - 1
                gy = ((y - TILE_SIZE / 2) / TILE_SIZE).toInt()
            }
            'W' -> {
                gx = (x / TILE_SIZE).toInt()
                gy = ((y - TILE_SIZE / 2) / TILE_SIZE).toInt()
            }
            else -> {
                gx = ((x - TILE_SIZE / 2) / TILE_SIZE).toInt()
                gy = ((y - TILE_SIZE / 2) / TILE_SIZE).toInt()
            }
        }

        if (gx < 0) {
            gx = 0
        }
        if (gy < 0) {
            gy = 0
        }

        val newDir = grid[gy][gx]

        if (newDir != 'X') {
            dir = newDir
        }

        when (dir) {
            'N' -> {
                y -= speed
                rotationAngle = -90.0
            }
            'S' -> {
                y += speed
                rotationAngle = 90.0
            }
            'E' -> {
                x += speed
                rotationAngle = 0.0
            }
            'W' -> {
                x -= speed
                rotationAngle = 180.0
            }
        }

        distanceTraveled += speed.toInt()
    }

    override fun draw(gc: GraphicsContext) {

        val xi = x - TILE_SIZE / 2
        val yi = y - TILE_SIZE / 2

        val r = Rotate(rotationAngle, x, y)
        gc.save()
        gc.setTransform(r.mxx, r.myx, r.mxy, r.myy, r.tx, r.ty)
        gc.drawImage(img, xi, yi, TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
        gc.restore()
    }

}
