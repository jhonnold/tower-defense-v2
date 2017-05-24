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
import java.util.Random;

public class Level implements Runnable {
	
	private final Game game;
	
	private int level;

	// This is the transition matrix of the markov generated levels - as n increases, the matrix tends to an even
	// distribution of the 3 advanced enemies
	private DMatrix4x4 transitions = new DMatrix4x4(.85, 0, 0, 0,
													.075, .45, .45, .1,
													.075, .45, .45, .1,
													0, .1, .1, .8);

	// Arbitrary difficulties assigned for each enemy type - should be tweaked for optimal level fun.
	private double e1_strength = 1;
	private double e2_strength = 1.25;
	private double e3_strength = 1.25;
	private double e4_strength = 1.5;
	
	public Level(int level, Game game) {
		this.game = game;
		this.level = level;
	}

	public double[] enemyTypes() {

		DMatrix4 start = new DMatrix4(1,0,0,0);
		CommonOps_DDF4 op = new CommonOps_DDF4();

		for(int i = 0; i < level; i++) {
			DMatrix4 start_temp = new DMatrix4();
			op.mult(transitions, start, start_temp);
			start = start_temp;
		}

		double array[] = {start.a1, start.a2, start.a3, start.a4};
		return array;
	}

	public void run() {
		// Arbitrary variables corresponding to the exponential level growth.
		double a = 1;
		double b = 1.5;
		double k = -1;

		double count = a * Math.pow(b, (double)level + k);
		Random rand = new Random();

		double[] probs = enemyTypes();
		System.out.println(probs[0] + " " + probs[1] + " " + probs[2] + " " + probs[3]);

		while(count >= 0) {
			double temp = rand.nextDouble();
			temp = temp - Math.floor(temp);
			if (temp < probs[0]) {
				game.addEnemy(new SimpleEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
				count -= e1_strength;
			} else if (temp < probs[0] + probs[1]) {
				game.addEnemy(new SpeedEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
				count -= e2_strength;
			} else if (temp < probs[0] + probs[1] + probs[2]) {
				game.addEnemy(new DurableEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
				count -= e3_strength;
			} else {
				game.addEnemy(new BossEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
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
