/**
 * Sample Skeleton for 'MenuButtonScene.fxml' Controller Class
 */

package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MenuButttonSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="einstellungenButton"
    private ImageView einstellungenButton; // Value injected by FXMLLoader

    @FXML // fx:id="hintergrundBild"
    private ImageView hintergrundBild; // Value injected by FXMLLoader

    @FXML // fx:id="fachName"
    private Label fachName; // Value injected by FXMLLoader

    @FXML
    void mouseExited(MouseEvent event) {
    }

    @FXML
    void mouseOver(MouseEvent event) {
    }

    @FXML
    void mousePressed(MouseEvent event) {
    }

    @FXML
    void mouseReleased(MouseEvent event) {
    }

    @FXML
    void einstellungenMouseExited(MouseEvent event) {
    }

    @FXML
    void einstellungenMouseOver(MouseEvent event) {

    }

    @FXML
    void einstellungenMousePressed(MouseEvent event) {

    }

    @FXML
    void einstellungenMouseReleased(MouseEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert einstellungenButton != null : "fx:id=\"einstellungenButton\" was not injected: check your FXML file 'MenuButtonScene.fxml'.";
        assert hintergrundBild != null : "fx:id=\"hintergrundBild\" was not injected: check your FXML file 'MenuButtonScene.fxml'.";
        assert fachName != null : "fx:id=\"fachName\" was not injected: check your FXML file 'MenuButtonScene.fxml'.";

    }
}


