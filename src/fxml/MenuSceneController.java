/**
 * Sample Skeleton for 'MenuScene.fxml' Controller Class
 */

package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import schueler.Schueler;

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
    
    public static Scene getElement(Schueler s) {
    	try {
			Parent root = FXMLLoader.load(MenuSceneController.class.getResource("MenuScene.fxml"));
			Scene scene = new Scene(root);
			return scene;
		} catch (IOException e) {
			return new Scene(new AnchorPane());
		}
    }
}

