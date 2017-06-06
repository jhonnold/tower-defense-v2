package entity.enemy

import javafx.scene.image.Image

class SpeedEnemy(x: Int, y: Int) : Enemy(x.toDouble(), y.toDouble(), 5, 3.0) {

    init {
        img = Image(IMAGE_URL)
        setDir('E')
        reward = 3
    }

    companion object {

        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile248.png"
    }

}
