/*
 * This work is licensed under Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *  Author: Giovanni De Toni (giovanni at detoni dot me)
 *  Date: 25/05/2015
 *  WebSite: www.detoni.me
 */
package pdf.downloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                try {
                    engine.setUrl(new URL(layout.getSite().getText()));
                    if (!engine.checkConfig()) {
                        engine.openPage();
                        engine.setList();
                    }
                }   catch (MalformedURLException ex) {
                    Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
           break;
           case "path":
               DirectoryChooser dir = new DirectoryChooser();
               dir.setTitle("Choose a directory");
               engine.setPATH(dir.showDialog(layout).getAbsolutePath()); 
           break;
       }
    }
    
}
