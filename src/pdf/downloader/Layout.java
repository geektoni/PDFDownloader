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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private Button download;
    private TextField site;

    public Layout() {
        setButtons();
        setTextField();
        setLayout();
    }
    
    
    public BorderPane getLayout() {
        return layout;
    }
    
    private void setLayout() {
        layout = new BorderPane();
        controls = new HBox();
        
        controls.getChildren().addAll(site, download);
        setBox(controls);
        
        layout.setTop(controls);
        
    }
    
    private void setTextField() {
        site = new TextField();
    }
    
    private void setButtons() {
        download = new Button("Download");
        download.setId("download");
        download.addEventHandler(MouseEvent.MOUSE_CLICKED, new ButtonHandler(this));
    }
    
    private void setBox(HBox h) {
        h.setAlignment(Pos.CENTER);
        h.setSpacing(SPACING);
        h.setPadding(PADDING);
    }
    
    
}
