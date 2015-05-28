package pdf.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

    public PDFParser(String url, String path) throws MalformedURLException {
        this.url = new URL(url);
        this.PATH = path;
        openPage();
        getList();
    }
    
    /**
     * @param args the command line arguments
     
    public static void main(String[] args) throws Exception {
        String siteUrl = "http://test.com";
        List<String> elements;
            URL page = new URL(siteUrl);
            elements = m.getList();
            int i =0;
            for (String s : elements) {
                if (s.contains(".pdf")) {
                    if (!s.contains(siteUrl)) {
                        String url = siteUrl;
                        m.download(url.concat(s), i);
                        i++; 
                    } else {
                        m.download(s, i);
                        i++;
                    }
                }
            }
    }
    **/
    private void openPage() {
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
    
    private void getList() {
        list = document.getElementsByTagName("a");
        for (int i = 0; i < list.getLength(); i++) {
            srcs.add(list.item(i).getAttributes().getNamedItem("href").getNodeValue());
        }
    }
    
    private void download(String url, int i) {
        System.out.println("opening connection " + url);
        try {
            this.url = new URL(url);
            in = this.url.openStream();
            fos = new FileOutputStream(new File(PATH+i+"-"+getName(url)));
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
        } catch(Exception e) {
            System.err.println(e);
        }
        
        System.out.println("file was downloaded");
    }
    
    private String getName(String name) {
        return name.substring(name.lastIndexOf("/")+1);
    }
    
}
