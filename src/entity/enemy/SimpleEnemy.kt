package entity.enemy

import javafx.scene.image.Image

class SimpleEnemy(x: Int, y: Int) : Enemy(x.toDouble(), y.toDouble(), 5, 1.0) {

    init {
        img = Image(IMAGE_URL)
        setDir('E')
        reward = 1
    }

    companion object {

        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile245.png"
    }

}
