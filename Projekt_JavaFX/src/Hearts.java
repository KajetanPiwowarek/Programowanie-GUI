import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Hearts {
    public static ImageView Heart1, Heart2, Heart3;
    public static int heartCounter = 3;

    public static void getHearts(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("./src/PNG/Heart.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);

        Heart1 = new ImageView();
        Heart1.setImage(image);
        Heart1.setX(400);
        Heart1.setY(140);
        Heart1.setFitWidth(50);
        Heart1.setFitHeight(50);
        Heart1.setPreserveRatio(true);

        Heart2 = new ImageView();
        Heart2.setImage(image);
        Heart2.setX(450);
        Heart2.setY(140);
        Heart2.setFitWidth(50);
        Heart2.setFitHeight(50);
        Heart2.setPreserveRatio(true);

        Heart3 = new ImageView();
        Heart3.setImage(image);
        Heart3.setX(500);
        Heart3.setY(140);
        Heart3.setFitWidth(50);
        Heart3.setFitHeight(50);
        Heart3.setPreserveRatio(true);
    }
}
