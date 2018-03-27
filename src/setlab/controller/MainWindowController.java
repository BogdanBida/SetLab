package setlab.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import setlab.cores.SetCore.SetObj;
import setlab.cores.BinRelCore.BinRel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import setlab.cores.BinRelCore;
import setlab.cores.CombCore;

public class MainWindowController implements Initializable {

    public static HashMap<String, SetObj> MapOfSets = new HashMap<>();

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

    @FXML
    private TextArea binrel_area;

    @FXML
    private TextField binrel_field;

    @FXML
    private Button binrel_analysis;

    @FXML
    private WebView comb_webView;

    @FXML
    private TextArea comb_infoAccept;

    @FXML
    private Button comb_infoAccept_noBtn;

    @FXML
    private Button comb_infoAccept_yesBtn;

    @FXML
    private Button comb_infoAccept_backBtn;

    DocumentBuilder builder;
    Document doc;
    Element element;
    Node node;
    Node t;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ---------------------------------------------- COMBINATORICS PAGE ---
        comb_webView.setContextMenuEnabled(false);
        comb_infoAccept_backBtn.setDisable(true);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new File("src/setlab/combAlg.xml"));
            element = doc.getDocumentElement();

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            System.err.println("Error");
        }

        // YES
        comb_infoAccept_yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            comb_infoAccept_backBtn.setDisable(false);

            comb_infoAccept.setText(node.getNodeValue());
        });
        // NO
        comb_infoAccept_noBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            comb_infoAccept_backBtn.setDisable(false);

            comb_infoAccept.setText(node.getNodeValue());
        });
        // BACK
        comb_infoAccept_backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            node = node.getParentNode().getPreviousSibling();
            comb_infoAccept.setText(node.getNodeValue());

            if (node.getParentNode().getPreviousSibling() == null) {
                comb_infoAccept_backBtn.setDisable(true);
            }
        });
        // ---------------------------------------------------------------------
        
        
        
    }

    @FXML
    public void analisisBinRel() {
        BinRel A = new BinRel("A", "((2,1) (4,2) (1,3) (2,3))");
        StringBuilder res = new StringBuilder(A + "\n");
        res.append(BinRelCore.D(A));
        res.append("\n");
        res.append(BinRelCore.E(A));
        res.append("\n");
        res.append(BinRelCore.O(A));
        res.append("\n");
        res.append(BinRelCore.Ident(BinRelCore.O(A)));
        res.append("\n");
        res.append(BinRelCore.Reverse(A));
        res.append("\n ___ \n");
        res.append(BinRelCore.Refelex(A));
        res.append("\n");
        res.append(BinRelCore.AntiRefelex(A));
        res.append("\n");
        res.append(BinRelCore.Simetry(A));
        res.append("\n");
        res.append(BinRelCore.AntiSimetry(A));
        res.append("\n");
        res.append(BinRelCore.Asimetry(A));
        res.append("\n");
        res.append(BinRelCore.Transity(A));
        res.append("\n");
        binrel_area.setText(res.toString() + "∪ ∩ / ∆");
    }

    @FXML
    public void getCommand() {
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

    /*
        - Запрет на ввод текста в поля вывода
        - Пункут меню "о программе" модальное окно с авторами, версией
            датой созданий - текущей датой (Пример: 25.03.2018 - 01.01.2020)
            почтой и.т.д.
        - Найти иконки для бинарных отношений
        - Сделать кнопки операций для множеств: ∪ ∩ / ∆
     */
}
