import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Eggs {
    public static ImageView EggLeftUp, EggLeftDown, EggRightUp, EggRightDown;

    public static void getEggs(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("./src/PNG/Egg.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);

        EggLeftUp = new ImageView();
        EggLeftUp.setImage(image);
        EggLeftUp.setX(200);
        EggLeftUp.setY(195);
        EggLeftUp.setFitWidth(30);
        EggLeftUp.setFitHeight(30);
        EggLeftUp.setPreserveRatio(true);

        EggLeftDown = new ImageView();
        EggLeftDown.setImage(image);
        EggLeftDown.setX(200);
        EggLeftDown.setY(280);
        EggLeftDown.setFitWidth(30);
        EggLeftDown.setFitHeight(30);
        EggLeftDown.setPreserveRatio(true);

        EggRightUp = new ImageView();
        EggRightUp.setImage(image);
        EggRightUp.setX(700);
        EggRightUp.setY(185);
        EggRightUp.setFitWidth(30);
        EggRightUp.setFitHeight(30);
        EggRightUp.setPreserveRatio(true);

        EggRightDown = new ImageView();
        EggRightDown.setImage(image);
        EggRightDown.setX(700);
        EggRightDown.setY(280);
        EggRightDown.setFitWidth(30);
        EggRightDown.setFitHeight(30);
        EggRightDown.setPreserveRatio(true);
    }
}
