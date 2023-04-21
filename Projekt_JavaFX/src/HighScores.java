import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class HighScores {

    public HighScores() throws Exception{

        Stage stage = new Stage();

        StackPane stackPane = new StackPane();

        BorderPane borderPane = new BorderPane();

        //=========================================== Background =======================================================

        InputStream stream1 = new FileInputStream("./src/PNG/BackgroundImage.png");
        Image image1 = new Image(stream1);
        ImageView Background = new ImageView();
        Background.setImage(image1);
        Background.setX(0);
        Background.setY(0);
        Background.setFitWidth(1200);
        Background.setFitHeight(600);
        Background.setPreserveRatio(true);

        //================================================= Label + Button =============================================

        GridPane gridPaneTop = new GridPane();
        gridPaneTop.setAlignment(Pos.CENTER);
        gridPaneTop.setHgap(20);
        gridPaneTop.setVgap(20);

        StackPane buttonStackPane = new StackPane();

        Label label = new Label("High Scores");

        Button returnButton = new Button("");
        returnButton.setShape(new Circle(25));
        returnButton.setMinSize(50, 50);
        returnButton.setMaxSize(50, 50);
        returnButton.setOnAction(e -> {
            stage.close();
            try {
                new Menu();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        InputStream stream2 = new FileInputStream("./src/PNG/ReturnArrow.png");
        Image image2 = new Image(stream2);
        ImageView ReturnArrow = new ImageView();
        ReturnArrow.setImage(image2);
        ReturnArrow.setX(0);
        ReturnArrow.setY(0);
        ReturnArrow.setFitWidth(40);
        ReturnArrow.setFitHeight(40);
        ReturnArrow.setPreserveRatio(true);

        buttonStackPane.getChildren().addAll(returnButton,ReturnArrow);

        gridPaneTop.add(label,1,0);
        gridPaneTop.add(buttonStackPane,0,0);

        //================================================== List ======================================================

        GridPane gridPaneCenter = new GridPane();
        gridPaneCenter.setAlignment(Pos.CENTER);

        ListView listView = new ListView();
        listView.setPrefSize(300,400);
        listView.setItems(Gamer.gamers);

        ScrollPane scrollPane = new ScrollPane(listView);

        gridPaneCenter.add(scrollPane,0,0);

        borderPane.setTop(gridPaneTop);
        borderPane.setCenter(gridPaneCenter);

        stackPane.getChildren().addAll(Background, borderPane);

        Scene scene = new Scene(stackPane, 500, 500);
        scene.getStylesheets().add("Style.css");

        stage.setTitle("Nu pogodi!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
