package setlab.controller;

import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.*;
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
    public void tryToSendMassege(){
        sendMassege(fd_titleField.getText(), fd_textArea.getText());
    }
    
    public void sendMassege(String title, String text){
        try {
            transport.connect("smtp.gmail.com", EMAIL_login, EMAIL_password);
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(EMAIL_login));
            InternetAddress[] address = {new InternetAddress(EMAIL_login)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(title);
            msg.setSentDate(new Date());

            msg.setText(text);

            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        
    }

    public String getMessage(String inner) {
        StringBuilder res = new StringBuilder(inner);
        res.append("\n------------ Info -----------\n");
        res.append(getInfo());
        return res.toString();
    }

    private String getInfo() {
        StringBuilder res = new StringBuilder();
        res.append("\tOS: ").append(System.getProperties().getProperty("os.name")).append("\n");
        res.append("\tJava version: ");
        res.append(System.getProperties().getProperty("java.version")).append("\n");
        Date date = new Date();
        res.append(date);
        return res.toString();
    }

    private void initializeFeedBack() throws NoSuchProviderException{
        int MAX_LENGTH = 100;
        fd_score.setText("0/" + MAX_LENGTH);
        fd_titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            fd_score.setText(newValue.length() + "/" + MAX_LENGTH);
            if (newValue.length() > MAX_LENGTH) {
                fd_titleField.setText(oldValue);
            }
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
