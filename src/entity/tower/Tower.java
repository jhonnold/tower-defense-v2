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
	long lastShotTime;
	double rotationAngle = 0;
	
	Enemy lastEnemy;
	Image img, baseImg, blankImg;
	
	public Tower(int x, int y) {
		super(x, y);
		lastShotTime = Main.CURRENT_GAME_TICK - 100;
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
		
		if (!canFire() && blankImg != null) {
			gc.drawImage(blankImg, xi, yi, TILE_SIZE, TILE_SIZE);
		} else {
			gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		}
		
		gc.restore();
		
	}
	
	public void draw(GraphicsContext gc, int x, int y) {
		double xi = x - TILE_SIZE / 2;
		double yi = y - TILE_SIZE / 2;
		
		if (baseImg != null) {
			gc.drawImage(baseImg, xi, yi, TILE_SIZE, TILE_SIZE);
		}
		
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
	}
	
	public boolean canFire() {
		int dt = (int)(Main.CURRENT_GAME_TICK - lastShotTime);
		
		return dt >= shotDelay;
	}
	
	public abstract Bullet fire(Enemy e);
	
	public void updateAngle() {
		if (lastEnemy != null) {
			double dx = lastEnemy.getX() - getX();
			double dy = lastEnemy.getY() - getY();
			
			rotationAngle = Math.toDegrees(Math.atan2(dx, -dy));
		}
	}
}
