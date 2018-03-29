package setlab.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class ManualWindowController implements Initializable {

    @FXML
    private WebView mainWin;

    @FXML
    private Label info;

    URL url;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = getClass().getResource("/setlab/manual.html");
        mainWin.getEngine().load(url.toExternalForm());
        mainWin.contextMenuEnabledProperty().set(false);
        
        info.setText("SetLab v.0.1 (beta)");
    }
}
