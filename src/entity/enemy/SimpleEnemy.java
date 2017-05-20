package entity.enemy;

import javafx.scene.image.Image;

public class SimpleEnemy extends Enemy {
	
	public SimpleEnemy(int x, int y) {
		super(x, y, 10, 1);
		img = new Image("file:img/PNG/Retina/towerDefense_tile245.png");
		setDir('E');
	}
	
}
