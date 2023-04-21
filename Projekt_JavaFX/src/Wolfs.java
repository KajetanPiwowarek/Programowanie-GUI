import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Wolfs {
    public static ImageView WolfRightUp, WolfRightDown, WolfLeftUp, WolfLeftDown;
    public static boolean isWolfLeftDown, isWolfLeftUp, isWolfRightDown, isWolfRightUp;

    public static void getWolfs() {
        try {
            InputStream stream1 = new FileInputStream("./src/PNG/WolfRightUp.png");
            Image image1 = new Image(stream1);

            WolfRightUp = new ImageView();
            WolfRightUp.setImage(image1);
            WolfRightUp.setX(360);
            WolfRightUp.setY(220);
            WolfRightUp.setFitWidth(300);
            WolfRightUp.setFitHeight(450);
            WolfRightUp.setPreserveRatio(true);

            InputStream stream2 = new FileInputStream("./src/PNG/WolfRightDown.png");
            Image image2 = new Image(stream2);

            WolfRightDown = new ImageView();
            WolfRightDown.setImage(image2);
            WolfRightDown.setX(400);
            WolfRightDown.setY(220);
            WolfRightDown.setFitWidth(300);
            WolfRightDown.setFitHeight(450);
            WolfRightDown.setPreserveRatio(true);

            InputStream stream3 = new FileInputStream("./src/PNG/WolfLeftUp.png");
            Image image3 = new Image(stream3);

            WolfLeftUp = new ImageView();
            WolfLeftUp.setImage(image3);
            WolfLeftUp.setX(170);
            WolfLeftUp.setY(220);
            WolfLeftUp.setFitWidth(300);
            WolfLeftUp.setFitHeight(450);
            WolfLeftUp.setPreserveRatio(true);

            InputStream stream4 = new FileInputStream("./src/PNG/WolfLeftDown.png");
            Image image4 = new Image(stream4);

            WolfLeftDown = new ImageView();
            WolfLeftDown.setImage(image4);
            WolfLeftDown.setX(170);
            WolfLeftDown.setY(220);
            WolfLeftDown.setFitWidth(300);
            WolfLeftDown.setFitHeight(450);
            WolfLeftDown.setPreserveRatio(true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void setDirection(String direction){
        if(direction.equals("LeftUp")) {
            isWolfLeftUp = true;
            isWolfLeftDown = false;
            isWolfRightUp = false;
            isWolfRightDown = false;
        }else if(direction.equals("LeftDown")){
            isWolfLeftUp = false;
            isWolfLeftDown = true;
            isWolfRightUp = false;
            isWolfRightDown = false;
        }else if(direction.equals("RightUp")){
            isWolfLeftUp = false;
            isWolfLeftDown = false;
            isWolfRightUp = true;
            isWolfRightDown = false;
        }else if(direction.equals("RightDown")){
            isWolfLeftUp = false;
            isWolfLeftDown = false;
            isWolfRightUp = false;
            isWolfRightDown = true;
        }
    }
}
