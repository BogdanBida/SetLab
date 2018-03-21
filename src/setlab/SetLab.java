package setlab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import setlab.SetCore.*;

public class SetLab extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        SetObj A = new SetObj("A", "a,b,c,d,e,1,2,3,4");
        System.out.println(A.toString());
        
        primaryStage.setTitle("SetLab v.0.1 alpha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
