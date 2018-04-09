package setlab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import setlab.SetLab;
import setlab.cores.BinRelCore.BinRel;
import setlab.cores.SetCore.SetObj;
import setlab.s.BinRelTypesGraph;

public class MainWindowController implements Initializable {

    @FXML
    private Tab tab_set;

    @FXML
    private ListView<String> set_listView;

    @FXML
    private TextArea set_area;

    @FXML
    private TextField set_field;

    @FXML
    private Button set_btnEnter;

    @FXML
    private Button set_op_union;

    @FXML
    private Button set_op_inter;

    @FXML
    private Button set_op_diff;

    @FXML
    private Button set_op_symmdiff;

    @FXML
    private Button set_btn_add;

    @FXML
    private Button set_btn_remove;

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
    private Slider binRel_sliderForCanvas;

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
    private ImageView comb_imageViewReset;

    @FXML
    private WebView comb_infoAcceptWebView;

    private Image imageReset;
    private Image imageYeah;
    private Image imageNope;
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

    public static HashMap<String, SetObj> MapOfSets = new HashMap<>();
    private static ObservableList obsList = FXCollections.observableArrayList();
    public static BinRel bufferedBinRel;
    private static byte comb_typeFunc;
    private static HashMap<Integer, ImageView> MapOfImageView = new HashMap<>();
    private static GraphicsContext context;
    SimpleStringProperty string = new SimpleStringProperty();
    private static String set_PrefTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializaImage();
        initializeMapOfImageView();
        initializeSet();
        initializeBinRel();
        initializeComb();
        initializeFields();
    }

    @FXML
    public void set_getCommand() {
        if (!set_field.getText().isEmpty()) {
            set_area.appendText(SintaxSet.get(set_field.getText()));
            set_field.setText("");
        }
    }

    @FXML
    public void binrel_getCommand() {
        if (!binrel_field.getText().isEmpty()) {
            String command = binrel_field.getText();
            if (command.equals("clear")) {
                binrel_area.setText("");
                binrel_field.setText("");
            } else if (BinRelTypesGraph.contain(command)) {
                bufferedBinRel = BinRelTypesGraph.get(command);
                BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
            } else {
                String[] line = command.replaceAll(" ", "").split("=");
                bufferedBinRel = new BinRel(line[0], line[1]);
                BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
                binrel_area.setText(SintaxBinRel.get(command, bufferedBinRel));

                for (int i = 0; i < SintaxBinRel.internals.length; i++) {
                    setImageToTable(i, SintaxBinRel.internals[i]);
                }
            }
        }
    }

    @FXML
    public void comb_getCommand() {
        if (!comb_btnEnter.isDisable()) {
            // https://community.oracle.com/message/11243184#11243184  ------------ it is not crutch!
            int[] n = Arrays.stream(comb_fieldN.getText().split(",")).mapToInt(Integer::parseInt).toArray();
            int m = "".equals(comb_fieldM.getText()) ? 0 : Integer.valueOf(comb_fieldM.getText());
            StringBuilder html = new StringBuilder().append("<html>");
            html.append("<head>");
            html.append("   <script language=\"javascript\" type=\"text/javascript\">");
            html.append("       function toBottom(){");
            html.append("           window.scrollTo(0, document.body.scrollHeight);");
            html.append("       }");
            html.append("   </script>");
            html.append("</head>");
            html.append("<body onload='toBottom()'>");
            switch (comb_typeFunc) {
                case 1:
                    m = 0;
                default:
                    comb_webView.getEngine().loadContent(html.toString() + CombSolutionCore.get(comb_typeFunc, n, m) + "</body></html>");
                    break;
            }
            comb_fieldN.setText("");
            comb_fieldM.setText("");
        }
    }

    private void initializeSet() {
        set_listView.setItems(obsList);
        set_area.setFont(Setting.FONT);
        set_field.setFont(Setting.FONT);
        set_op_union.setFont(Setting.FONT);
        set_op_inter.setFont(Setting.FONT);
        set_op_diff.setFont(Setting.FONT);
        set_op_symmdiff.setFont(Setting.FONT);
        // Enter
        set_field.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                set_getCommand();
            }
        });
        set_btnEnter.setOnMouseClicked((event) -> {
            set_getCommand();
        });
        // func button 1
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
        // func button 2
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
        // func button 3
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
        // func button 4
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
        // add closer )
        set_field.setOnKeyTyped((event) -> {
            if (event.getCharacter().equals("(")) {
                String t = set_field.getText(), leftRes, rigthRes;
                int pos = set_field.getCaretPosition();
                leftRes = t.substring(0, pos);
                rigthRes = t.substring(pos);
                set_field.setText(leftRes + ")" + rigthRes);
                set_field.positionCaret(pos);
            }
        });
        set_btn_remove.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (set_listView.getSelectionModel().getSelectedIndex() != -1) {
                    MapOfSets.remove(set_listView.getSelectionModel().getSelectedItem());
                    set_listView.getItems().remove(set_listView.getSelectionModel().getSelectedIndex());
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setContentText("Не выбрано множество");
                    alert.showAndWait();
                }
            }
        });
        set_btn_add.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {

            }
        });
    }

    private void initializeBinRel() {
        bufferedBinRel = new BinRel("R", "{}");
        binrel_paneCanvas.setStyle("-fx-background-color: #585858");
        context = binrel_canvas.getGraphicsContext2D();
        BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
        binrel_area.setFont(Font.font(15));

        binRel_sliderForCanvas.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = binRel_sliderForCanvas.valueProperty().doubleValue();
            binRel_sliderForCanvas.valueProperty().set(Math.round(value / 5) * 5);
            BinRel_GraphicsGraphCore.setAngle((float) binRel_sliderForCanvas.getValue());
            BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
        });
        // Reset angle
        binRel_sliderForCanvas.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                binRel_sliderForCanvas.valueProperty().set(0);
            }
        });
        // Scroll of slider
        binRel_sliderForCanvas.setOnScroll((event) -> {
            binRel_sliderForCanvas.valueProperty().set(binRel_sliderForCanvas.valueProperty().intValue() + event.getDeltaY() / 8);
        });
        // Zoom on Canvas
        binrel_canvas.setOnScroll((event) -> {
            BinRel_GraphicsGraphCore.changeZoom((float) (event.getDeltaY() / 40));
            BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
        });
        // Reset zoom of canvas
        binrel_canvas.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                BinRel_GraphicsGraphCore.resetZoom();
                BinRel_GraphicsGraphCore.Render(binrel_canvas, bufferedBinRel);
            }
        });
        // Analisis of datas
        binrel_field.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                binrel_getCommand();
            }
        });
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
                    comb_fieldN.setPromptText("n");
                    comb_fieldM.setPromptText("");
                    initializeFieldMask();
                    comb_fieldM.setDisable(true);

                    comb_typeFunc = 1;
                    break;
                case "start;yeah;yeah;yeah":
                    massage = "Перестановки с повторениями из n элементов по m с заданой спецификацией";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_pk.getHeight());
                    comb_imageView.setFitWidth(imageFormula_pk.getWidth());
                    comb_imageView.setImage(imageFormula_pk);
                    comb_fieldN.setPromptText("k1, k2 ... kn");
                    comb_fieldM.setPromptText("m");
                    initializeFieldMask();
                    comb_fieldM.setDisable(false);
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
                    comb_fieldN.setPromptText("n");
                    comb_fieldM.setPromptText("m");
                    initializeFieldMask();
                    comb_fieldM.setDisable(false);
                    comb_typeFunc = 3;
                    break;
                case "start;yeah;nope;nope":
                    massage = "Размещения без повторений из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_a_mn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_a_mn.getWidth());
                    comb_imageView.setImage(imageFormula_a_mn);
                    comb_fieldN.setPromptText("n");
                    comb_fieldM.setPromptText("m");
                    initializeFieldMask();
                    comb_fieldM.setDisable(false);
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
                    comb_fieldN.setPromptText("n");
                    comb_fieldM.setPromptText("m");
                    initializeFieldMask();
                    comb_fieldM.setDisable(false);
                    comb_typeFunc = 5;
                    break;
                case "start;nope;nope":
                    massage = "Сочетания без повторений из n элементов по m";
                    listenerBack.set(false);
                    listener.set(true);
                    comb_imageView.setFitHeight(imageFormula_c_mn.getHeight());
                    comb_imageView.setFitWidth(imageFormula_c_mn.getWidth());
                    comb_imageView.setImage(imageFormula_c_mn);
                    comb_fieldN.setPromptText("n");
                    comb_fieldM.setPromptText("m");
                    initializeFieldMask();
                    comb_fieldM.setDisable(false);
                    comb_typeFunc = 6;
                    break;
                default:
                    System.out.println("Ошибка почему-то");
            }
            initializeFields();
            String html = "<html><br><br><center>" + massage + "</center></html>";
            comb_infoAcceptWebView.getEngine().loadContent(html);
        });

        comb_btnEnter.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                comb_getCommand();
            }
        });
        comb_fieldN.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                comb_getCommand();
            }
        });
        comb_fieldM.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                comb_getCommand();
            }
        });

        comb_imageViewReset.setFitHeight(24);
        comb_imageViewReset.setFitWidth(24);
        comb_imageViewReset.setImage(imageReset);
        comb_imageViewReset.setOnMouseClicked((event) -> {
            string.set("start");
            comb_imageView.setImage(null);
        });

        comb_webView.getEngine().setUserStyleSheetLocation("data:,body { font: 16px Tahoma; }");
        comb_infoAcceptWebView.getEngine().setUserStyleSheetLocation("data:,body { font: 16px Tahoma; }");
        comb_infoAcceptWebView.setContextMenuEnabled(false);
        comb_webView.setContextMenuEnabled(false);
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

        comb_fieldM.setText("");
        comb_fieldN.setText("");
        comb_fieldM.setPromptText("");
        comb_fieldN.setPromptText("");
    }

    private void initializaImage() {
        imageFormula_a_mn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_a_mn.png"));
        imageFormula_amn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_amn.png"));
        imageFormula_c_mn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_c_mn.png"));
        imageFormula_cmn = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_cmn.png"));
        imageFormula_p = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_p.png"));
        imageFormula_pk = new Image(SetLab.class.getResourceAsStream("fxml/icon/formula_pk.png"));

        imageReset = new Image(SetLab.class.getResourceAsStream("fxml/icon/reset.png"));
        imageYeah = new Image(SetLab.class.getResourceAsStream("fxml/icon/accept.png"));
        imageNope = new Image(SetLab.class.getResourceAsStream("fxml/icon/dismiss.png"));
    }

    private void initializeFieldMask() {
        Pattern patternForNumber = Pattern.compile("[\\d]{0,}");

        comb_fieldM.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!Pattern.compile("[\\d]{0,}").matcher(newValue).matches()) {
                    comb_fieldM.setText(oldValue);
                }
            }
        });

        comb_fieldN.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!"k1, k2 ... kn".equals(comb_fieldN.getPromptText()) && !Pattern.compile("[\\d]{0,}").matcher(newValue).matches()) {
                    comb_fieldN.setText(oldValue);
                }
                if ("k1, k2 ... kn".equals(comb_fieldN.getPromptText()) && !Pattern.compile("[\\d]{1,}[[,]{0,1}[\\d]{0,}]{0,}").matcher(newValue).matches() || Pattern.compile("[,]{2}").matcher(newValue).find()) {
                    comb_fieldN.setText(oldValue);
                }
            }
        });

    }

    private void initializeFields() {
        comb_btnEnter.disableProperty().bind(comb_fieldM.disableProperty().get() ? comb_fieldN.textProperty().isEmpty() : comb_fieldN.textProperty().isEmpty().or(comb_fieldM.textProperty().isEmpty()));
    }

    private void initializeMapOfImageView() {
        MapOfImageView.put(0, ImageViewReflex);
        MapOfImageView.put(1, ImageViewAntiReflex);
        MapOfImageView.put(2, ImageViewBidirect);
        MapOfImageView.put(3, ImageViewAntiBidirect);
        MapOfImageView.put(4, ImageViewAsBidirect);
        MapOfImageView.put(5, ImageViewTransitive);
    }

    private void setImageToTable(int i, boolean b) {
        if (b) {
            MapOfImageView.get(i).setImage(imageYeah);
        } else {
            MapOfImageView.get(i).setImage(imageNope);
        }
    }

    public static void addNewSet(SetObj newObj) {

        MapOfSets.put(newObj.name, newObj);
        if (obsList.contains(newObj.name)) {
            obsList.remove(newObj.name);
        }
        obsList.add(newObj.name);
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

    @FXML
    public void aboutProgram(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("");
        alert.setContentText("Авторы: \n"
                + "\tСтуденты ХНЭУ им. С. Кузнеца\n"
                + "\tБогдан Бида, Эдуард Белоусов\n"
                + "\t(bogdanbida.ua@gmail.com),(edikbelousov@gmail.com)\n"
                + "\tSetLab " + Setting.VERSION + "\n"
                + "Написан на JavaFX" + "\n"
                + "\t08.04.2018");

        alert.showAndWait();
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
