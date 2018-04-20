package setlab.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FeedbackWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public String getMessage(String inner) {
        StringBuilder res = new StringBuilder(inner);
        res.append("\n------------ Info -----------\n");
        res.append(getInfo());
        return res.toString();
    }
    
    private String getInfo() {
        StringBuilder res = new StringBuilder();
        res.append("\tOS: ").append(System.getProperties().getProperty("os.name")).append("\n");
        res.append("\tJava version: ");
        res.append(System.getProperties().getProperty("java.version")).append("\n");
        Date date = new Date();
        res.append(date);
        return res.toString();
    }
}
