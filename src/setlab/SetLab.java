package setlab;

import java.awt.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import setlab.controller.Setting;

public class SetLab extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/setlab/fxml/MainWindow.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("SetLab" + Setting.VERSION);

        primaryStage.setResizable(false);


        primaryStage.setWidth(670);
        primaryStage.setHeight(70 + 440);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
