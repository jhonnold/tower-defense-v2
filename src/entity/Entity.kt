package entity

import javafx.scene.canvas.GraphicsContext

abstract class Entity(var x: Double, var y: Double) {

    fun distance(e: Entity): Double {
        val dx = e.x - x
        val dy = e.y - y

        return Math.sqrt(dx * dx + dy * dy)
    }

    fun distance(mx: Int, my: Int): Double {
        val dx = mx - x
        val dy = my - y

        return Math.sqrt(dx * dx + dy * dy)
    }

    abstract fun draw(gc: GraphicsContext)

}
