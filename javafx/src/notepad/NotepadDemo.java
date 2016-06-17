package notepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotepadDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("notepad.fxml"));
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(NotepadDemo.class.getResource("notepad.css").toExternalForm());
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Notepad Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
