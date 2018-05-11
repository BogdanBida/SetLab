package setlab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SetLab extends Application {

    public static String NAME_PROGRAM = "SetLab";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/setlab/fxml/MainWindow.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle(NAME_PROGRAM + " " + Setting.VERSION);
        primaryStage.getIcons().add(new Image(SetLab.class.getResourceAsStream("fxml/UI_images/SetLab.png")));
        
        primaryStage.setMinWidth(670);
        primaryStage.setMinHeight(70 + 440);
        
        primaryStage.setWidth(670);
        primaryStage.setHeight(70 + 440);
        primaryStage.setScene(scene);
        
        primaryStage.setOnCloseRequest((event) -> {
            System.exit(0);
        });
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
