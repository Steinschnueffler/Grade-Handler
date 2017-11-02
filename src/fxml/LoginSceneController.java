package fxml;
/**
 * Sample Skeleton for 'LoginScene.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import schueler.Fach;
import schueler.SchuelerException;

public class LoginSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="passwortInput"
    private PasswordField passwortInput; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="nameInput"
    private TextField nameInput; // Value injected by FXMLLoader

    @FXML // fx:id="registerLink"
    private Label registerLink; // Value injected by FXMLLoader

    @FXML
    void enterGedrückt(ActionEvent event) {
    	loginButtonClicked(event);
    }
    
    @FXML
    void loginButtonClicked(ActionEvent event) {
    	if(nameInput.getText().trim().length() == 0) {
    		Main.stage.setScene(NotenSceneController.getElement(new Fach("Nicht eingeloggt")));
    		Main.stage.setTitle("Nicht eingeloggt - Notenmanager");
    		Main.schueler = null;
    		return;
    	}
    	try {
			Main.schueler = Main.workspace.loadSchueler(nameInput.getText().trim());
		} catch (SchuelerException e) {
			Main.showAlert("Can't load schueler: \n" + e.getLocalizedMessage(), e);
			return;
		}
    	if(!Main.schueler.passwordEquals(passwortInput.getText())) {
    		Main.showAlert("Wrong password");
    		return;
    	}
    	Main.stage.setTitle(Main.schueler.getName() + " - Notenmanager");
    	Main.stage.setScene(MenuSceneController.getElement());
    }

    @FXML
    void registerLinkClicked() {
    	Main.stage.setTitle("Registrieren - Notenmanager");
    	Main.stage.setScene(RegisterSceneController.getElement());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert passwortInput != null : "fx:id=\"passwortInput\" was not injected: check your FXML file 'LoginScene.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginScene.fxml'.";
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'LoginScene.fxml'.";
        assert registerLink != null : "fx:id=\"registerLink\" was not injected: check your FXML file 'LoginScene.fxml'.";

    }
    
    public static Scene getElement() {
    	try {
			Parent root = FXMLLoader.load(LoginSceneController.class.getResource("LoginScene.fxml"));
			Scene s = new Scene(root);
			return s;
		} catch (IOException e) {
			e.printStackTrace();
			return new Scene(new AnchorPane());
		}
    }
}

