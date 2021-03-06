/*
 * This work is licensed under Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)
 *  Author: Giovanni De Toni (giovanni at detoni dot me)
 *  Date: 25/05/2015
 *  WebSite: www.detoni.me
 */
package pdf.downloader;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
    private Button download, path, search;
    private Text message, numberFiles;
    private ProgressBar progress;
    private TextField site;
    private ListView<HBox> progressList;

    public Layout() {
        setList();
        setBar();
        setButtons();
        setTextField();
        setLayout();
    }
 
    
    public Text getNumberFiles() {
        return numberFiles;
    }
    
    public Button getDownload() {
        return download;
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
    
    public ListView<HBox> getProgressList() {
        return progressList; 
    }

    
    public void setProgressList(List<String> l) {
        List<HBox> listTmp = new ArrayList<HBox>();
        for (String elem : l) {
            HBox container = new HBox();
            HBox tmp = new HBox();
            HBox tmp2 = new HBox();
            tmp.getChildren().addAll(new Text(elem));
            tmp2.setAlignment(Pos.CENTER_RIGHT);
            container.getChildren().addAll(tmp, tmp2);
            container.setSpacing(SPACING);
            container.setAlignment(Pos.CENTER_LEFT);
            listTmp.add(container);
        }
        progressList.setItems(FXCollections.observableList(listTmp));
    }
    
    private void setLayout() {
        layout = new BorderPane();
        controls = new HBox();
        board = new VBox();
        message = new Text("Fill the above form");
        
        numberFiles = new Text("Number of file: ");
        
        controls.getChildren().addAll(site,path,search);
        setBox(controls);
        
        HBox tmpBox = new HBox();
        tmpBox.getChildren().add(progress);
        tmpBox.getChildren().add(numberFiles);
        tmpBox.setSpacing(SPACING);
        tmpBox.setAlignment(Pos.CENTER);
        
        board.getChildren().addAll(controls, tmpBox);
        setBox(board);
        
        layout.setTop(board);
        layout.setCenter(progressList);
        layout.setBottom(download);
        BorderPane.setAlignment(download, Pos.CENTER);
        BorderPane.setMargin(progressList, PADDING);
        layout.setPadding(PADDING);
        
    }
    
    private void setTextField() {
        site = new TextField();
        Tooltip tmp = new Tooltip("Insert here the page url");
        site.setTooltip(tmp);
    }
    
    private void setButtons() {
        ButtonHandler listener = new ButtonHandler(this);
        
        search = new Button("Search");
        search.setId("search");
        search.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
       
        path = new Button("Chose a location");
        path.setMaxWidth(130);
        path.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);
        path.setId("path");
        path.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
        
        download = new Button("Download");
        download.setId("download");
        download.setMinSize(30, 40);
        download.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
        download.setDisable(true);
    }
    
    private void setBox(HBox h) {
        h.setAlignment(Pos.CENTER);
        h.setSpacing(SPACING);
        //h.setPadding(PADDING);
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
    
    private void setList() {
       progressList = new ListView<>();
       progressList.setEditable(false);
    }
}
