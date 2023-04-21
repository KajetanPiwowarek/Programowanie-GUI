import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CrackedEgg {
    public static Node getCrackedEggRight(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("./src/PNG/CrackedEggRight.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);

        ImageView CrackedEggRight = new ImageView();
        CrackedEggRight.setImage(image);
        CrackedEggRight.setX(610);
        CrackedEggRight.setY(400);
        CrackedEggRight.setFitWidth(40);
        CrackedEggRight.setFitHeight(40);
        CrackedEggRight.setPreserveRatio(true);

        return CrackedEggRight;
    }
    public static Node getCrackedEggLeft(){
        InputStream stream = null;
        try {
            stream = new FileInputStream("./src/PNG/CrackedEggLeft.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);

        ImageView CrackedEggLeft = new ImageView();
        CrackedEggLeft.setImage(image);
        CrackedEggLeft.setX(290);
        CrackedEggLeft.setY(410);
        CrackedEggLeft.setFitWidth(40);
        CrackedEggLeft.setFitHeight(40);
        CrackedEggLeft.setPreserveRatio(true);

        return CrackedEggLeft;
    }
}
