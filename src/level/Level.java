package level;

import static gui.Game.TILE_SIZE;

import entity.enemy.BossEnemy;
import entity.enemy.DurableEnemy;
import entity.enemy.SimpleEnemy;
import entity.enemy.SpeedEnemy;
import gui.Game;
import main.Main;
import org.ejml.data.*;
import org.ejml.dense.fixed.*;

public class Level implements Runnable {
	
	private final Game game;
	
	private int level;

	// This is the transition matrix of the markov generated levels - as n increases, the matrix tends to an even
	// distribution of the 3 advanced enemies
	private DMatrix4x4 transitions = new DMatrix4x4(.85, 0, 0, 0,
													.075, .45, .45, .1,
													.075, .45, .45, .1,
													0, .1, .1, .8);
	
	private static DMatrix4 enemyDensity;

	// Arbitrary difficulties assigned for each enemy type - should be tweaked for optimal level fun.
	private double e1_strength = 1;
	private double e2_strength = 1.25;
	private double e3_strength = 1.25;
	private double e4_strength = 1.5;
	
	public Level(int level, Game game) {
		this.game = game;
		this.level = level;
	}

	public void updateEnemyDensityMatrix() {
		
		if (enemyDensity != null) {
			DMatrix4 result = new DMatrix4();
			CommonOps_DDF4.mult(transitions, enemyDensity, result);
			enemyDensity = result;	
		} else {
			enemyDensity = new DMatrix4(1, 0, 0, 0);
		}
		
	}

	public void run() {
		// Arbitrary variables corresponding to the exponential level growth.
		double a = 1;
		double b = 1.5;
		double k = -1;

		int sx = -1 * TILE_SIZE;
		int sy = 8 * TILE_SIZE;
		
		double count = a * Math.pow(b, (double)level + k);

		updateEnemyDensityMatrix();

		while(count >= 0) {
			double temp = Math.random();
			
			if (temp < enemyDensity.a1) {
				game.addEnemy(new SimpleEnemy(sx, sy));
				count -= e1_strength;
			} else if (temp < enemyDensity.a1 + enemyDensity.a2) {
				game.addEnemy(new SpeedEnemy(sx, sy));
				count -= e2_strength;
			} else if (temp < enemyDensity.a1 + enemyDensity.a2 + enemyDensity.a3) {
				game.addEnemy(new DurableEnemy(sx, sy));
				count -= e3_strength;
			} else {
				game.addEnemy(new BossEnemy(sx, sy));
				count -= e4_strength;
			}

			long endTick = Main.CURRENT_GAME_TICK + 100;
			try {
				while (endTick > Main.CURRENT_GAME_TICK) {
					Thread.yield();
				}
			} catch (Exception e) {
			}
		}

		game.onLevelDone();
	}

}
