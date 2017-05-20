package entity.tower;

import static gui.Game.TILE_SIZE;

import entity.bullet.Bullet;
import entity.bullet.RangeBullet;
import entity.enemy.Enemy;
import javafx.scene.image.Image;
import main.Main;

public class RangeTower extends Tower {
	
	public static final int RANGE = TILE_SIZE * 7;
	public static final String BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile180.png";
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile206.png";
	
	public RangeTower(int x, int y) {
		super(x, y);
		baseImg = new Image(BASE_IMAGE_URL);
		img = new Image(IMAGE_URL);
		range = RANGE;
		damage = 5;
		shotDelay = 100;
	}
	
	@Override
	public Bullet fire(Enemy e) {
		lastShotTime = Main.CURRENT_GAME_TICK;
		lastEnemy = e;
		
		double dy = (double) TILE_SIZE * .3 * Math.sin(Math.toRadians(rotationAngle - 90));
		double dx = (double) TILE_SIZE * .3 * Math.cos(Math.toRadians(rotationAngle - 90));
		
		return new RangeBullet(getX() + dx, getY() + dy, e);
	}
	
}
