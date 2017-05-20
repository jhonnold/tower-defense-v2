package entity.bullet;

import entity.enemy.Enemy;
import javafx.scene.image.Image;

public class RegularBullet extends Bullet {
	
	public static final int DAMAGE = 1;
	public static final int SPEED = 4;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile272.png";
	
	public RegularBullet(double x, double y, Enemy enemy) {
		super(x, y, enemy, SPEED, DAMAGE);
		
		img = new Image(IMAGE_URL);
	}

}
