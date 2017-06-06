package entity.enemy

import javafx.scene.image.Image

class DurableEnemy(x: Int, y: Int) : Enemy(x.toDouble(), y.toDouble(), 25, .5) {
    override var img: Image = Image(IMAGE_URL)

    init {
        setDir('E')
        reward = 3
    }

    companion object {

        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile247.png"
    }

}
