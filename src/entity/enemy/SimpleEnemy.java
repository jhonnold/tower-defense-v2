package entity.enemy;

import javafx.scene.image.Image;

public class SimpleEnemy extends Enemy {
	
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile245.png";
	
	public SimpleEnemy(int x, int y) {
		super(x, y, 5, 1);
		img = new Image(IMAGE_URL);
		setDir('E');
	}
	
}
