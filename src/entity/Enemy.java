package entity;

import static main.Game.TILE_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Enemy extends Entity {
	
	private int health, speed;
	private char dir;
	
	Image img;
	
	public Enemy(int x, int y, int health, int speed) {
		super(x, y);
		this.health = health;
		this.speed = speed;
	}
	
	public void setDir(char dir) {
		this.dir = dir;
	}
	
	public void move(char[][] grid) {
		int gx, gy;
		
		switch(dir) {
		case 'N':
			gx = (int)(getX() / TILE_SIZE);
			gy = (int)((getY() + TILE_SIZE / 2) / TILE_SIZE);
			break;
		case 'S':
			gx = (int)(getX() / TILE_SIZE);
			gy = (int)((getY() - TILE_SIZE / 2) / TILE_SIZE);
			break;
		case 'E':
			gx = (int)((getX() - TILE_SIZE / 2) / TILE_SIZE);
			gy = (int)(getY() / TILE_SIZE);
			break;
		case 'W':
			gx = (int)((getX() + TILE_SIZE / 2) / TILE_SIZE);
			gy = (int)(getY() / TILE_SIZE);
			break;
		default:
			gx = (int)(getX() / TILE_SIZE);
			gy = (int)(getY() / TILE_SIZE);
			break;
		}
		
		char newDir = grid[gy][gx];
		
		if (newDir != 'X') {
			dir = newDir;
		}
		
		switch(dir) {
		case 'N':
			y -= speed;
			break;
		case 'S':
			y += speed;
			break;
		case 'E':
			x += speed;
			break;
		case 'W':
			x -= speed;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {		
		gc.drawImage(img, getX(), getY(), TILE_SIZE, TILE_SIZE);
	}
	
}
