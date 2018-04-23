package setlab.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FeedbackWindowController implements Initializable {

    @FXML
    private Label fd_score;

    @FXML
    private TextField fd_titleField;

    @FXML
    private TextArea fd_textArea;

    @FXML
    private Button fd_btnSend;

    String EMAIL_login = "setlab.feedback@gmail.com";
    String EMAIL_password = "BeqhBE09";
    String EMAIL_host = "smtp.gmail.com";
    Properties props = new Properties();
    Session session = null;
    Transport transport = null;

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
        sendMassege(fd_titleField.getText(), fd_textArea.getText());
    }

    public void sendMassege(String title, String text) {
        try {
            transport.connect(EMAIL_host, 587, EMAIL_login, EMAIL_password);
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(EMAIL_login));
            InternetAddress[] address = {new InternetAddress(EMAIL_login)};
            msg.setRecipients(Message.RecipientType.TO, address);
            
            msg.setSubject(title);
            msg.setText(getMessage(text));

            msg.setSentDate(new Date());
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

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
        res.append("Язык на комрьютере: ").append(System.getProperty("user.country")).append("\n");
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

        props.put("mail.smtp.host", EMAIL_login);
        props.put("mail.smtp.ssl.enable", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(props);
        transport = session.getTransport();
    }
}
