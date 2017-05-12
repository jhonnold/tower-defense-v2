package entity;

import static main.Game.TILE_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Entity {
	
	private int speed, damage;
	private Enemy enemy;
	
	Image img;
	
	public Bullet(double x, double y, Enemy enemy, int speed, int damage) {
		super(x, y);
		this.enemy = enemy;
		this.speed = speed;
		this.damage = damage;
		
		img = new Image("file:img/PNG/Retina/towerDefense_tile272.png");
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		double xi = x - TILE_SIZE / 2;
		double yi = y - TILE_SIZE / 2;
		
		gc.drawImage(img, xi, yi, TILE_SIZE, TILE_SIZE);
		
	}
	
	public void move() {
		
		double dx = enemy.getX() - x;
		double dy = enemy.getY() - y;
		
		double angle = Math.atan2(dy, dx);
		
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		
	}
	
	public void doDamage() {
		enemy.takeDamage(damage);
	}
	
	public boolean collided() {
		return distance(enemy) <= 5;
	}
	
}
