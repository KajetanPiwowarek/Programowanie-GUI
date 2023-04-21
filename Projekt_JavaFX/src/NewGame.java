import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class NewGame {

    public NewGame() throws Exception {

        Stage stage = new Stage();

        StackPane stackPaneMain = new StackPane();

        Scene scene = new Scene(stackPaneMain, 1100, 600);

        BorderPane borderPane = new BorderPane();

        //=========================================== Background =======================================================

        InputStream stream = new FileInputStream("./src/PNG/BackgroundImage.png");
        Image image = new Image(stream);
        ImageView Background = new ImageView();
        Background.setImage(image);
        Background.setX(0);
        Background.setY(0);
        Background.setFitWidth(1200);
        Background.setFitHeight(600);
        Background.setPreserveRatio(true);

        //==============================================================================================================

        //=========================================== Game Screen ======================================================

        StackPane stackPane = new StackPane();

        Pane pane = new Pane();

        InputStream stream5 = new FileInputStream("./src/PNG/GameScreen.png");
        Image image5 = new Image(stream5);
        ImageView GameScreen = new ImageView();
        GameScreen.setImage(image5);
        GameScreen.setX(0);
        GameScreen.setY(0);
        GameScreen.setFitWidth(1000);
        GameScreen.setFitHeight(420);
        GameScreen.setPreserveRatio(true);

        //==============================================================================================================

        //================================================= Hearts =====================================================

        Hearts.getHearts();
        pane.getChildren().addAll(Hearts.Heart1, Hearts.Heart2, Hearts.Heart3);

        //==============================================================================================================

        //================================================= Eggs =======================================================

        Eggs.getEggs();

        EggsAnimation eggsAnimation = new EggsAnimation(pane,stage);
        eggsAnimation.createTask();
        eggsAnimation.start();

        //==============================================================================================================

        //================================================= Wolfs ======================================================

        Wolfs.getWolfs();

        pane.getChildren().add(Wolfs.WolfRightDown);

        //==============================================================================================================

        //=========================================== Game Buttons =====================================================

        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.setVgap(10);
        gridPaneLeft.setHgap(10);
        gridPaneLeft.setAlignment(Pos.CENTER);

        //Left Up Button
        StackPane leftUpPane = new StackPane();

        Button leftUp = new Button("");
        leftUp.setShape(new Circle(40));
        leftUp.setMinSize(80, 80);
        leftUp.setMaxSize(80, 80);

        leftUp.setOnAction(e -> {
            pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
            pane.getChildren().add(Wolfs.WolfLeftUp);
            Wolfs.setDirection("LeftUp");
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.NUMPAD4) {
                pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
                pane.getChildren().add(Wolfs.WolfLeftUp);
                Wolfs.setDirection("LeftUp");
            }
        });

        InputStream stream1 = new FileInputStream("./src/PNG/LeftUp.png");
        Image image1 = new Image(stream1);
        ImageView LeftUpArrow = new ImageView();
        LeftUpArrow.setImage(image1);
        LeftUpArrow.setX(0);
        LeftUpArrow.setY(0);
        LeftUpArrow.setFitWidth(60);
        LeftUpArrow.setFitHeight(60);
        LeftUpArrow.setPreserveRatio(true);


        leftUpPane.getChildren().addAll(leftUp, LeftUpArrow);
        gridPaneLeft.add(leftUpPane, 2, 0);

        //Left Down Button
        StackPane leftDownPane = new StackPane();

        Button leftDown = new Button("");
        leftDown.setShape(new Circle(40));
        leftDown.setMinSize(80, 80);
        leftDown.setMaxSize(80, 80);

        leftDown.setOnAction(e -> {
            pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
            pane.getChildren().add(Wolfs.WolfLeftDown);
            Wolfs.setDirection("LeftDown");
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.NUMPAD1) {
                pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
                pane.getChildren().add(Wolfs.WolfLeftDown);
                Wolfs.setDirection("LeftDown");
            }
        });

        InputStream stream2 = new FileInputStream("./src/PNG/LeftDown.png");
        Image image2 = new Image(stream2);
        ImageView LeftDownArrow = new ImageView();
        LeftDownArrow.setImage(image2);
        LeftDownArrow.setX(0);
        LeftDownArrow.setY(0);
        LeftDownArrow.setFitWidth(60);
        LeftDownArrow.setFitHeight(60);
        LeftDownArrow.setPreserveRatio(true);

        leftDownPane.getChildren().addAll(leftDown, LeftDownArrow);
        gridPaneLeft.add(leftDownPane, 2, 1);

        GridPane gridPaneRight = new GridPane();
        gridPaneRight.setVgap(20);
        gridPaneRight.setHgap(10);
        gridPaneRight.setAlignment(Pos.CENTER);

        //Right Up Button
        StackPane rightUpPane = new StackPane();

        Button rightUp = new Button("");
        rightUp.setShape(new Circle(40));
        rightUp.setMinSize(80, 80);
        rightUp.setMaxSize(80, 80);

        rightUp.setOnAction(e -> {
            pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
            pane.getChildren().add(Wolfs.WolfRightUp);
            Wolfs.setDirection("RightUp");
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.NUMPAD6) {
                pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
                pane.getChildren().add(Wolfs.WolfRightUp);
                Wolfs.setDirection("RightUp");
            }
        });

        InputStream stream3 = new FileInputStream("./src/PNG/RightUp.png");
        Image image3 = new Image(stream3);
        ImageView RightUpArrow = new ImageView();
        RightUpArrow.setImage(image3);
        RightUpArrow.setX(0);
        RightUpArrow.setY(0);
        RightUpArrow.setFitWidth(60);
        RightUpArrow.setFitHeight(60);
        RightUpArrow.setPreserveRatio(true);

        rightUpPane.getChildren().addAll(rightUp, RightUpArrow);
        gridPaneRight.add(rightUpPane, 0, 0);

        Label empty = new Label();
        gridPaneRight.add(empty, 1, 0);

        //Right Down Button
        StackPane rightDownPane = new StackPane();

        Button rightDown = new Button("");
        rightDown.setShape(new Circle(40));
        rightDown.setMinSize(80, 80);
        rightDown.setMaxSize(80, 80);

        rightDown.setOnAction(e -> {
            pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
            pane.getChildren().add(Wolfs.WolfRightDown);
            Wolfs.setDirection("RightDown");
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.NUMPAD3) {
                pane.getChildren().removeAll(Wolfs.WolfLeftDown, Wolfs.WolfLeftUp, Wolfs.WolfRightDown, Wolfs.WolfRightUp);
                pane.getChildren().add(Wolfs.WolfRightDown);
                Wolfs.setDirection("RightDown");
            }
        });

        InputStream stream4 = new FileInputStream("./src/PNG/RightDown.png");
        Image image4 = new Image(stream4);
        ImageView RightDownArrow = new ImageView();
        RightDownArrow.setImage(image4);
        RightDownArrow.setX(0);
        RightDownArrow.setY(0);
        RightDownArrow.setFitWidth(60);
        RightDownArrow.setFitHeight(60);
        RightDownArrow.setPreserveRatio(true);

        rightDownPane.getChildren().addAll(rightDown, RightDownArrow);
        gridPaneRight.add(rightDownPane, 0, 1);

        borderPane.setLeft(gridPaneLeft);
        borderPane.setRight(gridPaneRight);

        //==============================================================================================================

        //=========================================== Point Counter ====================================================

        Points.pointsUpdate();

        pane.getChildren().add(Points.pointsLabel);

        //==============================================================================================================

        stackPane.getChildren().addAll(GameScreen, pane);

        borderPane.setCenter(stackPane);

        stackPaneMain.getChildren().addAll(Background, borderPane);

        //==============================================================================================================

        //============================================ Exit Key ========================================================

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            final KeyCombination keyComb = new KeyCodeCombination(
                    KeyCode.Q,
                    KeyCombination.CONTROL_DOWN,
                    KeyCombination.SHIFT_DOWN
            );

            public void handle(KeyEvent key) {
                if (keyComb.match(key)) {
                    Points.points = 0;
                    Hearts.heartCounter = 3;
                    eggsAnimation.cancel();
                    stage.close();
                    try {
                        new Menu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //==============================================================================================================

        scene.getStylesheets().add("Style.css");

        stage.setTitle("Nu pogodi!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
