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
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
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
                       int i=0;
                       for (String elem : engine.getSrcs()) {
                            if (engine.checkContainsFile(elem)) {
                                if (engine.download(elem, i)) {
                                    HBox tmpBox = (HBox)layout.getProgressList().getItems().get(i).getChildren().get(1);
                                    ((ProgressBar)tmpBox.getChildren().get(0)).setProgress(1);
                                }
                                i++;
                            }
                        }
                               
                   }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
           break;
           case "path":
               DirectoryChooser dir = new DirectoryChooser();
               dir.setTitle("Choose a directory");
               engine.setPATH(dir.showDialog(layout).getPath());
           break;
               
           case "search":
               try {
                    engine.setUrl(new URL(layout.getSite().getText()));
                    if (!engine.checkConfig()) {
                       engine.openPage();
                       engine.setList();
                       layout.setProgressList(engine.getSrcs());
                       layout.getDownload().setDisable(false);
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
           break;
       }
    }
    
}
