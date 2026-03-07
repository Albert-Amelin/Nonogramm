package nonogramm;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class NonogrammController {
    @FXML
    private Label label;

    @FXML
    private void handleButtonClick(){
        System.out.println("Button clicked");
    }
}
