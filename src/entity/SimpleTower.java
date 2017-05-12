package entity;

import static main.Game.TILE_SIZE;

import javafx.scene.image.Image;

public class SimpleTower extends Tower {
	
	public SimpleTower(int x, int y) {
		super(x, y);
		img = new Image("file:img/PNG/Retina/towerDefense_tile205.png");
		setRange(TILE_SIZE * 4);
		setDamage(1);
		setShotDelay(1000);
	}
	
}
