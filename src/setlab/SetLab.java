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
        
        SetObj A = new SetObj("A","a,b,c,d,f");
        
        System.out.println(A.toString());
        
        primaryStage.setTitle("v.0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
