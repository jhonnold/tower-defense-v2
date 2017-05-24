package utils;

import static gui.Game.TILE_SIZE;

import java.util.List;

import entity.Entity;
import entity.tower.SpeedTower;
import entity.tower.PowerTower;
import entity.tower.RangeTower;
import entity.tower.SimpleTower;
import entity.tower.Tower;
import gui.Game;
import javafx.scene.input.MouseEvent;

public class InputHandler {

	public static Tower handleMouseClick(MouseEvent e, int[][] grid, List<Tower> towers, Tower selectedTower, Game g) {
		if (selectedTower == null) {
			return null;
		}
		
		int mx = (int) e.getX();
		int my = (int) e.getY();
		
		if (grid[my / TILE_SIZE][mx / TILE_SIZE] == 0 && !collides(towers, mx, my)) {
			if (selectedTower instanceof SimpleTower) {
				g.buyTower(SimpleTower.PRICE);
				towers.add(new SimpleTower(mx, my));
				return null;
			} else if (selectedTower instanceof SpeedTower) {
				g.buyTower(SpeedTower.PRICE);
				towers.add(new SpeedTower(mx, my));
				return null;
			} else if (selectedTower instanceof RangeTower) {
				g.buyTower(RangeTower.PRICE);
				towers.add(new RangeTower(mx, my));
				return null;
			} else if (selectedTower instanceof PowerTower) {
				g.buyTower(PowerTower.PRICE);
				towers.add(new PowerTower(mx, my));
				return null;
			}
		}
		
		return selectedTower;
	}
	
	public static boolean collides(Iterable<Tower> list, int mx, int my) {
		
		for (Entity entity : list) {
			if (entity.distance(mx, my) < (double) TILE_SIZE * .6) {
				return true;
			}
		}
		
		return false;	
	}
}
