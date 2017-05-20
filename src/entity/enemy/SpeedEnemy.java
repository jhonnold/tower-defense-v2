package entity.enemy;

import javafx.scene.image.Image;

public class SpeedEnemy extends Enemy {
	
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile248.png";
	
	public SpeedEnemy(int x, int y) {
		super(x, y, 5, 3);
		img = new Image(IMAGE_URL);
		setDir('E');
	}
	
}
