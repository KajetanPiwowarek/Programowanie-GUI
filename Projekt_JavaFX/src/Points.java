import javafx.scene.control.Label;

import java.text.DecimalFormat;

public class Points {
    public static Label pointsLabel;
    public static int points;
    public static String pointsText;

    public Points(){
        DecimalFormat dFormat = new DecimalFormat("0000");
        pointsText = dFormat.format(points);
        pointsLabel = new Label(Points.pointsText);
        pointsLabel.setLayoutX(620);
        pointsLabel.setLayoutY(130);
    }

    public static void pointsUpdate(){
        DecimalFormat dFormat = new DecimalFormat("0000");
        pointsText = dFormat.format(points);
        pointsLabel = new Label(Points.pointsText);
        pointsLabel.setLayoutX(620);
        pointsLabel.setLayoutY(130);
    }
}
