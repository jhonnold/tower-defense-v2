package main;

import java.util.ArrayList;

import entity.SimpleTower;
import entity.Tower;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
public class Game extends Canvas {

    public static int TILE_SIZE = 64;
    private final int GRID_WIDTH = 16;
    private final int GRID_HEIGHT = 10;

    private Image[] gridSet;
    
    private boolean paused = true;
    
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
    
    private ArrayList<Tower> towers = new ArrayList<>();
    
    Game (int width, int height) {
        // TODO
    	super(width, height);
    	
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
    }

    void repaint() {
        // TODO
    	GraphicsContext gc = getGraphicsContext2D();
    	
        gc.clearRect(0, 0, TILE_SIZE * GRID_WIDTH, TILE_SIZE * GRID_HEIGHT);

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j ++) {
                gc.drawImage(gridSet[grid[j][i]], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        
        for (Tower t : towers) {
        	t.draw(gc);
        }
        
    }

    void update() {
        if (paused) {
        	return;
        }
    }
}
