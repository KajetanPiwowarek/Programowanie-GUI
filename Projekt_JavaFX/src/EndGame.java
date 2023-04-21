import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class EndGame {
    private String name;
    int score;
    public EndGame() throws Exception{

        Stage stage = new Stage();

        StackPane stackPane = new StackPane();

        Scene scene = new Scene(stackPane, 400, 400);

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

        //================================================= Label ======================================================

        GridPane gridPaneTop = new GridPane();
        gridPaneTop.setAlignment(Pos.CENTER);
        gridPaneTop.setHgap(30);
        gridPaneTop.setVgap(30);

        Label label = new Label("Game Over");
        GridPane.setHalignment(label, HPos.CENTER);

        final Label scoreLabel = new Label("Your Score: " + Points.points);
        GridPane.setHalignment(scoreLabel, HPos.CENTER);

        gridPaneTop.add(label,0,0);
        gridPaneTop.add(scoreLabel,0,1);

        //================================================== Name ======================================================

        GridPane gridPaneCenter = new GridPane();
        gridPaneCenter.setAlignment(Pos.CENTER);
        gridPaneCenter.setHgap(30);
        gridPaneCenter.setVgap(30);

        TextField textField = new TextField();
        GridPane.setHalignment(textField, HPos.CENTER);

        Button button = new Button("Menu");
        GridPane.setHalignment(button, HPos.CENTER);
        button.setOnAction(e -> {
            name = textField.getText();
            score = Points.points;
            try {
                Gamer.addGamer(name, score);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Points.points = 0;
            Hearts.heartCounter = 3;
            stage.close();
            try {
                new Menu();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        gridPaneCenter.add(textField,0,0);
        gridPaneCenter.add(button,0,1);

        borderPane.setTop(gridPaneTop);
        borderPane.setCenter(gridPaneCenter);

        stackPane.getChildren().addAll(Background, borderPane);

        scene.getStylesheets().add("Style.css");

        stage.setTitle("Nu pogodi!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
