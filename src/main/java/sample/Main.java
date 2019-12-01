package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/***
 * This is the Main Class that starts the application
 * @author tszmoonhung
 *
 */
public class Main extends Application {
	/**
	 * Default constructor of Main
	 */
	public Main() {
		
	}
	
	/***
	 * This is the main entry point of the program
	 */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        primaryStage.setScene(new Scene(root, 630, 520));
        primaryStage.show();
        Arena appController = loader.getController();
        appController.createArena();
    }

    /**
     * If the JavaFx works properly,this method will be ignored.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
