package entity.tower;

import static gui.Game.TILE_SIZE;

import entity.bullet.Bullet;
import entity.bullet.MissileBullet;
import entity.enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import main.Main;

public class PowerTower extends Tower {

	public static final int PRICE = 25;
	public static final int RANGE = TILE_SIZE * 3;
	public static final String BASE_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile181.png";
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile205.png";
	public static final String HALF_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile205_5.png";
	public static final String BLANK_IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile228.png";
	
	private Image halfImg;
	
	private int bulletCount = 2;
	
	public PowerTower(int x, int y) {
		super(x, y);
		
		baseImg = new Image(BASE_IMAGE_URL);
		img = new Image(IMAGE_URL);
		halfImg = new Image(HALF_IMAGE_URL);
		blankImg = new Image(BLANK_IMAGE_URL);
		
		range = RANGE;
		damage = 5;
		shotDelay = 100;
	}
	
	@Override
	public Bullet fire(Enemy e) {
		lastShotTime = Main.CURRENT_GAME_TICK;
		lastEnemy = e;
		
		double dy = (double) TILE_SIZE * .2 * Math.sin(Math.toRadians(rotationAngle));
		double dx = (double) TILE_SIZE * .2 * Math.cos(Math.toRadians(rotationAngle));
		
		if (bulletCount == 2) {
			bulletCount--;
			return new MissileBullet(getX() + dx, getY() + dy, e);
		} else {
			bulletCount++;
			return new MissileBullet(getX() - dx, getY() - dy, e);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		double xi = getX() - TILE_SIZE / 2;
		double yi = getY() - TILE_SIZE / 2;
		
		if (baseImg != null) {
			gc.drawImage(baseImg, xi, yi, TILE_SIZE, TILE_SIZE);
		}
		
		Rotate r = new Rotate(rotationAngle, getX(), getY());
		gc.save();
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		
		if (!canFire() && bulletCount == 2) {
			gc.drawImage(blankImg, xi, yi, TILE_SIZE, TILE_SIZE);
		} else if (!canFire() && bulletCount == 1){
			gc.drawImage(halfImg, xi, yi, TILE_SIZE, TILE_SIZE);
		} else {
			gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		}
		
		gc.restore();
		
	}
	
}
