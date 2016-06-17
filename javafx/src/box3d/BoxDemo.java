package box3d;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BoxDemo extends Application {

    private Timeline animation;

    private void init(Stage primaryStage) {
        Group root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 800, true));
        primaryStage.getScene().setCamera(new PerspectiveCamera());
        root.getTransforms().addAll(
                new Translate(800 / 2, 800 / 2),
                new Rotate(180, Rotate.X_AXIS)
        );
        root.getChildren().add(create3dContent());
    }

    private Node create3dContent() {
        Cube cube = new Cube(300, Color.RED, 1);
        cube.rx.setAngle(45);
        cube.ry.setAngle(45);
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(cube.ry.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(cube.ry.angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(cube);
    }

    private void play() {
        animation.play();
    }

    @Override
    public void stop() {
        animation.pause();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}