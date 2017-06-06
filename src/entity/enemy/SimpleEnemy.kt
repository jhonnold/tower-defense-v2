package entity.enemy

import javafx.scene.image.Image

class SimpleEnemy(x: Int, y: Int) : Enemy(x.toDouble(), y.toDouble(), 5, 1.0) {
    override var img: Image = Image(IMAGE_URL)

    init {
        setDir('E')
    }

    companion object {

        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile245.png"
    }

}
