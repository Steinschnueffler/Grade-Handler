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
import schueler.SchuelerException;

public class MenuButtonSceneController {

	private Stage notenStage = null;
	
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
    	if(notenStage.isShowing()) notenStage.toFront();
    	else notenStage.show();
    	notenStage.centerOnScreen();
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
    		FXMLLoader loader = new FXMLLoader(MenuButtonSceneController.class.getResource("MenuButtonScene.fxml"));
			Parent root = loader.load();
			MenuButtonSceneController mbsc = loader.getController();
			mbsc.fachName.setText(f.getName());
			mbsc.hintergrundBild.setImage(Main.workspace.menuButton_hintergrund);
			
			mbsc.notenStage = new Stage();
			mbsc.notenStage.setScene(NotenSceneController.getElement(f));
			mbsc.notenStage.initOwner(Main.stage);
			mbsc.notenStage.sizeToScene();
			mbsc.notenStage.setResizable(false);
			mbsc.notenStage.setTitle(f.getName() + " - " +Main.schueler.getName() + " - Notenmanager");
			mbsc.notenStage.setOnCloseRequest(e -> {
	    		try {
					Main.workspace.saveSchueler();
				} catch (SchuelerException ex) {
					Main.workspace.writeException(ex);
					Main.showAlert("Can't save schueler", ex);
				}
	    	});
		
			return root;
		} catch (IOException e) {
			Main.workspace.writeException(e);
			Main.showAlert("Can't create Menubutton", e);
			Main.stage.setScene(LoginSceneController.getElement());
			return new AnchorPane();
		}
    }
}


