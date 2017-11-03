package fxml;
/**
 * Sample Skeleton for 'RegisterScene.fxml' Controller Class
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
import schueler.Schueler;
import schueler.SchuelerException;

public class RegisterSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginLink"
    private Label loginLink; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwortInput1"
    private PasswordField passwortInput1; // Value injected by FXMLLoader

    @FXML // fx:id="passwortInput2"
    private PasswordField passwortInput2; // Value injected by FXMLLoader

    @FXML // fx:id="nameInput"
    private TextField nameInput; // Value injected by FXMLLoader

    @FXML 
    void enter1Gedrückt(ActionEvent event) {
    	passwortInput1.requestFocus();
    }
    
    @FXML 
    void enter2Gedrückt(ActionEvent event) {
    	passwortInput2.requestFocus();
    }
    
    @FXML 
    void enter3Gedrückt(ActionEvent event) {
    	registerButtonClicked(event);
    }
    
    @FXML
    void registerButtonClicked(ActionEvent event) {
    	String name = nameInput.getText().trim();
    	String password = passwortInput1.getText();
    	if(name.length() == 0) {
    		Main.showAlert("Name is to short. \n It must be at least 1 character.");
    		return;
    	}
    	if(password.length() < 5) {
    		Main.showAlert("Passwords is to short. \n It must be at least 5 chars.");
    		return;
    	}
    	if(!password.equals(passwortInput2.getText())) {
    		Main.showAlert("Passwords are not equal");
    		passwortInput2.setText("");
    		return;
    	}
    	Main.schueler = new Schueler(name, password);
    	try {
			Main.workspace.saveNewSchueler();
		} catch (SchuelerException e) {
			Main.showAlert("Can't save schueler: " +e.getLocalizedMessage(), e);
			return;
		}
    	Main.stage.setTitle(Main.schueler.getName() + " - Notenmanager");
    	Main.stage.setScene(MenuSceneController.getElement());
    }

    @FXML
    void loginLinkClicked() {
    	Main.stage.setTitle("Login - Notenmanager");
    	Main.stage.setScene(LoginSceneController.getElement());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginLink != null : "fx:id=\"loginLink\" was not injected: check your FXML file 'RegisterScene.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'RegisterScene.fxml'.";
        assert passwortInput1 != null : "fx:id=\"passwortInput1\" was not injected: check your FXML file 'RegisterScene.fxml'.";
        assert passwortInput2 != null : "fx:id=\"passwortInput2\" was not injected: check your FXML file 'RegisterScene.fxml'.";
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'RegisterScene.fxml'.";
    }
    
    public static Scene getElement() {
    	try {
			Parent root = FXMLLoader.load(RegisterSceneController.class.getResource("RegisterScene.fxml"));
			Scene s = new Scene(root);
			return s;
    	} catch (IOException e) {
			e.printStackTrace();
			return new Scene(new AnchorPane());
		}
    }
}

