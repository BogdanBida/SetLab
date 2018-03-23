package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import setlab.cores.SetCore;
import setlab.cores.SetCore.SetObj;
import setlab.cores.CombCore;
import setlab.cores.BinRelCore;

public class MainWindowController implements Initializable {
    
    public static HashMap<String,SetObj> MapOfSets = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    @FXML
    public void Manual() throws IOException {
    }
    
    @FXML
    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
    
}
