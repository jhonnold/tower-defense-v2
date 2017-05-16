package level;

import static gui.Game.TILE_SIZE;

import entity.SimpleEnemy;
import gui.Game;

public class Level implements Runnable {
	
	private final Game game;
	
	public Level(Game game) {
		this.game = game; 
	}
	
	@Override
	public void run() {
		int count = 10;
		
		while (count > 0) {
			game.addEnemy(new SimpleEnemy(-1 * TILE_SIZE, 8 * TILE_SIZE));
			count--;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
		}
	}

}
