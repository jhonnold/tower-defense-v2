package entity;

import static main.Game.TILE_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Bullet extends Entity {
	
	private int speed, damage;
	private Enemy enemy;
	
	private double rotationAngle = 0;
	
	Image img;
	
	public Bullet(double x, double y, Enemy enemy, int speed, int damage) {
		super(x, y);
		this.enemy = enemy;
		this.speed = speed;
		this.damage = damage;
		
		img = new Image("file:img/PNG/Retina/towerDefense_tile251.png");
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
	}
	
	public void move() {
		
		double dx = enemy.getX() - x;
		double dy = enemy.getY() - y;
		
		double angle = Math.atan2(dy, dx);
		
		rotationAngle = Math.toDegrees(angle) + 90;
		
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
