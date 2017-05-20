package entity.bullet;

import entity.enemy.Enemy;
import javafx.scene.image.Image;

public class RangeBullet extends Bullet {
	
	public static final int DAMAGE = 1;
	public static final int SPEED = 1;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile252.png";
	
	public RangeBullet(double x, double y, Enemy enemy) {
		super(x, y, enemy, SPEED, DAMAGE);
		
		img = new Image(IMAGE_URL);
	}
	
	@Override
	public void move() {
		double dx = enemy.getX() - getX();
		double dy = enemy.getY() - getY();
		
		double angle = Math.atan2(dy, dx);
		
		rotationAngle = Math.toDegrees(angle) + 90;
		
		setX(getX() + speed * Math.cos(angle));
		setY(getY() + speed * Math.sin(angle));
		
		speed += .05;
		
		if (speed > 9.8) {
			speed = 9.8;
		}
	}
	
}
