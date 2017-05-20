package entity.tower;

import static gui.Game.TILE_SIZE;

import entity.bullet.Bullet;
import entity.bullet.MissileBullet;
import entity.enemy.Enemy;
import javafx.scene.image.Image;
import main.Main;

public class PowerTower extends Tower {

	public static final int RANGE = TILE_SIZE * 3;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile250.png";
	
	public PowerTower(int x, int y) {
		super(x, y);
		img = new Image(IMAGE_URL);
		range = RANGE;
		damage = 5;
		shotDelay = 100;
	}
	
	@Override
	public Bullet fire(Enemy e) {
		lastShotTime = Main.CURRENT_GAME_TICK;
		lastEnemy = e;
		return new MissileBullet(getX(), getY(), e);
	}
}
