package pdf.downloader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main application class
 * @author Giovanni De Toni
 */
public class Main extends Application {
    
    final static public String TITLE = "PDFDownloader";
    
    @Override
    public void start(Stage primaryStage) {
        
        Layout root = new Layout();    
        Scene scene = new Scene(root.getLayout());
        
        primaryStage.setTitle(Main.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
