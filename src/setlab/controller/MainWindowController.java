package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import setlab.cores.SetCore;
import setlab.cores.SetCore.SetObj;

public class MainWindowController implements Initializable {
    
    public static HashMap<String,SetObj> MapOfSets = new HashMap<>();
    
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetObj A = new SetObj("A", "a,b,c,d,e,1,2,3,4");
        SetObj B = new SetObj("B", "2,c,f,d,r,5");
        System.out.println(SetCore.Union(A, B).toString());
        System.out.println(SetCore.Intersect(A, B).toString());
        System.out.println(SetCore.Diff(A, B).toString());
        System.out.println(SetCore.SymmetricDiff(A, B).toString());
    }
    
    @FXML
    public void getCommand(){
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
