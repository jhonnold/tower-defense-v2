package gui;

import static utils.InputHandler.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import entity.bullet.Bullet;
import entity.enemy.Enemy;
import entity.tower.Tower;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import level.Level;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
public class Game extends Canvas {

	public static int TILE_SIZE = 64;
	private final int GRID_WIDTH = 16;
	private final int GRID_HEIGHT = 10;

	private Image[] gridSet;

	private final int[][] grid = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0 }, { 0, 0, 0, 4, 9, 3, 3, 3, 3, 3, 10, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 4, 2, 0, 0, 0, 0 }, { 0, 0, 0, 4, 2, 0, 0, 5, 1, 1, 11, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 2, 0, 0, 4, 9, 3, 3, 7, 0, 0, 0, 0 }, { 0, 0, 0, 4, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 11, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0 }, { 3, 3, 3, 3, 7, 0, 0, 4, 12, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 3, 3, 3, 3, 3, 3, 3, 3 } };

	private final char[][] route = new char[][] {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'W', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'E', 'X', 'X', 'N', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	private List<Tower> towers;
	private List<Enemy> enemies;
	private List<Bullet> bullets;

	private int mx, my, money = 50, levelNum = 1;
	private Tower selectedTower;
	private Button levelButton;

	private boolean contained = true;

	public Game(int width, int height) {
		// TODO
		super(width, height);

		towers = Collections.synchronizedList(new ArrayList<>());
		enemies = Collections.synchronizedList(new ArrayList<>());
		bullets = Collections.synchronizedList(new ArrayList<>());

		init();
	}

	public void setLevelButton(Button levelButton) {
		this.levelButton = levelButton;
		this.levelButton.setOnAction((ActionEvent e) -> {
			new Thread(new Level(levelNum++, this)).start();
			this.levelButton.setVisible(false);
		});
	}

	public void onLevelDone() {
		levelButton.setVisible(true);
	}

	public int getMoney() {
		return money;
	}

	public void buyTower(int cost) {
		money -= cost;
	}

	public void setSelectedTower(Tower t) {
		selectedTower = t;
	}

	public void addEnemy(Enemy simpleEnemy) {
		enemies.add(simpleEnemy);
	}

	private void init() {
		// TODO

		gridSet = new Image[13];
		gridSet[0] = new Image("file:img/PNG/Retina/towerDefense_tile162.png");
		gridSet[1] = new Image("file:img/PNG/Retina/towerDefense_tile254.png");
		gridSet[2] = new Image("file:img/PNG/Retina/towerDefense_tile230.png");
		gridSet[3] = new Image("file:img/PNG/Retina/towerDefense_tile208.png");
		gridSet[4] = new Image("file:img/PNG/Retina/towerDefense_tile232.png");
		gridSet[5] = new Image("file:img/PNG/Retina/towerDefense_tile210.png");
		gridSet[6] = new Image("file:img/PNG/Retina/towerDefense_tile211.png");
		gridSet[7] = new Image("file:img/PNG/Retina/towerDefense_tile234.png");
		gridSet[8] = new Image("file:img/PNG/Retina/towerDefense_tile233.png");
		gridSet[9] = new Image("file:img/PNG/Retina/towerDefense_tile207.png");
		gridSet[10] = new Image("file:img/PNG/Retina/towerDefense_tile209.png");
		gridSet[11] = new Image("file:img/PNG/Retina/towerDefense_tile255.png");
		gridSet[12] = new Image("file:img/PNG/Retina/towerDefense_tile253.png");

		setOnMouseMoved((MouseEvent e) -> {
			mx = (int) e.getX();
			my = (int) e.getY();
		});

		setOnMouseExited((MouseEvent e) -> {
			contained = false;
		});

		setOnMouseEntered((MouseEvent e) -> {
			contained = true;
		});

		setOnMouseClicked((MouseEvent e) -> {
			selectedTower = handleMouseClick(e, grid, towers, selectedTower, this);
		});

	}

	public void repaint() {
		// TODO
		GraphicsContext gc = getGraphicsContext2D();

		gc.clearRect(0, 0, TILE_SIZE * GRID_WIDTH, TILE_SIZE * GRID_HEIGHT);

		// DRAW BACKGROUND MAP
		// -------------------
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				gc.drawImage(gridSet[grid[j][i]], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		// -------------------

		// DRAW TOWERS
		synchronized (towers) {
			for (Tower t : towers) {
				t.draw(gc);
			}
		}

		// DRAW ENEMIES
		synchronized (enemies) {
			for (Enemy e : enemies) {
				e.draw(gc);
			}
		}

		// DRAW BULETS
		synchronized (bullets) {
			for (Bullet b : bullets) {
				b.draw(gc);
			}
		}

		// DRAW THE SELECTED TOWER
		if (selectedTower != null && contained) {
			if (grid[my / TILE_SIZE][mx / TILE_SIZE] == 0 && !collides(towers, mx, my)) {
				selectedTower.draw(gc, mx, my);

				int range = selectedTower.getRange();
				gc.setStroke(Color.RED);
				gc.strokeOval(mx - range, my - range, range * 2, range * 2);
			} else {
				gc.fillOval(mx, my, 5, 5);
			}
		}

		gc.fillText("Money: " + money, 100, 100);

	}

	public void update() {
		synchronized (bullets) {
			Iterator<Bullet> bIterator = bullets.iterator();

			while (bIterator.hasNext()) {
				Bullet b = bIterator.next();

				b.move();

				if (b.collided()) {
					b.doDamage();
					bIterator.remove();
					b = null;
				}
			}
		}

		synchronized (enemies) {
			Iterator<Enemy> eIterator = enemies.iterator();

			while (eIterator.hasNext()) {
				Enemy e = eIterator.next();

				if (e.isDead()) {
					eIterator.remove();
					money += e.getReward();
					e = null;
					continue;
				}

				e.move(route);
			}
		}

		for (Tower t : towers) {

			Enemy frontEnemy = null;

			for (Enemy e : enemies) {
				if (e.distance(t) < t.getRange()) {
					if (frontEnemy == null) {
						frontEnemy = e;
					} else if (e.getDistanceTraveled() > frontEnemy.getDistanceTraveled()) {
						frontEnemy = e;
					}
				}
			}
			
			if (frontEnemy != null && t.canFire()) {
				bullets.add(t.fire(frontEnemy));
			}
			
			t.updateAngle();
		}
	}
}
