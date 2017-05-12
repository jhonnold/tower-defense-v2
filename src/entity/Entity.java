package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	
	double x, y;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(Entity e) {
		double dx = e.getX() - x;
		double dy = e.getY() - y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public abstract void draw(GraphicsContext gc);
	
}
