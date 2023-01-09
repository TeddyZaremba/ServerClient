import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIClient extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/startScreen.fxml"));
		
		primaryStage.setTitle("Client");
		
		Scene scene = new Scene(root, 700,700);
		scene.getStylesheets().add("/styles/style1.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
