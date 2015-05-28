/*
 * This work is licensed under Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *  Author: Giovanni De Toni (giovanni at detoni dot me)
 *  Date: 25/05/2015
 *  WebSite: www.detoni.me
 */
package pdf.downloader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Giovanni De Toni 
 */
public class Layout extends Stage {
    
    final static private int SPACING = 20;
    final static private Insets PADDING = new Insets(SPACING);
    
    private BorderPane layout;
    private HBox controls;
    private VBox board;
    private Button download, path;
    private Text message;
    private ProgressBar progress;
    private TextField site;

    public Layout() {
        setBar();
        setButtons();
        setTextField();
        setLayout();
    }
    
    
    public BorderPane getLayout() {
        return layout;
    }
    
    public TextField getSite() {
        return site;
    }
    
    public Text getMessage() {
        return message;
    }

    public ProgressBar getProgress() {
        return progress;
    }
    
    private void setLayout() {
        layout = new BorderPane();
        controls = new HBox();
        board = new VBox();
        message = new Text("Fill the above form");
        
        controls.getChildren().addAll(site,path,download);
        setBox(controls);
        
        board.getChildren().addAll(message, progress);
        setBox(board);
        
        layout.setTop(controls);
        layout.setCenter(board);
        
    }
    
    private void setTextField() {
        site = new TextField();
    }
    
    private void setButtons() {
        download = new Button("Download");
        download.setId("download");
        download.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonHandler(this));
        path = new Button("Chose a location...");
        path.setId("path");
        path.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonHandler(this));
    }
    
    private void setBox(HBox h) {
        h.setAlignment(Pos.CENTER);
        h.setSpacing(SPACING);
        h.setPadding(PADDING);
    }
    
    private void setBox(VBox h) {
        h.setAlignment(Pos.CENTER);
        h.setSpacing(SPACING);
        h.setPadding(PADDING);
    }
    
    private void setBar() {
        progress = new ProgressBar(0.0);
        progress.setMinSize(20, 10);
    }
    
}
