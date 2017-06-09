package gui

import entity.bullet.Bullet
import entity.enemy.Enemy
import entity.tower.Tower
import javafx.event.ActionEvent
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.text.Font
import level.Level
import utils.InputHandler.collides
import utils.InputHandler.handleMouseClick
import java.util.*

class Game(width: Int, height: Int) : Canvas(width.toDouble(), height.toDouble()) {
    private val GRID_WIDTH = 16
    private val GRID_HEIGHT = 10

    private var gridSet: Array<Image?> = arrayOfNulls(13)

    private val grid = arrayOf(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), intArrayOf(0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 9, 3, 3, 3, 3, 3, 10, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 4, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 5, 1, 1, 11, 2, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 4, 9, 3, 3, 7, 0, 0, 0, 0), intArrayOf(0, 0, 0, 4, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0), intArrayOf(1, 1, 1, 11, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0), intArrayOf(3, 3, 3, 3, 7, 0, 0, 4, 12, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 8, 3, 3, 3, 3, 3, 3, 3, 3))

    private val route = arrayOf(charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'W', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('E', 'X', 'X', 'N', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'), charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'X'))

    private val towers: MutableList<Tower> = Collections.synchronizedList(ArrayList<Tower>())
    private val enemies: MutableList<Enemy> = Collections.synchronizedList(ArrayList<Enemy>())
    private val bullets: MutableList<Bullet> = Collections.synchronizedList(ArrayList<Bullet>())

    private var mx = 0
    private var my = 0

    internal var money = 50
        get set

    private var selectedTower: Tower? = null
    private var levelButton: Button? = null

    private var contained = true

    init {
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

        setOnMouseExited { _: MouseEvent -> contained = false }

        setOnMouseEntered { _: MouseEvent -> contained = true }

        setOnMouseClicked { e: MouseEvent -> selectedTower = handleMouseClick(e, grid, towers, selectedTower, this) }
    }

    fun setLevelButton(levelButton: Button) {
        this.levelButton = levelButton
        this.levelButton?.setOnAction { _: ActionEvent ->
            bullets.clear()
            Thread(Level(this)).start()
            this.levelButton?.isVisible = false
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

    fun repaint() {
        val gc = graphicsContext2D

        gc.clearRect(0.0, 0.0, (TILE_SIZE * GRID_WIDTH).toDouble(), (TILE_SIZE * GRID_HEIGHT).toDouble())

        // DRAW BACKGROUND MAP
        // -------------------
        for (i in 0..GRID_WIDTH - 1) {
            for (j in 0..GRID_HEIGHT - 1) {
                gc.drawImage(gridSet[grid[j][i]], (i * TILE_SIZE).toDouble(), (j * TILE_SIZE).toDouble(), TILE_SIZE.toDouble(), TILE_SIZE.toDouble())
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

        gc.font = FONT
        gc.fillText("Money: " + money, 10.0, 100.0)
    }

    fun update() {
        synchronized(bullets) {
            val bIterator = bullets.iterator()

            while (bIterator.hasNext()) {
                val b: Bullet = bIterator.next()

                b.move()

                if (b.collided()) {
                    b.doDamage()
                    bIterator.remove()
                } else if (b.offScreen()) {
                    bIterator.remove()
                }
            }
        }

        synchronized(enemies) {
            val eIterator = enemies.iterator()

            while (eIterator.hasNext()) {
                val e: Enemy = eIterator.next()

                if (e.isDead) {
                    eIterator.remove()
                    money += e.reward
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

            if (frontEnemy != null && t.canFire) {
                bullets.add(t.fire(frontEnemy))
            }

            t.updateAngle()
        }
    }

    companion object {
        val FONT = Font("KenVector Future Regular", 22.0)
        val TILE_SIZE = 64
    }
}
