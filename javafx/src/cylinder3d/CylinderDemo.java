package cylinder3d;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;


public class CylinderDemo extends Application {
    @Override
    public void start(Stage stage) {
        if (!Platform.isSupported(ConditionalFeature.SCENE3D)) {
            throw new RuntimeException(
                    "*** ERROR: common conditional SCENE3D is not supported");
        }

        stage.setTitle("Cylinder 3D Demo");

        Group root = new Group();
        Scene scene = new Scene(root, 800, 800);
        scene.setFill(Color.color(0.5, 0.5, 0.5, 1.0));

        HBox hbox = new HBox();
        hbox.setLayoutX(260);
        hbox.setLayoutY(100);

        PhongMaterial phongMaterial = new PhongMaterial(Color.color(0, 1, 1));

        Cylinder cylinder = new Cylinder(100, 200);
        cylinder.setMaterial(phongMaterial);
        SubScene subScene = createSubScene(cylinder, Color.TRANSPARENT,
                new PerspectiveCamera());
        hbox.getChildren().add(subScene);

        Slider slider = new Slider(0, 360, 0);
        slider.setBlockIncrement(1);
        slider.setTranslateX(375);
        slider.setTranslateY(625);
        cylinder.rotateProperty().bind(slider.valueProperty());
        root.getChildren().addAll(hbox, slider);

        stage.setScene(scene);
        stage.show();
    }

    private static SubScene createSubScene(Node node,
                                           Paint fillPaint, Camera camera) {
        Group root = new Group();

        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);

        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));
        node.setRotationAxis(new Point3D(2, 1, 0).normalize());
        node.setTranslateX(180);
        node.setTranslateY(180);
        root.getChildren().addAll(ambientLight, light, node);

        SubScene subScene = new SubScene(root, 600, 400, true, SceneAntialiasing.BALANCED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);

        return subScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
