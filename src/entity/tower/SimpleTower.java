package entity.tower;

import static gui.Game.TILE_SIZE;

import javafx.scene.image.Image;

public class SimpleTower extends Tower {
	
	public static final int RANGE = TILE_SIZE * 2;
	public static final String BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile180.png";
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile249.png";
	
	public SimpleTower(int x, int y) {
		super(x, y);
		baseImg = new Image(BASE_IMAGE_URL);
		img = new Image(IMAGE_URL);
		range = RANGE;
		damage = 1;
		shotDelay = 100;
	}
	
}
