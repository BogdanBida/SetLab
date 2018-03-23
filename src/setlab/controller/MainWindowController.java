package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import setlab.cores.SetCore.SetObj;

public class MainWindowController implements Initializable {
    
    public static HashMap<String, SetObj> MapOfSets = new HashMap<>();
    
    @FXML
    private Tab tab_set;
    
    @FXML
    private ListView<?> set_listView;
    
    @FXML
    private TextArea set_area;
    
    @FXML
    private TextField set_field;
    
    @FXML
    private Tab tab_binRel;
    
    @FXML
    private Tab tab_comb;

    @FXML
    private WebView comb_webView;

    @FXML
    private TextArea comb_infoAccept;

    @FXML
    private Button comb_infoAccept_noBtn;

    @FXML
    private Button comb_infoAccept_yesBtn;

    @FXML
    private Button comb_infoAccept_backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    public void getCommand() {
        if (!set_field.getText().isEmpty()) {
            
        }
    }
    
    @FXML
    public void Manual() throws IOException {
    }
    
    @FXML
    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
    
}
