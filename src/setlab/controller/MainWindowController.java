package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import setlab.SetCore;
import setlab.SetCore.SetObj;

public class MainWindowController implements Initializable {
    
    public static HashMap<String,SetObj> MapOfSets = new HashMap<>();
    
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
    public void Manual() throws IOException {
    }
    
    @FXML
    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
    
}
