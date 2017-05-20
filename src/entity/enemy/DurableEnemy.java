package entity.enemy;

import javafx.scene.image.Image;

public class DurableEnemy extends Enemy {
	
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile247.png";
	
	public DurableEnemy(int x, int y) {
		super(x, y, 25, .5);
		img = new Image(IMAGE_URL);
		setDir('E');
	}
	
}
