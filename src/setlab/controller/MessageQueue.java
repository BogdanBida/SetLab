package setlab.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.util.Pair;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageQueue extends Task<Void> implements Serializable {

    String EMAIL_login = "setlab.feedback@gmail.com";
    String EMAIL_password = "BeqhBE09";
    String EMAIL_host = "smtp.gmail.com";

    ArrayList<Pair<String, String>> list = new ArrayList<>();

    Properties props = new Properties();
    Session session = null;
    Transport transport = null;

    public MessageQueue() {
        try {
            list = getList();
        } catch (IOException ex) {
            Logger.getLogger(MessageQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Void call() throws Exception {
        try {
            while (!list.isEmpty()) {
                connectToServer();
                if (transport.isConnected()) {
                    tryToSendMessage();
                }
                Thread.sleep(20000);
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MessageQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    protected void succeeded() {
        
    }

    @Override
    protected void cancelled() {

    }

    @Override
    protected void failed() {
        
    }
    
    public void addToQueue(String subject, String text) {
        list.add(new Pair<>(subject, text));
        try {
            saveList();
        } catch (IOException ex) {
        }
    }

    private void connectToServer() {
        props.put("mail.smtp.host", EMAIL_login);
        props.put("mail.smtp.ssl.enable", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(props);
        try {
            transport = session.getTransport();
            transport.connect(EMAIL_host, 587, EMAIL_login, EMAIL_password);
        } catch (NoSuchProviderException ex) {
        } catch (MessagingException ex) {
        }
    }

    private void tryToSendMessage() throws IOException, FileNotFoundException, URISyntaxException {
        while (transport.isConnected() && !list.isEmpty()) {
            MimeMessage temp = createMessage(list.get(0));
            try {
                if (temp != null) {
                    transport.sendMessage(temp, temp.getRecipients(Message.RecipientType.TO));
                }
            } catch (MessagingException ex) {
                return;
            }
            list.remove(0);
            saveList();
        }
    }

    private MimeMessage createMessage(Pair<String, String> pair) {
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(EMAIL_login));
            InternetAddress[] address = {new InternetAddress(EMAIL_login)};
            msg.setRecipients(Message.RecipientType.TO, address);

            msg.setSubject(pair.getKey());
            msg.setText(pair.getValue());

            msg.setSentDate(new Date());
        } catch (MessagingException ex) {
            return null;
        }
        return msg;
    }

    private void saveList() throws IOException {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(new File("src/setlab/tempFiles/list.obj"));
        } catch (FileNotFoundException exception) {
        }
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(list);
        oos.close();
    }

    private ArrayList<Pair<String, String>> getList() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(new File("src/setlab/tempFiles/list.obj"));
        ObjectInputStream ois = new ObjectInputStream(fin);
        return (ArrayList<Pair<String, String>>) ois.readObject();
    }

}
