package test;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView {

    Image up, down;


    public ImageButton(String upImage, String downImage) {
        super(upImage);

        up = new Image(upImage);
        down = new Image(downImage);

        setOnMousePressed((MouseEvent e) -> {
            setImage(down);
        });

        setOnMouseReleased((MouseEvent e) -> {
            setImage(up);
        });
    }
}
