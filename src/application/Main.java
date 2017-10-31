package application;
	
import java.io.PrintWriter;
import java.io.StringWriter;

import fxml.LoginSceneController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage stage;
	public static Workspace workspace = Workspace.getDefault();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.stage = primaryStage;
			Main.stage.setScene(LoginSceneController.getElement());
			Main.stage.setResizable(false);
			Main.stage.centerOnScreen();
			Main.stage.setTitle("Login - Notenmanager");
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void showAlert(String info) {
		showAlert(info, null);
	}
	
	public static void showAlert(String info, Throwable cause) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(info);
		alert.initOwner(stage);
		if(cause != null) {
			alert.setContentText("Stacktrace:");
			TextArea ta = new TextArea();
			ta.setEditable(false);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			cause.printStackTrace(pw);
			ta.setText(sw.toString());
			pw.close();
			alert.getDialogPane().setExpandableContent(ta);
		}
		alert.showAndWait();
	}
}
