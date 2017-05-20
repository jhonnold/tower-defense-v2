package entity.bullet;

import static gui.Game.TILE_SIZE;

import entity.enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class DoubleBullet extends Bullet {
	
	public static final int DAMAGE = 1;
	public static final int SPEED = 4;
	public static final String IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile273.png";
	
	public DoubleBullet(double x, double y, Enemy enemy) {
		super(x, y, enemy, SPEED, DAMAGE);
		
		img = new Image(IMAGE_URL);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		double xi = getX() - TILE_SIZE / 2;
		double yi = getY() - TILE_SIZE / 2;
		
		Rotate r;
			
		r = new Rotate(rotationAngle, getX(), getY());
		gc.save();
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		gc.drawImage(img, xi - (double) TILE_SIZE * .1, yi, TILE_SIZE, TILE_SIZE);
		gc.drawImage(img, xi + (double) TILE_SIZE * .1, yi, TILE_SIZE, TILE_SIZE);
		gc.restore();
	}
	
}
