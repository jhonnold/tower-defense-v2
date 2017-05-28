package entity.bullet;

import entity.enemy.Enemy;
import javafx.scene.image.Image;

public class MissileBullet extends Bullet {
	
	public static final int DAMAGE = 3;
	public static final int SPEED = 4;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile251.png";
	public static final String FLAMES_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile295.png";
	
	public MissileBullet(double x, double y, Enemy enemy) {
		super(x, y, enemy, SPEED, DAMAGE);
		
		img = new Image(IMAGE_URL);
		flames = new Image(FLAMES_IMAGE_URL);
	}


}	
