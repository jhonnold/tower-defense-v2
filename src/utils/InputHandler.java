package utils;

import static gui.Game.TILE_SIZE;

import java.util.ArrayList;

import entity.Entity;
import entity.SimpleTower;
import entity.Tower;
import javafx.scene.input.MouseEvent;

public class InputHandler {

	public static void handleMouseClick(MouseEvent e, int[][] grid, ArrayList<Tower> towers, Tower selectedTower) {
		int mx = (int) e.getX();
		int my = (int) e.getY();
		
		if (grid[my / TILE_SIZE][mx / TILE_SIZE] == 0 && !collides(towers, mx, my)) {
			towers.add(new SimpleTower(mx, my));
			//selectedTower = null;
		}
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
