package gui

import java.util.ArrayList
import java.util.Collections

import entity.bullet.Bullet
import entity.enemy.Enemy
import entity.tower.Tower
import javafx.event.ActionEvent
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import level.Level
import utils.InputHandler.collides
import utils.InputHandler.handleMouseClick

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
class Game(width: Int, height: Int) : Canvas(width.toDouble(), height.toDouble()) {
    private val GRID_WIDTH = 16
    private val GRID_HEIGHT = 10

    private var gridSet: Array<Image?> = arrayOfNulls(13)

    private val grid = arrayOf(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 9, 3, 3, 3, 3, 3, 10, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 4, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 5, 1, 1, 11, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 4, 9, 3, 3, 7, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0), intArrayOf(1, 1, 1, 11, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0), intArrayOf(3, 3, 3, 3, 7, 0, 0, 4, 12, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 8, 3, 3, 3, 3, 3, 3, 3, 3))

    private val route = arrayOf(charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'W', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('E', 'X', 'X', 'N', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'X'))

    private val towers: MutableList<Tower>
    private val enemies: MutableList<Enemy>
    private val bullets: MutableList<Bullet>

    private var mx: Int = 0
    private var my: Int = 0
    var money = 50
        private set
    private var levelNum = 1
    private var selectedTower: Tower? = null
    private var levelButton: Button? = null

    private var contained = true

    init {

        towers = Collections.synchronizedList(ArrayList<Tower>())
        enemies = Collections.synchronizedList(ArrayList<Enemy>())
        bullets = Collections.synchronizedList(ArrayList<Bullet>())

        init()
    }// TODO

    fun setLevelButton(levelButton: Button) {
        this.levelButton = levelButton
        this.levelButton!!.setOnAction { e: ActionEvent ->
            bullets.clear()
            Thread(Level(levelNum++, this)).start()
            this.levelButton!!.isVisible = false
        }
    }

    fun onLevelDone() {
        levelButton!!.isVisible = true
    }

    fun buyTower(cost: Int) {
        money -= cost
    }

    fun setSelectedTower(t: Tower) {
        selectedTower = t
    }

    fun addEnemy(simpleEnemy: Enemy) {
        enemies.add(simpleEnemy)
    }

    private fun init() {
        gridSet[0] = Image("file:img/PNG/Retina/towerDefense_tile162.png")
        gridSet[1] = Image("file:img/PNG/Retina/towerDefense_tile254.png")
        gridSet[2] = Image("file:img/PNG/Retina/towerDefense_tile230.png")
        gridSet[3] = Image("file:img/PNG/Retina/towerDefense_tile208.png")
        gridSet[4] = Image("file:img/PNG/Retina/towerDefense_tile232.png")
        gridSet[5] = Image("file:img/PNG/Retina/towerDefense_tile210.png")
        gridSet[6] = Image("file:img/PNG/Retina/towerDefense_tile211.png")
        gridSet[7] = Image("file:img/PNG/Retina/towerDefense_tile234.png")
        gridSet[8] = Image("file:img/PNG/Retina/towerDefense_tile233.png")
        gridSet[9] = Image("file:img/PNG/Retina/towerDefense_tile207.png")
        gridSet[10] = Image("file:img/PNG/Retina/towerDefense_tile209.png")
        gridSet[11] = Image("file:img/PNG/Retina/towerDefense_tile255.png")
        gridSet[12] = Image("file:img/PNG/Retina/towerDefense_tile253.png")

        setOnMouseMoved { e: MouseEvent ->
            mx = e.x.toInt()
            my = e.y.toInt()
        }

        setOnMouseExited { e: MouseEvent -> contained = false }

        setOnMouseEntered { e: MouseEvent -> contained = true }

        setOnMouseClicked { e: MouseEvent -> selectedTower = handleMouseClick(e, grid, towers, selectedTower, this) }

    }

    fun repaint() {
        // TODO
        val gc = graphicsContext2D

        gc.clearRect(0.0, 0.0, (TILE_SIZE * GRID_WIDTH).toDouble(), (TILE_SIZE * GRID_HEIGHT).toDouble())

        // DRAW BACKGROUND MAP
        // -------------------
        for (i in 0..GRID_WIDTH - 1) {
            for (j in 0..GRID_HEIGHT - 1) {
                gc.drawImage(gridSet!![grid[j][i]], (i * TILE_SIZE).toDouble(), (j * TILE_SIZE).toDouble(), TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
            }
        }
        // -------------------

        // DRAW TOWERS
        synchronized(towers) {
            for (t in towers) {
                t.draw(gc)
            }
        }

        // DRAW ENEMIES
        synchronized(enemies) {
            for (e in enemies) {
                e.draw(gc)
            }
        }

        // DRAW BULETS
        synchronized(bullets) {
            for (b in bullets) {
                b.draw(gc)
            }
        }

        // DRAW THE SELECTED TOWER
        if (selectedTower != null && contained) {
            if (grid[my / TILE_SIZE][mx / TILE_SIZE] == 0 && !collides(towers, mx, my)) {
                selectedTower!!.draw(gc, mx, my)

                val range = selectedTower!!.range
                gc.stroke = Color.RED
                gc.strokeOval((mx - range).toDouble(), (my - range).toDouble(), (range * 2).toDouble(), (range * 2).toDouble())
            } else {
                gc.fillOval(mx.toDouble(), my.toDouble(), 5.0, 5.0)
            }
        }

        gc.fillText("Money: " + money, 100.0, 100.0)

    }

    fun update() {
        synchronized(bullets) {
            val bIterator = bullets.iterator()

            while (bIterator.hasNext()) {
                var b: Bullet? = bIterator.next()

                b!!.move()

                if (b.collided()) {
                    b.doDamage()
                    bIterator.remove()
                    b = null
                } else if (b.offScreen()) {
                    bIterator.remove()
                    b = null
                }
            }
        }

        synchronized(enemies) {
            val eIterator = enemies.iterator()

            while (eIterator.hasNext()) {
                var e: Enemy? = eIterator.next()

                if (e!!.isDead) {
                    eIterator.remove()
                    money += e.reward
                    e = null
                    continue
                }

                e.move(route)
            }
        }

        for (t in towers) {

            var frontEnemy: Enemy? = null

            for (e in enemies) {
                if (e.distance(t) < t.range) {
                    if (frontEnemy == null) {
                        frontEnemy = e
                    } else if (e.distanceTraveled > frontEnemy.distanceTraveled) {
                        frontEnemy = e
                    }
                }
            }

            if (frontEnemy != null && t.canFire()) {
                bullets.add(t.fire(frontEnemy))
            }

            t.updateAngle()
        }
    }

    companion object {

        var TILE_SIZE = 64
    }
}
