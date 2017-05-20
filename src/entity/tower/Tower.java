package entity.tower;

import static gui.Game.TILE_SIZE;

import entity.Entity;
import entity.bullet.Bullet;
import entity.enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import main.Main;

public abstract class Tower extends Entity {
	
	int range, damage, shotDelay;
	private long lastShotTime;
	
	private Enemy lastEnemy;
	private double rotationAngle = 0;
	
	Image img, baseImg;
	
	public Tower(int x, int y) {
		super(x, y);
		lastShotTime = -100;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getDamage() {
		return damage;
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
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		gc.restore();
		
	}
	
	public void draw(GraphicsContext gc, int x, int y) {
		double xi = x - TILE_SIZE / 2;
		double yi = y - TILE_SIZE / 2;
		
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
	}
	
	public boolean canFire() {
		int dt = (int)(Main.CURRENT_GAME_TICK - lastShotTime);
		
		if (dt >= shotDelay) {
			return true;
		} else {
			return false;
		}
	}
	
	public Bullet fire(Enemy e) {
		lastShotTime = Main.CURRENT_GAME_TICK;
		lastEnemy = e;
		return new Bullet(getX(), getY(), e, 2, damage);
	}
	
	public void updateAngle() {
		if (lastEnemy != null) {
			double dx = lastEnemy.getX() - getX();
			double dy = lastEnemy.getY() - getY();
			
			rotationAngle = Math.toDegrees(Math.atan2(dx, -1 * dy));
		}
	}
	
	public static int getRange(Tower t) {
		if (t instanceof SimpleTower) {
			return SimpleTower.RANGE;
		} else {
			return 0;
		}
	}
	
}
