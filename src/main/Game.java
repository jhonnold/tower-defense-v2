package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
class Game extends Canvas {

    private final int TILE_SIZE = 64;
    private final int GRID_WIDTH = 16;
    private final int GRID_HEIGHT = 10;

    private Image[] tileSet;
    
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

    Game (int width, int height) {
        // TODO
    	super(width, height);
    	
        init();
    }

    private void init() {
        // TODO
        tileSet = new Image[13];
        tileSet[0] = new Image("file:img/PNG/Retina/towerDefense_tile162.png");
        tileSet[1] = new Image("file:img/PNG/Retina/towerDefense_tile254.png");
        tileSet[2] = new Image("file:img/PNG/Retina/towerDefense_tile230.png");
        tileSet[3] = new Image("file:img/PNG/Retina/towerDefense_tile208.png");
        tileSet[4] = new Image("file:img/PNG/Retina/towerDefense_tile232.png");
        tileSet[5] = new Image("file:img/PNG/Retina/towerDefense_tile210.png");
        tileSet[6] = new Image("file:img/PNG/Retina/towerDefense_tile211.png");
        tileSet[7] = new Image("file:img/PNG/Retina/towerDefense_tile234.png");
        tileSet[8] = new Image("file:img/PNG/Retina/towerDefense_tile233.png");
        tileSet[9] = new Image("file:img/PNG/Retina/towerDefense_tile207.png");
        tileSet[10] = new Image("file:img/PNG/Retina/towerDefense_tile209.png");
        tileSet[11] = new Image("file:img/PNG/Retina/towerDefense_tile255.png");
        tileSet[12] = new Image("file:img/PNG/Retina/towerDefense_tile253.png");
    }

    void repaint() {
        // TODO
    	GraphicsContext gc = getGraphicsContext2D();
    	
        gc.clearRect(0, 0, TILE_SIZE * GRID_WIDTH, TILE_SIZE * GRID_HEIGHT);

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j ++) {
                gc.drawImage(tileSet[grid[j][i]], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    void update() {
        if (paused) {
        	return;
        }
    }
}
