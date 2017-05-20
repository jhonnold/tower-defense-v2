package level;

import static gui.Game.TILE_SIZE;

import entity.enemy.SimpleEnemy;
import gui.Game;
import main.Main;

public class Level implements Runnable {
	
	private final Game game;
	
	private int count;
	
	public Level(int level, Game game) {
		this.game = game;
		
		count = level * 3 + 2;
	}
	
	@Override
	public void run() {
		
		while (count > 0) {
			game.addEnemy(new SimpleEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
			count--;
			
			long endTick = Main.CURRENT_GAME_TICK + 100;
			try {
				while (endTick > Main.CURRENT_GAME_TICK) {
					Thread.yield();
				}
			} catch (Exception e) {}
		}
		
		game.onLevelDone();
	}

}
