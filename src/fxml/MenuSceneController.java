/**
 * Sample Skeleton for 'MenuScene.fxml' Controller Class
 */

package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MenuSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="box"
    private VBox box; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert box != null : "fx:id=\"box\" was not injected: check your FXML file 'MenuScene.fxml'.";
    }
}

