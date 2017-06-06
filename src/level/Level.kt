package level

import entity.enemy.BossEnemy
import entity.enemy.DurableEnemy
import entity.enemy.SimpleEnemy
import entity.enemy.SpeedEnemy
import gui.Game
import gui.Game.Companion.TILE_SIZE
import main.Main
import org.ejml.data.*
import org.ejml.dense.fixed.*

class Level(private val level: Int, private val game: Game) : Runnable {

    // This is the transition matrix of the markov generated levels - as n increases, the matrix tends to an even
    // distribution of the 3 advanced enemies
    private val transitions = DMatrix4x4(.85, 0.0, 0.0, 0.0,
            .075, .45, .45, .1,
            .075, .45, .45, .1,
            0.0, .1, .1, .8)

    // Arbitrary difficulties assigned for each enemy type - should be tweaked for optimal level fun.
    private val e1_strength = 1.0
    private val e2_strength = 1.25
    private val e3_strength = 1.25
    private val e4_strength = 1.5

    fun updateEnemyDensityMatrix() {

        if (enemyDensity != null) {
            val result = DMatrix4()
            CommonOps_DDF4.mult(transitions, enemyDensity!!, result)
            enemyDensity = result
        } else {
            enemyDensity = DMatrix4(1.0, 0.0, 0.0, 0.0)
        }

    }

    override fun run() {
        // Arbitrary variables corresponding to the exponential level growth.
        val a = 1.0
        val b = 1.5
        val k = -1.0

        val sx = -1 * TILE_SIZE
        val sy = 8 * TILE_SIZE

        var count = a * Math.pow(b, level.toDouble() + k)

        updateEnemyDensityMatrix()

        while (count >= 0) {
            val temp = Math.random()

            if (temp < enemyDensity!!.a1) {
                game.addEnemy(SimpleEnemy(sx, sy))
                count -= e1_strength
            } else if (temp < enemyDensity!!.a1 + enemyDensity!!.a2) {
                game.addEnemy(SpeedEnemy(sx, sy))
                count -= e2_strength
            } else if (temp < enemyDensity!!.a1 + enemyDensity!!.a2 + enemyDensity!!.a3) {
                game.addEnemy(DurableEnemy(sx, sy))
                count -= e3_strength
            } else {
                game.addEnemy(BossEnemy(sx, sy))
                count -= e4_strength
            }

            val endTick = Main.CURRENT_GAME_TICK + 100
            try {
                while (endTick > Main.CURRENT_GAME_TICK) {
                    Thread.yield()
                }
            } catch (e: Exception) {
            }

        }

        game.onLevelDone()
    }

    companion object {

        private var enemyDensity: DMatrix4? = null
    }

}
