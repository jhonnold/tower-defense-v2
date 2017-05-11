package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
class Game {

    private final int TILE_SIZE = 64;
    private final int GRID_WIDTH = 16;
    private final int GRID_HEIGHT = 10;

    private Image[] tileSet;

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

    Game () {
        // TODO
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

    void repaint(GraphicsContext gc) {
        // TODO
        gc.clearRect(0, 0, TILE_SIZE * GRID_WIDTH, TILE_SIZE * GRID_HEIGHT);

        for (int i = 0; i < GRID_WIDTH * TILE_SIZE; i += TILE_SIZE) {
            for (int j = 0; j < GRID_HEIGHT * TILE_SIZE; j += TILE_SIZE) {
                gc.drawImage(tileSet[grid[j / TILE_SIZE][i / TILE_SIZE]], i, j, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    void update() {
        // TODO
    }
}
