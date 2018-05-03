package setlab.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.NoSuchProviderException;

public class FeedbackWindowController implements Initializable {

    @FXML
    private Label fd_score;

    @FXML
    private TextField fd_titleField;

    @FXML
    private TextArea fd_textArea;

    @FXML
    private Button fd_btnSend;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initializeFeedBack();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void tryToSendMassege() {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.addToQueue(fd_titleField.getText(), getMessage(fd_textArea.getText()));
        Thread dThread = new Thread(messageQueue);
        dThread.setDaemon(true);
        dThread.start();
    }

    public String getMessage(String inner) {
        StringBuilder res = new StringBuilder(inner);
        res.append("\n------------------------ Info -----------------------\n");
        res.append(getInfo());
        return res.toString();
    }

    private String getInfo() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("'\nДата: 'E dd.MM.yyyy '\nВремя: 'HH:mm:ss zzz");
        StringBuilder res = new StringBuilder("");
        res.append("OS: ").append(System.getProperties().getProperty("os.name")).append("\n");
        res.append("Имя пользователя: ").append(System.getProperty("user.name")).append("\n");
        res.append("Язык на компьютере: ").append(System.getProperty("user.country")).append("\n");
        res.append("Java version: ").append(System.getProperties().getProperty("java.version")).append("\n");
        res.append("\n------------ Processor info ------------\n");
        res.append("Название процессора: ").append(System.getenv("PROCESSOR_IDENTIFIER")).append("\n");
        res.append("Количество ядер: ").append(System.getenv("NUMBER_OF_PROCESSORS")).append("\n");
        res.append("Архитектура процессора: ").append(System.getenv("PROCESSOR_ARCHITECTURE")).append("\n");
        res.append("\nСообщение создано: ").append(formatForDateNow.format(new Date()));
        return res.toString();
    }

    private void initializeFeedBack() throws NoSuchProviderException {
        int MAX_LENGTH = 100;
        fd_score.setText("0/" + MAX_LENGTH);
        fd_titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            fd_score.setText(newValue.length() + "/" + MAX_LENGTH);
            if (newValue.length() > MAX_LENGTH) {
                fd_titleField.setText(oldValue);
            }
        });

        fd_btnSend.setOnAction((event) -> {
            tryToSendMassege();
        });

        fd_btnSend.disableProperty().bind(fd_textArea.textProperty().isEmpty().or(fd_titleField.textProperty().isEmpty()));
    }

}
