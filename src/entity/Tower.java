package entity;

import static gui.Game.TILE_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public abstract class Tower extends Entity {
	
	private int range, damage, shotDelay;
	private long lastShotTime;
	
	private Enemy lastEnemy;
	private double rotationAngle = 0;
	
	Image img;
	
	public Tower(int x, int y) {
		super(x, y);
		lastShotTime = System.currentTimeMillis();
	}
	
	public int getRange() {
		return range;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setShotDelay(int shotDelay) {
		this.shotDelay = shotDelay;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		double xi = x - TILE_SIZE / 2;
		double yi = y - TILE_SIZE / 2;
		
		Rotate r = new Rotate(rotationAngle, x, y);
		gc.save();
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		gc.restore();
		
		gc.fillText("" + rotationAngle, xi, yi);
		
	}
	
	public void draw(GraphicsContext gc, int x, int y) {
		double xi = x - TILE_SIZE / 2;
		double yi = y - TILE_SIZE / 2;
		
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
	}
	
	public boolean canFire() {
		int dt = (int)(System.currentTimeMillis() - lastShotTime);
		
		if (dt >= shotDelay) {
			//lastShotTime = System.currentTimeMillis();
			return true;
		} else {
			return false;
		}
	}
	
	public Bullet fire(Enemy e) {
		lastShotTime = System.currentTimeMillis();
		lastEnemy = e;
		return new Bullet(x, y, e, 2, damage);
	}
	
	public void updateAngle() {
		if (lastEnemy != null) {
			double dx = lastEnemy.getX() - x;
			double dy = lastEnemy.getY() - y;
			
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
