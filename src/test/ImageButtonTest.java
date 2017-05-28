package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Jay on 5/28/2017.
 */
public class ImageButtonTest extends Application {

    @Override
    public void start(Stage primary) {

        ImageButton button = new ImageButton("file:src/test/rsc/up.png", "file:src/test/rsc/down.png");

        BorderPane bp = new BorderPane();
        bp.setCenter(button);

        Scene scene = new Scene(bp);

        primary.setScene(scene);
        primary.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
