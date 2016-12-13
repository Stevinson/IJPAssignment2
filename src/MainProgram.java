import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * The starting class of the application. Attempts to initialise the user interface as
 * "WorldViewer.fxml" (and sets its min size) and style it according to the provided 
 * CSS file ("fileCSS.css"). It also sets the controller as set by the fxml file. Throws
 * an exception if this fails.
 * 
 * @author Edward Stevinson
 * @version 1
 *
 */

public class MainProgram extends Application {

	
	public void start(Stage stage) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			String viewerFxml = "WorldViewer.fxml";
			AnchorPane page = (AnchorPane) fxmlLoader.load(
					this.getClass().getResource(viewerFxml).openStream());
			Scene scene = new Scene(page);
			stage.setScene(scene);
			// Set minimum size for window
			stage.setMinHeight(400);
			stage.setMinWidth(350);
			// Uses fileCSS.css as the css file to dictate the style
			scene.getStylesheets().add("fileCSS.css"); 
			// Gets controller object associated with anchorpane
			MyController controller = (MyController) fxmlLoader.getController(); 
			controller.Initialise(); 
			// Show the GUI
			stage.show();
			
		} catch (IOException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex );
			System.exit(1);
		}
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
