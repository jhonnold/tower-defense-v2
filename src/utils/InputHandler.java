package utils;

import static gui.Game.TILE_SIZE;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.SimpleTower;
import entity.Tower;
import javafx.scene.input.MouseEvent;

public class InputHandler {

	public static Tower handleMouseClick(MouseEvent e, int[][] grid, List<Tower> towers, Tower selectedTower) {
		if (selectedTower == null) {
			return null;
		}
		
		int mx = (int) e.getX();
		int my = (int) e.getY();
		
		if (grid[my / TILE_SIZE][mx / TILE_SIZE] == 0 && !collides(towers, mx, my)) {
			towers.add(new SimpleTower(mx, my));
			return null;
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
