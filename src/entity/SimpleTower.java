package entity;

import static gui.Game.TILE_SIZE;

import javafx.scene.image.Image;

public class SimpleTower extends Tower {
	
	public static final int RANGE = TILE_SIZE * 2;
	
	public SimpleTower(int x, int y) {
		super(x, y);
		img = new Image("file:img/PNG/Retina/towerDefense_tile205.png");
		setRange(RANGE);
		setDamage(5);
		setShotDelay(1000);
	}
	
}
