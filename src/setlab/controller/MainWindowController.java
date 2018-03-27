package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.web.WebView;
import setlab.cores.BinRelCore;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;

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
    private Button set_op_union;

    @FXML
    private Button set_op_inter;

    @FXML
    private Button set_op_diff;

    @FXML
    private Button set_op_symmdiff;

    @FXML
    private Tab tab_binRel;

    @FXML
    private TextArea binrel_area;

    @FXML
    private TextField binrel_field;

    @FXML
    private Button analisis;

    @FXML
    private Tab tab_comb;

    @FXML
    private WebView comb_webView;

    @FXML
    private WebView comb_infoAcceptWebView;

    @FXML
    private Button comb_infoAccept_noBtn;

    @FXML
    private Button comb_infoAccept_yesBtn;

    @FXML
    private Button comb_infoAccept_backBtn;

    SimpleStringProperty string = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //----------------------------------------------------------- SET ------
        set_op_union.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                String t = set_field.getText(), leftRes, rigthRes;
                int pos = set_field.getCaretPosition();
                leftRes = t.substring(0, pos);
                rigthRes = t.substring(pos);
                set_field.setText(leftRes + "∪" + rigthRes);
                set_field.positionCaret(pos + 1);
            }
        });
        set_op_inter.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                String t = set_field.getText(), leftRes, rigthRes;
                int pos = set_field.getCaretPosition();
                leftRes = t.substring(0, pos);
                rigthRes = t.substring(pos);
                set_field.setText(leftRes + "∩" + rigthRes);
                set_field.positionCaret(pos + 1);
            }
        });
        set_op_diff.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                String t = set_field.getText(), leftRes, rigthRes;
                int pos = set_field.getCaretPosition();
                leftRes = t.substring(0, pos);
                rigthRes = t.substring(pos);
                set_field.setText(leftRes + "/" + rigthRes);
                set_field.positionCaret(pos + 1);
            }
        });
        set_op_symmdiff.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                String t = set_field.getText(), leftRes, rigthRes;
                int pos = set_field.getCaretPosition();
                leftRes = t.substring(0, pos);
                rigthRes = t.substring(pos);
                set_field.setText(leftRes + "∆" + rigthRes);
                set_field.positionCaret(pos + 1);
            }
        });
        //----------------------------------------------------- BIN REL --------

        binrel_field.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                binrel_getCommand();
            }
        });

        //--------------------------------------------------- COMBINATORICS ----
        string.addListener((observable, oldValue, newValue) -> {
            String massage = "";
            switch (string.getValue()) {
                case "start":
                    massage = "Важен ли порядок расположения элементов в КК?";
                    comb_infoAccept_backBtn.setDisable(true);
                    comb_infoAccept_yesBtn.setDisable(false);
                    comb_infoAccept_noBtn.setDisable(false);
                    break;
                case "start;yeah":
                    massage = "Все ли элементы множества А входят в КК?";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(false);
                    comb_infoAccept_noBtn.setDisable(false);
                    break;
                case "start;yeah;yeah":
                    massage = "Есть ли в КК повторения элементов?";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(false);
                    comb_infoAccept_noBtn.setDisable(false);
                    break;
                case "start;yeah;yeah;nope":
                    massage = "Перестановки без повторений из n элементов";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                case "start;yeah;yeah;yeah":
                    massage = "Перестановки с повторениями из n элементов по m с заданой спецификацией";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                case "start;yeah;nope":
                    massage = "Есть ли в КК повторения элементов?";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(false);
                    comb_infoAccept_noBtn.setDisable(false);
                    break;
                case "start;yeah;nope;yeah":
                    massage = "Размещения с повторениями из n элементов по m";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                case "start;yeah;nope;nope":
                    massage = "Размещения без повторений из n элементов по m";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                case "start;nope":
                    massage = "Есть ли в КК повторения элементов?";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(false);
                    comb_infoAccept_noBtn.setDisable(false);
                    break;
                case "start;nope;yeah":
                    massage = "Сочетания с повторениями из n элементов по m";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                case "start;nope;nope":
                    massage = "Сочетания без повторений из n элементов по m";
                    comb_infoAccept_backBtn.setDisable(false);
                    comb_infoAccept_yesBtn.setDisable(true);
                    comb_infoAccept_noBtn.setDisable(true);
                    break;
                default:
                    System.out.println("Ошибка почему-то");
            }

            String html = "<html><h4>" + massage + "</h4></html>";
            comb_infoAcceptWebView.getEngine().loadContent(html);
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
        binrel_area.setText(res.toString());
    }

    @FXML
    public void set_getCommand() {
        if (!set_field.getText().isEmpty()) {

        }
    }

    @FXML
    public void binrel_getCommand() {
        if (!binrel_field.getText().isEmpty()) {
            String command = binrel_field.getText();
            binrel_area.setText(SintaxBinRel.get(command));
            binrel_field.setText("");
        }
    }

    @FXML
    public void Manual() throws IOException {
    }

    @FXML
    public void commandStart() {
        string.set("start");
    }

    @FXML
    public void commandYeah() {
        String temp = string.getValue();
        temp = temp + ";yeah";

        string.set(temp);
    }

    @FXML
    public void commandNope() {
        String temp = string.getValue();
        temp = temp + ";nope";

        string.set(temp);
    }

    @FXML
    public void commandBack() {
        String temp = string.getValue();
        temp = temp.substring(0, temp.length() - 5);

        string.set(temp);
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
        - Сделать кнопки операций для множеств: ∪ ∩ / ∆ \
     */
}
