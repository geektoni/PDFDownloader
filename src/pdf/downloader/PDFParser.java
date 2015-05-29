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
 *
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
    private List<String> srcs = new ArrayList<>();

    public List<String> getSrcs() {
        return srcs;
    }

    public PDFParser() {
        PATH = null;
        url = null;
    }
    
    public boolean checkConfig() {
        return (url == null || PATH == null);
    }
    
    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
    
    public String getPath() {
        return PATH;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }
    
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
    
    public void setList() {
        list = document.getElementsByTagName("a");
        for (int i = 0; i < list.getLength(); i++) {
            String urlTmp = list.item(i).getAttributes().getNamedItem("href").getNodeValue();
            if (checkContainsFile(urlTmp)) {
                srcs.add(urlTmp);
            }
        }
    }
    
    public boolean download(String url, int i) {
        boolean status= false;
        System.out.println("opening connection " + url);
        try {
            this.url = new URL(url);
            in = this.url.openStream();
            fos = new FileOutputStream(new File(PATH+"/"+i+"-"+getName(url)));
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("reading file...");
        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from connection
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
    
    private String getName(String name) {
        return name.substring(name.lastIndexOf("/")+1);
    }
    
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
    
    public boolean checkContainsFile(String url) {
        boolean status = false;
        if (url.contains(".pdf")) {
            status = true;
        }
        return status;
    }
}
