package entity.tower;

import static gui.Game.TILE_SIZE;

import entity.bullet.Bullet;
import entity.bullet.MissileBullet;
import entity.enemy.Enemy;
import javafx.scene.image.Image;
import main.Main;

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

	@Override
	public Bullet fire(Enemy e) {
		lastShotTime = Main.CURRENT_GAME_TICK;
		lastEnemy = e;
		return new MissileBullet(getX(), getY(), e);
	}
	
	
	
}
