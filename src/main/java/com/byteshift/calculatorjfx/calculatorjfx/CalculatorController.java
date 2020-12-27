package main.java.com.byteshift.calculatorjfx.calculatorjfx;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.java.com.byteshift.calculatorjfx.calculator_engine.CalculatorEngine;
import main.java.com.byteshift.calculatorjfx.util.EffectUtilities;
import main.java.com.byteshift.calculatorjfx.util.Utility;
import main.java.com.byteshift.calculatorjfx.watch.DigitalWatch;

/**
 *
 * @author Nacer Salah Eddine
 */
public class CalculatorController implements Initializable {

    @FXML
    private StackPane root, calcHeader;
    @FXML
    private Label lblOperationResult, lblOperationsHistory, maximizeBtn;
    @FXML
    private JFXButton clockBtn;

    public Stage mainStage;
    private final String STYLE_SHEET_LOCATION = "/main/java/com/byteshift/calculatorjfx/about/styles/styles.css";
    private final String styleSheet = this.getClass().getResource(STYLE_SHEET_LOCATION).toExternalForm();
    public static int MAX_INPUT_NUMBERS = 16;
    public String currResultStr = "0";
    public Double result = null;
    public boolean newInput = true;
    public String operator;
    public String prevOperator;

    public StringBuilder historySb = new StringBuilder();
    public String spaceSep = "  ";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new DigitalWatch(clockBtn);
        Platform.runLater(() -> {
            EffectUtilities.makeDraggable(((Stage) root.getScene().getWindow()), calcHeader, maximizeBtn);
            root.getScene().addEventHandler(KeyEvent.KEY_PRESSED, createKeyPressEventHandler());

        });

    }

    private void writeOperand(String inpt) {
        if (!this.newInput) {
            this.newInput = true;
            lblOperationResult.setText("0");
            this.prevOperator = this.operator;
        }

        this.currResultStr = unformatInput(lblOperationResult.getText());

        String unformattedStr = this.currResultStr;

        if (getInptLength(unformattedStr) < MAX_INPUT_NUMBERS) {

            if (unformattedStr.equals("0")) {
                lblOperationResult.setText("");
                unformattedStr = "";
            }

            String express = "".equals(unformattedStr) ? inpt : unformattedStr + inpt;

            lblOperationResult.setText(formatInput(express));
            this.currResultStr = express;

        }
    }

    private void writeOperator(String operator) {
        if (getInptLength(currResultStr) <= MAX_INPUT_NUMBERS) {
            this.operator = operator;

            equalsOnActionHandler();
            //historySb.append(currResult).append(spaceSep).append(operator).append(spaceSep);
        }

    }

    @FXML
    private void equalsOnActionHandler() {
        if (this.newInput) {
            this.currResultStr = filterTrailingZeros(this.currResultStr);
            this.historySb.append(this.currResultStr).append(this.spaceSep).append(this.operator).append(this.spaceSep);
            this.newInput = false;

            if (this.result != null) {
                System.out.print(this.result + " " + this.prevOperator + " " + this.currResultStr + " = ");
                this.result = CalculatorEngine.makeStandardCalc(result, Double.parseDouble(this.currResultStr), this.prevOperator);
                System.out.println(this.result);
                lblOperationResult.setText(formatInput(result + ""));

            } else {
                this.result = Double.parseDouble(this.currResultStr);
            }

        } else {
            //3 depends on the number of spaces between the operands
            this.historySb.setCharAt(this.historySb.length() - 3, this.operator.charAt(0));

        }

        //
        lblOperationsHistory.setText(historySb.toString());
    }

    @FXML
    private void numberOnActionHandler(ActionEvent event) {
        String inpt = ((JFXButton) event.getSource()).getText();
        writeOperand(inpt);
    }

    private static String filterTrailingZeros(String result) {
        if (result.matches(".+(\\.)") || result.matches(".+(\\.)0+")) {
            //remove the trailing dot or sequence of zeros
            result = result.substring(0, result.indexOf("."));
        } else if (result.matches(".+0+")) {
            //ex: 12.050500000 ==> 12.0505
            return result.replaceAll("0+$", "");

        }
        return result;
    }

    private void deleteLastExpss() {
        int i = lblOperationResult.getText().length() - 1;
        if (i > 0) {
            String nexText = unformatInput(lblOperationResult.getText().substring(0, i));
            if (nexText.equals("-")) {
                lblOperationResult.setText("0");
            } else {
                lblOperationResult.setText(formatInput(nexText));
            }
            currResultStr = unformatInput(lblOperationResult.getText());
        } else if (i == 0) {
            lblOperationResult.setText("0");
            currResultStr = "0";
        }

    }

    //Returns the length of the input expression withought counting the . and/or -
    private int getInptLength(String s) {
        int len = s.length();
        if (s.contains(".")) {
            len--;
        }
        if (s.charAt(0) == '-') {
            len--;
        }
        return len;
    }

    @FXML
    private void signOperatorOnActionHandler(ActionEvent event) {

        String inptStr = unformatInput(lblOperationResult.getText());
        if (getInptLength(inptStr) <= MAX_INPUT_NUMBERS && !inptStr.equals("0")) {

            String s = inptStr.charAt(0) == '-'
                    ? inptStr.substring(1, inptStr.length()) : "-" + inptStr;

            lblOperationResult.setText(formatInput(s));
            currResultStr = unformatInput(s);
        }
    }

    @FXML
    private void deleteOnActionHandler(ActionEvent event) {
        deleteLastExpss();
    }

    @FXML
    private void dotOnActionHandler() {
        if (!lblOperationResult.getText().contains(".")) {
            //writeOperand(".");

            String express = unformatInput(lblOperationResult.getText()) + ".";
            lblOperationResult.setText(formatInput(express));
        }
    }

    /**
     * **********************RESULT********************************
     */
    private String formatInput(String s) {

        String[] splt = s.split("\\.");
        String newS = splt[0];
        StringBuilder sb = null;
        int len = newS.length();
        int i = 0;
        if (s.charAt(0) == '-') {
            i++;
        }

        if (len - i >= 4) {
            sb = new StringBuilder();
            char[] sc = newS.toCharArray();

            for (; i < len; i++) {
                sb.append(sc[i]);
                if ((len - i - 1) % 3 == 0 && ((len - i - 1) > 0)) {
                    sb.append(",");
                }

            }

        }

        if (sb != null) {
            if (splt.length > 1) {
                sb.append(".").append(splt[1]);
            } else if (s.charAt(s.length() - 1) == '.') {
                sb.append(".");
            }
            if (s.charAt(0) == '-') {
                sb.insert(0, "-");
            }

            //System.out.println(sb);
            return sb.toString();
        } else {

            return s;
        }
    }

    private String unformatInput(String s) {
        return s.replaceAll(",", "");
    }

    /**
     * **********************HISTORY********************************
     */
    private String formatHistory(String s) {

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);

        }
        //sets spaces between operands
        return sb.toString();
    }

    private String unformatHistory() {
        StringBuilder sb = new StringBuilder();
        for (char c : lblOperationsHistory.getText().toCharArray()) {
            if (c != ' ') {
                sb.append(c);
            }

        }
        return sb.toString();//removes all spaces between operands
    }

    /**
     * ************************************************************
     */
    @FXML
    private void operatorOnActionHandler(ActionEvent event) {
        writeOperator(((JFXButton) event.getSource()).getText());
    }

    @FXML
    private void allClearOnActionHandler() {
        lblOperationResult.setText("0");
        lblOperationsHistory.setText("");
        currResultStr = "0";
        this.result = null;
        historySb = new StringBuilder();

    }

    /**
     * **********************************************************************
     */
    @FXML
    private void clearOnActionHandler() {
        lblOperationResult.setText("0");
        currResultStr = "0";
    }

    @FXML
    private void closeMouseClickHandler(MouseEvent event) {
        //these tow close events will close the window but will not call the setOnCloseRequest because its an internal call for close
        //this.mainStage.close();
        //System.exit(0);
        this.mainStage.fireEvent(new WindowEvent(this.mainStage, WindowEvent.WINDOW_CLOSE_REQUEST));

    }

    @FXML
    private void minimizeMouseClickHandler(MouseEvent event) {
        this.mainStage.setIconified(true);
    }

    @FXML
    private void maximizeMouseClickHandler(MouseEvent event) {
        //((Stage) root.getScene().getWindow()).setFullScreen(true);
        //mainStage.setMaximized(true);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        if (this.mainStage.getWidth() == bounds.getWidth()
                && this.mainStage.getHeight() == bounds.getHeight()) {
            this.mainStage.setWidth(CalculatorJFX.WIND_WIDTH);
            this.mainStage.setHeight(CalculatorJFX.WIND_HEIGHT);

            maximizeBtn.getTooltip().setText("Maximize...");
            this.mainStage.centerOnScreen();
        } else {

            this.mainStage.setX(bounds.getMinX());
            this.mainStage.setY(bounds.getMinY());
            this.mainStage.setWidth(bounds.getWidth());
            this.mainStage.setHeight(bounds.getHeight());

            maximizeBtn.getTooltip().setText("Restore Down...");
        }

    }

    @FXML
    private void showAboutBtnAction(ActionEvent event) throws Exception {
        Platform.runLater(() -> {
            Utility.loadWindow(
                    getClass().getResource("/main/java/com/byteshift/calculatorjfx/about/about.fxml"), "about", StageStyle.UNDECORATED, null, this.mainStage,
                    styleSheet);

        });

    }

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    private EventHandler createKeyPressEventHandler() {

        return (EventHandler<KeyEvent>) (KeyEvent event) -> {

            if (null != event.getCode()) {
                switch (event.getCode()) {
                    case NUMPAD0:
                    case DIGIT0:

                        writeOperand("0");
                        break;
                    case NUMPAD1:
                    case DIGIT1:

                        writeOperand("1");
                        break;
                    case NUMPAD2:
                    case DIGIT2:

                        writeOperand("2");
                        break;
                    case NUMPAD3:
                    case DIGIT3:

                        writeOperand("3");
                        break;
                    case NUMPAD4:
                    case DIGIT4:

                        writeOperand("4");
                        break;
                    case NUMPAD5:
                    case DIGIT5:

                        writeOperand("5");
                        break;
                    case NUMPAD6:
                    case DIGIT6:

                        writeOperand("6");
                        break;
                    case NUMPAD7:
                    case DIGIT7:

                        writeOperand("7");
                        break;
                    case NUMPAD8:
                    case DIGIT8:

                        writeOperand("8");
                        break;
                    case NUMPAD9:
                    case DIGIT9:

                        writeOperand("9");
                        break;
                    case ESCAPE:
                        allClearOnActionHandler();
                        break;
                    case BACK_SPACE:
                        deleteLastExpss();
                        break;
                    case PERIOD:
                    case DECIMAL:
                        dotOnActionHandler();
                        break;
                    case ENTER:
                    case EQUALS:
                        equalsOnActionHandler();
                        break;
                    case AMPERSAND:
                        writeOperator("%");
                        break;
                    case C:
                        clearOnActionHandler();
                        break;
                    case MULTIPLY:
                    case ASTERISK:
                        writeOperator("×");
                        break;
                    case SUBTRACT:
                        writeOperator("-");
                        break;
                    case ADD:

                        writeOperator("+");
                        break;
                    case DIVIDE:
                        writeOperator("÷");
                        break;
                    default:
                        //System.out.println(">>" + event.getCode());
                        break;
                }
            }
            event.consume();
        };
    }

}
