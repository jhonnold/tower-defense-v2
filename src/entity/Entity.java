package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	
	private double x, y;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(Entity e) {
		double dx = e.getX() - x;
		double dy = e.getY() - y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double distance(int mx, int my) {
		double dx = mx - x;
		double dy = my - y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public abstract void draw(GraphicsContext gc);
	
}
