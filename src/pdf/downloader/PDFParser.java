package pdf.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

/**
 * Class that parse the HTML page to find links to pdf files.
 * @author Giovanni De Toni
 */
public class PDFParser {

    private String PATH;
    private FileOutputStream fos;
    private InputStream in;
    private URL url;
    private NodeList list;
    private Document document;
    private Tidy tidy;
    private List<String> srcs;

    /**
     * Constructor, set PATH and url variables to null.
     */
    public PDFParser() {
        PATH = null;
        url = null;
    }
    
    /**
     * Method that check if url and PATH are specified (not null).
     * @return true whether path ore url are not specified. 
     */
    public boolean checkConfig() {
        return (url == null || PATH == null);
    }
    
    /*
    * Some getter and setter of private variables.
    */
    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
    
    public String getPath() {
        return PATH;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }
    
    public List<String> getSrcs() {
        return srcs;
    }

    /**
     * Method that open the page and populate
     * document variable using JTidy.
     */
    public void openPage() {
        tidy = new Tidy();
        tidy.setShowErrors(0);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        try {
            document = tidy.parseDOM(url.openStream(), null);
        } catch (IOException ex) {
            Logger.getLogger(PDFParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method that generate a new list of every href occurrencies,
     * but only if they contain .pdf.
     */
    public void setList() {
        srcs = new ArrayList<>();
        list = document.getElementsByTagName("a");
        for (int i = 0; i < list.getLength(); i++) {
            String urlTmp = list.item(i).getAttributes().getNamedItem("href").getNodeValue();
            if (checkContainsFile(urlTmp)) {
                srcs.add(checkURL(urlTmp));
            }
        }
    }
    
    /**
     * Method that download a file, given an url and
     * an integer.
     * @param url string that identifies the file
     * @param i counter
     * @return true if everything worked
     */
    public boolean download(String url, int i) {
        System.out.println(url);
        boolean status= false;
        try {
            this.url = new URL(url);
            in = this.url.openStream();
            fos = new FileOutputStream(new File(PATH+"/"+i+"-"+getName(url)));
        } catch (Exception e) {
            System.err.println(e);
        }
        int length = -1;
        byte[] buffer = new byte[1024];
        try {
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            status = true;
        } catch(Exception e) {
            System.err.println(e);
        }
        return status;
    }
    
    /**
     * Method that return the name of a specified file
     * @param name url of that file
     * @return string representing the filename
     */
    private String getName(String name) {
        return name.substring(name.lastIndexOf("/")+1);
    }
    
    /**
     * Method that check if url passed is not relative and
     * if it is it will convert it to and absolute one.
     * @param url file url (relative or not).
     * @return absolute file url
     */
    public String checkURL(String url) {
         String result = null;
         if (!url.contains(this.url.toString())) {
            String urltmp = this.url.toString();
            result = urltmp.concat(url); 
         } else {
             result = url;
         }
         return result;
    }
    
    /**
     * Method that check if an url contains .pdf or not
     * @param url file url
     * @return true if it contains .pdf, false otherwise. 
     */
    public boolean checkContainsFile(String url) {
        boolean status = false;
        if (url.contains(".pdf")) {
            status = true;
        }
        return status;
    }
}
