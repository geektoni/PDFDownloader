/*
 * This work is licensed under Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *  Author: Giovanni De Toni (giovanni at detoni dot me)
 *  Date: 25/05/2015
 *  WebSite: www.detoni.me
 */
package pdf.downloader;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Giovanni De Toni
 */
public class ButtonHandler implements EventHandler {

    private Layout layout;
    private PDFParser engine;
    
    public ButtonHandler(Layout layout) {
        this.layout = layout;
        engine = new PDFParser();
    }

    @Override
    public void handle(Event t) {
       Button tmp = (Button) t.getSource();
       switch(tmp.getId()) {
           case "download":
              // PDFParser getter = new PDFParser(layout.getSite().getText(), null);
           break;
           case "path":
               DirectoryChooser dir = new DirectoryChooser();
               dir.setTitle("Choose a directory");
               engine.setPATH(dir.showDialog(layout).getAbsolutePath()); 
           break;
       }
    }
    
}
