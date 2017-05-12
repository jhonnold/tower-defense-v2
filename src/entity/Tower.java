package entity;

import static main.Game.TILE_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Tower extends Entity {
	
	private int range, damage, shotDelay;
	private long lastShotTime;
	
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
		
		double xi = getX() - TILE_SIZE / 2;
		double yi = getY() - TILE_SIZE / 2;
		
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		
		if (!canFire()) {
			gc.setFill(Color.RED);
			gc.fillText("FIRE!", xi, yi);
		}
		
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
		return new Bullet(x, y, e, 2, 10);
	}
	
}
