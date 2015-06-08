/*
 * This work is licensed under Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *  Author: Giovanni De Toni (giovanni at detoni dot me)
 *  Date: 25/05/2015
 *  WebSite: www.detoni.me
 */
package pdf.downloader;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application class
 * @author Giovanni De Toni
 */
public class Main extends Application {
    
    final static public String TITLE = "PDFDownloader";
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml_layout.fxml"));    
        Scene scene = new Scene(root);
        
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
