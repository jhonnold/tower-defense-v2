package gui;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Bullet;
import entity.Enemy;
import entity.SimpleEnemy;
import entity.SimpleTower;
import entity.Tower;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
public class Game extends Canvas {

    public static int TILE_SIZE = 64;
    private final int GRID_WIDTH = 16;
    private final int GRID_HEIGHT = 10;

    private Image[] gridSet;
    
    private final int[][] grid = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0},
            {0, 0, 0, 4, 9, 3, 3, 3, 3, 3, 10, 2, 0, 0, 0, 0},
            {0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 4, 2, 0, 0, 0, 0},
            {0, 0, 0, 4, 2, 0, 0, 5, 1, 1, 11, 2, 0, 0, 0, 0},
            {0, 0, 0, 4, 2, 0, 0, 4, 9, 3, 3, 7, 0, 0, 0, 0},
            {0, 0, 0, 4, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 11, 2, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
            {3, 3, 3, 3, 7, 0, 0, 4, 12, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 8, 3, 3, 3, 3, 3, 3, 3, 3}
    };
    
    private final char[][] route = new char[][] {
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'W', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'E', 'X', 'X', 'N', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    	{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'E', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
    };
    
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;	
    private ArrayList<Bullet> bullets;
    
    private int mx, my;
    private Tower selectedTower;
    
    public Game (int width, int height) {
        // TODO
    	super(width, height);
    	
    	towers = new ArrayList<>();
    	enemies = new ArrayList<>();
    	bullets = new ArrayList<>();
    	
        init();
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
        
        towers.add(new SimpleTower(100, 100));
        towers.add(new SimpleTower(800, 400));
        enemies.add(new SimpleEnemy(0, 8 * TILE_SIZE));
        enemies.add(new SimpleEnemy(-3 * TILE_SIZE, 8 * TILE_SIZE));
        
        setOnMouseMoved((MouseEvent e) -> {
        	mx = (int) e.getX();
        	my = (int) e.getY();
        });
        
        setOnMouseClicked((MouseEvent e) -> {
        	if (selectedTower != null) {
        		towers.add(new SimpleTower(mx, my));
        		selectedTower = null;
        	}
        });
        
        selectedTower = new SimpleTower(mx, my);
    }

    public void repaint() {
        // TODO
    	GraphicsContext gc = getGraphicsContext2D();
    	
        gc.clearRect(0, 0, TILE_SIZE * GRID_WIDTH, TILE_SIZE * GRID_HEIGHT);
        
        // DRAW BACKGROUND MAP
        // -------------------
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j ++) {
                gc.drawImage(gridSet[grid[j][i]], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        // -------------------
        
        // DRAW TOWERS
        for (Tower t : towers) {
        	t.draw(gc);
        }
        
        // DRAW ENEMIES
        for (Enemy e : enemies) {
        	e.draw(gc);
        }
        
        // DRAW BULETS
        for (Bullet b : bullets) {
        	b.draw(gc);
        }
        
        if (selectedTower != null) {
        	selectedTower.draw(gc, mx, my);
        }
        
    }

    public void update() {
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
    	
    	Iterator<Enemy> eIterator = enemies.iterator();
    	
    	while (eIterator.hasNext()) {
    		Enemy e = eIterator.next();
    		
    		if (e.isDead()) {
    			eIterator.remove();
    			e = null;
    			continue;
    		}
    		
    		e.move(route);
    	}
    	
    	for (Tower t : towers) {
    		for (Enemy e : enemies) {			
    			if (t.distance(e) < t.getRange() && t.canFire()) {
    				bullets.add(t.fire(e));
    			}		
    		}
    		t.updateAngle();
    	}
    	
    	
    }
}
