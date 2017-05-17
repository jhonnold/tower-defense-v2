package level;

import static gui.Game.TILE_SIZE;

import entity.SimpleEnemy;
import gui.Game;

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
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
		
		game.onLevelDone();
	}

}
