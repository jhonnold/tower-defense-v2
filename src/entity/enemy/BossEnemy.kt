package entity.enemy

import javafx.scene.image.Image

class BossEnemy(x: Int, y: Int) : Enemy(x.toDouble(), y.toDouble(), 20, 2.5) {
    override var img: Image = Image(IMAGE_URL)

    init {
        setDir('E')
        reward = 10
    }

    companion object {

        val IMAGE_URL = "file:img/PNG/Retina/towerDefense_tile246.png"
    }

}
