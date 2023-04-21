import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Menu{

    public Menu() throws Exception {
        Stage stage = new Stage();

        StackPane stackPane = new StackPane();

        InputStream stream = new FileInputStream("./src/PNG/nu_pogodi.png");
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);

        //=========================================== New Game =========================================================

        Button newGame = new Button("New Game");
        GridPane.setHalignment(newGame, HPos.CENTER);
        gridPane.add(newGame,0,0);
        newGame.setOnAction(e -> {
            stage.close();
            try {
                new NewGame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //=========================================== High Scores ======================================================

        Button highScores = new Button("High Scores");
        GridPane.setHalignment(highScores, HPos.CENTER);
        gridPane.add(highScores,0,1);
        highScores.setOnAction(e -> {
            stage.close();
            try {
                new HighScores();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //============================================== Exit ==========================================================

        Button exit = new Button("Exit");
        GridPane.setHalignment(exit, HPos.CENTER);
        gridPane.add(exit,0,2);
        exit.setOnAction(e -> {
            stage.close();
        });

        stackPane.getChildren().addAll(imageView,gridPane);

        stage.setTitle("Nu pogodi!");
        Scene scene = new Scene(stackPane, 500, 500);
        scene.getStylesheets().add("Style.css");

        stage.setScene(scene);
        stage.show();

    }

}
