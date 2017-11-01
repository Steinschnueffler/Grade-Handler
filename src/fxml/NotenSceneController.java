/**
 * Sample Skeleton for 'NotenScene.fxml' Controller Class
 */

package fxml;

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
import javafx.scene.control.TextField;
import schueler.Fach;

public class NotenSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="schulaufgabenOk"
    private Button schulaufgabenOk; // Value injected by FXMLLoader

    @FXML // fx:id="kurzarbeitenInput"
    private TextField kurzarbeitenInput; // Value injected by FXMLLoader

    @FXML // fx:id="schulaufgabenInput"
    private TextField schulaufgabenInput; // Value injected by FXMLLoader

    @FXML // fx:id="normalNoten"
    private Label normalNoten; // Value injected by FXMLLoader

    @FXML // fx:id="normalOk"
    private Button normalOk; // Value injected by FXMLLoader

    @FXML // fx:id="normalBearbeitenButton"
    private Button normalBearbeitenButton; // Value injected by FXMLLoader

    @FXML // fx:id="fachName"
    private Label fachName; // Value injected by FXMLLoader

    @FXML // fx:id="schulaufgabenNoten"
    private Label schulaufgabenNoten; // Value injected by FXMLLoader

    @FXML // fx:id="kurzarbeitenBearbeitenButton"
    private Button kurzarbeitenBearbeitenButton; // Value injected by FXMLLoader

    @FXML // fx:id="kurzarbeitenOk"
    private Button kurzarbeitenOk; // Value injected by FXMLLoader

    @FXML // fx:id="schulaufgabenBearbeitenButton"
    private Button schulaufgabenBearbeitenButton; // Value injected by FXMLLoader

    @FXML // fx:id="normalInput"
    private TextField normalInput; // Value injected by FXMLLoader

    @FXML // fx:id="kurzarbeitenEinzelDurchschnitt"
    private Label kurzarbeitenEinzelDurchschnitt; // Value injected by FXMLLoader

    @FXML // fx:id="geasmtDurchschnitt"
    private Label geasmtDurchschnitt; // Value injected by FXMLLoader

    @FXML // fx:id="normalEinzelDurchschnitt"
    private Label normalEinzelDurchschnitt; // Value injected by FXMLLoader

    @FXML // fx:id="kurzarbeitenNoten"
    private Label kurzarbeitenNoten; // Value injected by FXMLLoader

    @FXML // fx:id="shulaufgabenEinzelDurchschnitt"
    private Label shulaufgabenEinzelDurchschnitt; // Value injected by FXMLLoader

    @FXML
    void normalEingegeben(ActionEvent event) {

    }

    @FXML
    void normalOkPressed(ActionEvent event) {

    }

    @FXML
    void normalBearbeitenButtonPressed(ActionEvent event) {

    }

    @FXML
    void kurzarbeitenEingegeben(ActionEvent event) {

    }

    @FXML
    void kurzarbeitenOkPressed(ActionEvent event) {

    }

    @FXML
    void kurzarbeitenBearbeitenButtonPressed(ActionEvent event) {

    }

    @FXML
    void schulaufgabenEingegeben(ActionEvent event) {

    }

    @FXML
    void schulaufgabenOkPressed(ActionEvent event) {

    }

    @FXML
    void schulaufgabenBearbeitenButtonPressed(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert schulaufgabenOk != null : "fx:id=\"schulaufgabenOk\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert kurzarbeitenInput != null : "fx:id=\"kurzarbeitenInput\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert schulaufgabenInput != null : "fx:id=\"schulaufgabenInput\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert normalNoten != null : "fx:id=\"normalNoten\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert normalOk != null : "fx:id=\"normalOk\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert normalBearbeitenButton != null : "fx:id=\"normalBearbeitenButton\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert fachName != null : "fx:id=\"fachName\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert schulaufgabenNoten != null : "fx:id=\"schulaufgabenNoten\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert kurzarbeitenBearbeitenButton != null : "fx:id=\"kurzarbeitenBearbeitenButton\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert kurzarbeitenOk != null : "fx:id=\"kurzarbeitenOk\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert schulaufgabenBearbeitenButton != null : "fx:id=\"schulaufgabenBearbeitenButton\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert normalInput != null : "fx:id=\"normalInput\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert kurzarbeitenEinzelDurchschnitt != null : "fx:id=\"kurzarbeitenEinzelDurchschnitt\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert geasmtDurchschnitt != null : "fx:id=\"geasmtDurchschnitt\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert normalEinzelDurchschnitt != null : "fx:id=\"normalEinzelDurchschnitt\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert kurzarbeitenNoten != null : "fx:id=\"kurzarbeitenNoten\" was not injected: check your FXML file 'NotenScene.fxml'.";
        assert shulaufgabenEinzelDurchschnitt != null : "fx:id=\"shulaufgabenEinzelDurchschnitt\" was not injected: check your FXML file 'NotenScene.fxml'.";
    }
    
    public static Scene getElement(Fach f) {
    	try {
    		FXMLLoader loader = new FXMLLoader(NotenSceneController.class.getResource("NotenScene.fxml"));
			Parent root = loader.load();
			NotenSceneController nsc = loader.getController();
			nsc.fachName.setText(f.getName());
			nsc.geasmtDurchschnitt.setText("" +f.gesamtDurchschnitt());
			nsc.normalEinzelDurchschnitt.setText("" +f.normaleDurchschnitt());
			nsc.shulaufgabenEinzelDurchschnitt.setText("" +f.schulaufgabenDurchschnitt());
			return new Scene(root);
		} catch (IOException e) {
			Main.workspace.writeException(e);
			Main.showAlert("Can't load Window", e);
			return null;
		}
    }
}
