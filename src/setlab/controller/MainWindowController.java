package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;
import setlab.SetLab;

public class MainWindowController implements Initializable {

    public static HashMap<String, SetObj> MapOfSets = new HashMap<>();
    private static byte comb_typeFunc;

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
    private Canvas binrel_canvas;

    @FXML
    private StackPane binrel_paneCanvas;

    @FXML
    private ImageView ImageViewReflex;

    @FXML
    private ImageView ImageViewAntiReflex;

    @FXML
    private ImageView ImageViewBidirect;

    @FXML
    private ImageView ImageViewAntiBidirect;

    @FXML
    private ImageView ImageViewAsBidirect;

    @FXML
    private ImageView ImageViewTransitive;

    @FXML
    private Tab tab_comb;

    @FXML
    private WebView comb_webView;

    @FXML
    private ImageView comb_imageView;

    @FXML
    private WebView comb_infoAcceptWebView;

    private Image imageFormula_a_mn;
    private Image imageFormula_amn;
    private Image imageFormula_c_mn;
    private Image imageFormula_cmn;
    private Image imageFormula_p;
    private Image imageFormula_pk;

    @FXML
    private Button comb_infoAccept_noBtn;

    @FXML
    private Button comb_infoAccept_yesBtn;

    @FXML
    private Button comb_infoAccept_backBtn;

    @FXML
    private TextField comb_fieldN;

    @FXML
    private TextField comb_fieldM;

    @FXML
    private Button comb_btnEnter;

    @FXML
    private HBox comb_inputPane;

    SimpleStringProperty string = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeSet();
        initializeBinRel();
        initializeComb();
        initializaImage();
        initializeFields();
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
            if (command.equals("clear")) {
                binrel_area.setText("");
            } else {
                String[] line = command.replaceAll(" ", "").split("=");
                BinRel R = new BinRel(line[0], line[1]);
                GraphicsContext context = binrel_canvas.getGraphicsContext2D();
                context = BinRel_GraphicsGraphCore.getContext(binrel_canvas, R);

                binrel_area.setText(binrel_area.getText() + "\n" + SintaxBinRel.get(command, R));

            }
            binrel_field.setText("");
        }
    }

    @FXML
    public void comb_getCommand() {
        if (true) {
            if (false) {
                String[] k;
                String m;
            } else {
                String n, m;
            }
        }
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
        comb_imageView.setImage(null);
    }

    private void initializeSet() {
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
    }

    private void initializeBinRel() {
        binrel_field.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                binrel_getCommand();
                // = SintaxBinRel.anLine.get(0);
            }
        });

        BinRel R = new BinRel("R", "((a,b),(c,d))");
        binrel_paneCanvas.setStyle("-fx-background-color: #d0d0d0");
        GraphicsContext context = binrel_canvas.getGraphicsContext2D();
        context = BinRel_GraphicsGraphCore.getContext(binrel_canvas, R);
    }

    private void initializeComb() {
        BooleanProperty listener = new SimpleBooleanProperty();
        BooleanProperty listenerBack = new SimpleBooleanProperty();

        comb_infoAccept_noBtn.disableProperty().bind(listener);
        comb_infoAccept_yesBtn.disableProperty().bind(listener);
        comb_inputPane.disableProperty().bind(listener.not());
        comb_infoAccept_backBtn.disableProperty().bind(listenerBack);
        
        string.addListener((observable, oldValue, newValue) -> {
            String massage = "";

            switch (string.getValue()) {
                case "start":
                    massage = "Важен ли порядок расположения элементов в КК?";
                    listenerBack.set(true);
                    listener.set(false);
                    break;
                case "start;yeah":
                    massage = "Все ли элементы множества А входят в КК?";
                    listenerBack.set(false);
                    listener.set(false);
                    break;
                case "start;yeah;yeah":
                    massage = "Есть ли в КК повторения элементов?";
                    listenerBack.set(false);
                    listener.set(false);
                    break;
                case "start;yeah;yeah;nope":
                    massage = "Перестановки без повторений из n элементов";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_p.getHeight());
                    comb_imageView.setFitWidth(imageFormula_p.getWidth());
                    comb_imageView.setImage(imageFormula_p);
                    comb_typeFunc = 1;
                    break;
                case "start;yeah;yeah;yeah":
                    massage = "Перестановки с повторениями из n элементов по m с заданой спецификацией";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_pk.getHeight());
                    comb_imageView.setFitWidth(imageFormula_pk.getWidth());
                    comb_imageView.setImage(imageFormula_pk);
                    comb_typeFunc = 2;
                    break;
                case "start;yeah;nope":
                    massage = "Есть ли в КК повторения элементов?";
                    listenerBack.set(false);
                    listener.set(false);
                    break;
                case "start;yeah;nope;yeah":
                    massage = "Размещения с повторениями из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_amn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_amn.getWidth());
                    comb_imageView.setImage(imageFormula_amn);
                    comb_typeFunc = 3;
                    break;
                case "start;yeah;nope;nope":
                    massage = "Размещения без повторений из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_a_mn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_a_mn.getWidth());
                    comb_imageView.setImage(imageFormula_a_mn);
                    comb_typeFunc = 4;
                    break;
                case "start;nope":
                    massage = "Есть ли в КК повторения элементов?";
                    listenerBack.set(false);
                    listener.set(false);
                    break;
                case "start;nope;yeah":
                    massage = "Сочетания с повторениями из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_cmn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_cmn.getWidth());
                    comb_imageView.setImage(imageFormula_cmn);
                    comb_typeFunc = 5;
                    break;
                case "start;nope;nope":
                    massage = "Сочетания без повторений из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_c_mn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_c_mn.getWidth());
                    comb_imageView.setImage(imageFormula_c_mn);
                    comb_typeFunc = 6;
                    break;
                default:
                    System.out.println("Ошибка почему-то");
            }

            String html = "<html><br><br><center>" + massage + "</center></html>";
            comb_infoAcceptWebView.getEngine().loadContent(html);

            String mainHtml = "<html></html>";
            comb_webView.getEngine().loadContent(mainHtml);

        });

        comb_webView.getEngine().setUserStyleSheetLocation("data:,body { font: 16px Candara; }");
        comb_infoAcceptWebView.getEngine().setUserStyleSheetLocation("data:,body { font: 16px Candara; }");
        comb_infoAcceptWebView.setContextMenuEnabled(false);
        comb_webView.setContextMenuEnabled(false);
    }

    @FXML
    public void manual(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/setlab/fxml/ManualWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("SetLab Manual");
        stage.setResizable(true);
        stage.initModality(Modality.NONE);
        stage.setScene(scene);
        stage.show();
    }

    private void initializaImage() {
        imageFormula_a_mn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_a_mn.png"));
        imageFormula_amn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_amn.png"));
        imageFormula_c_mn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_c_mn.png"));
        imageFormula_cmn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_cmn.png"));
        imageFormula_p = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_p.png"));
        imageFormula_pk = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_pk.png"));
    }

    private void initializeFields(){
        
        comb_btnEnter.disableProperty().bind(comb_fieldM.textProperty().isEmpty().or(comb_fieldN.textProperty().isEmpty()));
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
     */
}
