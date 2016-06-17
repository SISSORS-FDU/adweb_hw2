package notepad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;

public class NotepadController {
    @FXML
    private AnchorPane layoutPane;
    @FXML
    private TextArea fileContent;
    private File result;

    @FXML
    private void onFileOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        result = fileChooser.showOpenDialog(layoutPane.getScene().getWindow());
        if (result != null) {
            fileContent.setText(FileHandler.readFile(result));
        }
    }
    @FXML
    private void onFileSave(ActionEvent event) {
        if(result != null){
            FileHandler.writeFile(result, fileContent.getText());
        }
    }
    @FXML
    private void onFileClose(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private void onMenuAbout(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "这是一款基于JavaFX开发的记事本。", "关于", JOptionPane.PLAIN_MESSAGE);
    }
    @FXML
    private void onContextSelectAll(ActionEvent event) {
        fileContent.selectAll();
    }
}
