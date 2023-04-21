import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        new Menu();
        if(!Gamer.gamers.isEmpty()) {
            Gamer.loadGamers();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
