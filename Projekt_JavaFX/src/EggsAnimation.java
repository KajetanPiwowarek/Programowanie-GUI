import javafx.animation.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EggsAnimation extends Service<Void> {
    public Pane pane;
    public Stage stage;

    public EggsAnimation(Pane pane, Stage stage) {
        this.pane = pane;
        this.stage = stage;
    }

    @Override
    public Task createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {

//================================================= LeftDown ===========================================================

                pane.getChildren().add(Eggs.EggLeftDown);

                FadeTransition fadeTransition1LeftDown = new FadeTransition(Duration.seconds(1), Eggs.EggLeftDown);
                fadeTransition1LeftDown.setFromValue(0);
                fadeTransition1LeftDown.setToValue(100);
                fadeTransition1LeftDown.setCycleCount(1);

                RotateTransition rotateTransitionEggLeftDown = new RotateTransition(Duration.seconds(10), Eggs.EggLeftDown);
                rotateTransitionEggLeftDown.setFromAngle(0);
                rotateTransitionEggLeftDown.setToAngle(360);
                rotateTransitionEggLeftDown.setCycleCount(1);

                PathTransition pathTransitionEggLeftDown = new PathTransition();
                Path pathLeftDown = new Path();
                pathLeftDown.getElements().add(new MoveTo(200, 300));
                pathLeftDown.getElements().add(new LineTo(280, 345));
                pathTransitionEggLeftDown.setDuration(Duration.seconds(10));
                pathTransitionEggLeftDown.setNode(Eggs.EggLeftDown);
                pathTransitionEggLeftDown.setPath(pathLeftDown);
                pathTransitionEggLeftDown.setCycleCount(1);

                FadeTransition fadeTransition2LeftDown = new FadeTransition(Duration.seconds(0.25), Eggs.EggLeftDown);
                fadeTransition2LeftDown.setFromValue(100);
                fadeTransition2LeftDown.setToValue(0);
                fadeTransition2LeftDown.setCycleCount(1);

                ParallelTransition parallelTransitionLeftDown = new ParallelTransition();
                parallelTransitionLeftDown.getChildren().addAll(rotateTransitionEggLeftDown, pathTransitionEggLeftDown);
                parallelTransitionLeftDown.setCycleCount(1);

                SequentialTransition sequentialTransitionLeftDown = new SequentialTransition();
                sequentialTransitionLeftDown.getChildren().addAll(fadeTransition1LeftDown, parallelTransitionLeftDown, fadeTransition2LeftDown);
                sequentialTransitionLeftDown.setCycleCount(1);
                sequentialTransitionLeftDown.setDelay(Duration.seconds(4));
                sequentialTransitionLeftDown.play();

//================================================= LeftUp =============================================================

                pane.getChildren().add(Eggs.EggLeftUp);

                FadeTransition fadeTransition1LeftUp = new FadeTransition(Duration.seconds(1), Eggs.EggLeftUp);
                fadeTransition1LeftUp.setFromValue(0);
                fadeTransition1LeftUp.setToValue(100);
                fadeTransition1LeftUp.setCycleCount(1);

                RotateTransition rotateTransitionEggLeftUp = new RotateTransition(Duration.seconds(12), Eggs.EggLeftUp);
                rotateTransitionEggLeftUp.setFromAngle(0);
                rotateTransitionEggLeftUp.setToAngle(360);
                rotateTransitionEggLeftUp.setCycleCount(1);

                PathTransition pathTransitionEggLeftUp = new PathTransition();
                Path pathLeftUp = new Path();
                pathLeftUp.getElements().add(new MoveTo(200, 210));
                pathLeftUp.getElements().add(new LineTo(280, 255));
                pathTransitionEggLeftUp.setDuration(Duration.seconds(12));
                pathTransitionEggLeftUp.setNode(Eggs.EggLeftUp);
                pathTransitionEggLeftUp.setPath(pathLeftUp);
                pathTransitionEggLeftUp.setCycleCount(1);

                FadeTransition fadeTransition2LeftUp = new FadeTransition(Duration.seconds(0.25), Eggs.EggLeftUp);
                fadeTransition2LeftUp.setFromValue(100);
                fadeTransition2LeftUp.setToValue(0);
                fadeTransition2LeftUp.setCycleCount(1);

                ParallelTransition parallelTransitionLeftUp = new ParallelTransition();
                parallelTransitionLeftUp.getChildren().addAll(rotateTransitionEggLeftUp, pathTransitionEggLeftUp);
                parallelTransitionLeftUp.setCycleCount(1);

                SequentialTransition sequentialTransitionLeftUp = new SequentialTransition();
                sequentialTransitionLeftUp.getChildren().addAll(fadeTransition1LeftUp, parallelTransitionLeftUp, fadeTransition2LeftUp);
                sequentialTransitionLeftUp.setCycleCount(1);
                sequentialTransitionLeftUp.setDelay(Duration.seconds(8));
                sequentialTransitionLeftUp.play();

//================================================= RightDown ==========================================================

                pane.getChildren().add(Eggs.EggRightDown);

                FadeTransition fadeTransition1RightDown = new FadeTransition(Duration.seconds(1), Eggs.EggRightDown);
                fadeTransition1RightDown.setFromValue(0);
                fadeTransition1RightDown.setToValue(100);
                fadeTransition1RightDown.setCycleCount(1);

                RotateTransition rotateTransitionEggRightDown = new RotateTransition(Duration.seconds(10), Eggs.EggRightDown);
                rotateTransitionEggRightDown.setFromAngle(360);
                rotateTransitionEggRightDown.setToAngle(0);
                rotateTransitionEggRightDown.setCycleCount(1);

                PathTransition pathTransitionEggRightDown = new PathTransition();
                Path pathRightDown = new Path();
                pathRightDown.getElements().add(new MoveTo(700, 300));
                pathRightDown.getElements().add(new LineTo(635, 330));
                pathTransitionEggRightDown.setDuration(Duration.seconds(10));
                pathTransitionEggRightDown.setNode(Eggs.EggRightDown);
                pathTransitionEggRightDown.setPath(pathRightDown);
                pathTransitionEggRightDown.setCycleCount(1);

                FadeTransition fadeTransition2RightDown = new FadeTransition(Duration.seconds(0.25), Eggs.EggRightDown);
                fadeTransition2RightDown.setFromValue(100);
                fadeTransition2RightDown.setToValue(0);
                fadeTransition2RightDown.setCycleCount(1);

                ParallelTransition parallelTransitionRightDown = new ParallelTransition();
                parallelTransitionRightDown.getChildren().addAll(rotateTransitionEggRightDown, pathTransitionEggRightDown);
                parallelTransitionRightDown.setCycleCount(1);

                SequentialTransition sequentialTransitionRightDown = new SequentialTransition();
                sequentialTransitionRightDown.getChildren().addAll(fadeTransition1RightDown, parallelTransitionRightDown, fadeTransition2RightDown);
                sequentialTransitionRightDown.setCycleCount(1);
                sequentialTransitionRightDown.setDelay(Duration.seconds(12));
                sequentialTransitionRightDown.play();

//================================================= RightUp ============================================================

                pane.getChildren().add(Eggs.EggRightUp);

                FadeTransition fadeTransition1RightUp = new FadeTransition(Duration.seconds(1), Eggs.EggRightUp);
                fadeTransition1RightUp.setFromValue(0);
                fadeTransition1RightUp.setToValue(100);
                fadeTransition1RightUp.setCycleCount(1);

                RotateTransition rotateTransitionEggRightUp = new RotateTransition(Duration.seconds(12), Eggs.EggRightUp);
                rotateTransitionEggRightUp.setFromAngle(360);
                rotateTransitionEggRightUp.setToAngle(0);
                rotateTransitionEggRightUp.setCycleCount(1);

                PathTransition pathTransitionEggRightUp = new PathTransition();
                Path pathRightUp = new Path();
                pathRightUp.getElements().add(new MoveTo(700, 205));
                pathRightUp.getElements().add(new LineTo(635, 245));
                pathTransitionEggRightUp.setDuration(Duration.seconds(12));
                pathTransitionEggRightUp.setNode(Eggs.EggRightUp);
                pathTransitionEggRightUp.setPath(pathRightUp);
                pathTransitionEggRightUp.setCycleCount(1);

                FadeTransition fadeTransition2RightUp = new FadeTransition(Duration.seconds(0.25), Eggs.EggRightUp);
                fadeTransition2RightUp.setFromValue(100);
                fadeTransition2RightUp.setToValue(0);
                fadeTransition2RightUp.setCycleCount(1);

                ParallelTransition parallelTransitionRightUp = new ParallelTransition();
                parallelTransitionRightUp.getChildren().addAll(rotateTransitionEggRightUp, pathTransitionEggRightUp);
                parallelTransitionRightUp.setCycleCount(1);

                SequentialTransition sequentialTransitionRightUp = new SequentialTransition();
                sequentialTransitionRightUp.getChildren().addAll(fadeTransition1RightUp, parallelTransitionRightUp, fadeTransition2RightUp);
                sequentialTransitionRightUp.setCycleCount(1);
                sequentialTransitionRightUp.setDelay(Duration.seconds(16));
                sequentialTransitionRightUp.play();

//================================================= Conditions =========================================================

                sequentialTransitionLeftDown.setOnFinished(e->{
                    if (Wolfs.isWolfLeftDown) {
                        Points.points += 5;
                        pane.getChildren().remove(Points.pointsLabel);
                        Points.pointsUpdate();
                        pane.getChildren().add(Points.pointsLabel);
                    } else {
                        Node CrackedEggLeft = CrackedEgg.getCrackedEggLeft();
                        pane.getChildren().add(CrackedEggLeft);

                        FadeTransition fadeTransition3LeftDown = new FadeTransition(Duration.seconds(5), CrackedEggLeft);
                        fadeTransition3LeftDown.setFromValue(100);
                        fadeTransition3LeftDown.setToValue(0);
                        fadeTransition3LeftDown.setCycleCount(1);
                        fadeTransition3LeftDown.play();

                        switch (Hearts.heartCounter) {
                            case 0 -> {
                                sequentialTransitionLeftDown.stop();
                                sequentialTransitionLeftUp.stop();
                                sequentialTransitionRightDown.stop();
                                sequentialTransitionRightUp.stop();
                                stage.close();
                                try {
                                    new EndGame();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                            case 1 -> {
                                pane.getChildren().remove(Hearts.Heart2);
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart1);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 2 -> {
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart2);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 3 -> {
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart3);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                        }
                    }
                    sequentialTransitionLeftDown.playFromStart();
                });

                sequentialTransitionLeftUp.setOnFinished(e-> {
                    if (Wolfs.isWolfLeftUp) {
                        Points.points += 5;
                        pane.getChildren().remove(Points.pointsLabel);
                        Points.pointsUpdate();
                        pane.getChildren().add(Points.pointsLabel);
                    } else {
                        Node CrackedEggLeft = CrackedEgg.getCrackedEggLeft();
                        pane.getChildren().add(CrackedEggLeft);

                        FadeTransition fadeTransition3LeftUp = new FadeTransition(Duration.seconds(5), CrackedEggLeft);
                        fadeTransition3LeftUp.setFromValue(100);
                        fadeTransition3LeftUp.setToValue(0);
                        fadeTransition3LeftUp.setCycleCount(1);
                        fadeTransition3LeftUp.play();

                        switch (Hearts.heartCounter) {
                            case 0 -> {
                                sequentialTransitionLeftDown.stop();
                                sequentialTransitionLeftUp.stop();
                                sequentialTransitionRightDown.stop();
                                sequentialTransitionRightUp.stop();
                                stage.close();
                                try {
                                    new EndGame();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                            case 1 -> {
                                pane.getChildren().remove(Hearts.Heart2);
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart1);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 2 -> {
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart2);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 3 -> {
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart3);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                        }
                    }
                    sequentialTransitionLeftUp.playFromStart();
                });

                sequentialTransitionRightDown.setOnFinished(e->{
                    if (Wolfs.isWolfRightDown) {
                        Points.points += 5;
                        pane.getChildren().remove(Points.pointsLabel);
                        Points.pointsUpdate();
                        pane.getChildren().add(Points.pointsLabel);
                    } else {
                        Node CrackedEggRight = CrackedEgg.getCrackedEggRight();
                        pane.getChildren().add(CrackedEggRight);

                        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(5), CrackedEggRight);
                        fadeTransition3.setFromValue(100);
                        fadeTransition3.setToValue(0);
                        fadeTransition3.setCycleCount(1);
                        fadeTransition3.play();

                        switch (Hearts.heartCounter) {
                            case 0 -> {
                                sequentialTransitionLeftDown.stop();
                                sequentialTransitionLeftUp.stop();
                                sequentialTransitionRightDown.stop();
                                sequentialTransitionRightUp.stop();
                                stage.close();
                                try {
                                    new EndGame();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                            case 1 -> {
                                pane.getChildren().remove(Hearts.Heart2);
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart1);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 2 -> {
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart2);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 3 -> {
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart3);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                        }
                    }
                    sequentialTransitionRightDown.playFromStart();
                });

                sequentialTransitionRightUp.setOnFinished(e->{
                    if (Wolfs.isWolfRightUp) {
                        Points.points += 5;
                        pane.getChildren().remove(Points.pointsLabel);
                        Points.pointsUpdate();
                        pane.getChildren().add(Points.pointsLabel);
                    } else {
                        Node CrackedEggRight = CrackedEgg.getCrackedEggRight();
                        pane.getChildren().add(CrackedEggRight);

                        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(5), CrackedEggRight);
                        fadeTransition3.setFromValue(100);
                        fadeTransition3.setToValue(0);
                        fadeTransition3.setCycleCount(1);
                        fadeTransition3.play();

                        switch (Hearts.heartCounter) {
                            case 0 -> {
                                sequentialTransitionLeftDown.stop();
                                sequentialTransitionLeftUp.stop();
                                sequentialTransitionRightDown.stop();
                                sequentialTransitionRightUp.stop();
                                stage.close();
                                try {
                                    new EndGame();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                            case 1 -> {
                                pane.getChildren().remove(Hearts.Heart2);
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart1);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 2 -> {
                                pane.getChildren().remove(Hearts.Heart3);
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart2);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                            case 3 -> {
                                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), Hearts.Heart3);
                                fadeTransition.setFromValue(100);
                                fadeTransition.setToValue(0);
                                fadeTransition.setCycleCount(1);
                                fadeTransition.play();
                                Hearts.heartCounter--;
                            }
                        }
                    }
                    sequentialTransitionRightUp.playFromStart();
                });

                return null;
            }
        };
    }
}