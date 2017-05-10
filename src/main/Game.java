package main;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by zomby on 5/10/17 @ 4:20 PM.
 */
class Game {

    int x = 0, y = 0;

    Game () {
        // TODO
        init();
    }

    private void init() {
        // TODO
    }

    void repaint(GraphicsContext gc) {
        gc.fillRect(x, y, 1, 1);
    }

    void update() {
        // TODO
        x++;
        y++;
    }
}
