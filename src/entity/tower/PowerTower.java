package entity.tower;

import static gui.Game.TILE_SIZE;

import javafx.scene.image.Image;

public class PowerTower extends Tower {

	public static final int RANGE = TILE_SIZE * 3;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile250.png";
	
	public PowerTower(int x, int y) {
		super(x, y);
		img = new Image(IMAGE_URL);
		range = RANGE;
		damage = 5;
		shotDelay = 100;
	}
	
}
