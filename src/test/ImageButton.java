package test;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView {

    public ImageButton(String upImage, String downImage) {
        Image up = new Image(upImage, 128, 128, true, true);
        Image down = new Image(downImage, 128, 128, true, true);

        setImage(up);

        setOnMousePressed((MouseEvent e) -> {
            setImage(down);
        });

        setOnMouseReleased((MouseEvent e) -> {
            setImage(up);
        });
    }
}
