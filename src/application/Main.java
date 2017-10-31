package application;
	
import fxml.LoginSceneController;
import javafx.application.Application;
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
}
