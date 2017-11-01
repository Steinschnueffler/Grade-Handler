/**
 * Sample Skeleton for 'MenuButtonScene.fxml' Controller Class
 */

package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import schueler.Fach;

public class MenuButttonSceneController {

	private Fach f;
	
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
    	hintergrundBild.setImage(Main.workspace.menuButton_hintergrund);
    }

    @FXML
    void mouseOver(MouseEvent event) {
    	hintergrundBild.setImage(Main.workspace.menuButton_hintergrund_ausgewählt);
    }

    @FXML
    void mousePressed(MouseEvent event) {
    	Stage s = new Stage();
    	s.setScene(NotenSceneController.getElement(f));
    	s.initOwner(Main.stage);
    	s.show();
    	hintergrundBild.setImage(Main.workspace.menuButton_hintergrund_gedrückt);
    }

    @FXML
    void mouseReleased(MouseEvent event) {
    	if(hintergrundBild.isHover())
    		hintergrundBild.setImage(Main.workspace.menuButton_hintergrund_ausgewählt);
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
    
    public static Node getElement(Fach f) {
    	try {
    		FXMLLoader loader = new FXMLLoader(MenuButttonSceneController.class.getResource("MenuButtonScene.fxml"));
			Parent root = loader.load();
			MenuButttonSceneController mbsc = loader.getController();
			mbsc.f = f;
			mbsc.fachName.setText(f.getName());
			mbsc.hintergrundBild.setImage(Main.workspace.menuButton_hintergrund);
			return root;
		} catch (IOException e) {
			Main.workspace.writeException(e);
			Main.showAlert("Can't create Menubutton", e);
			Main.stage.setScene(LoginSceneController.getElement());
			return new AnchorPane();
		}
    }
}


