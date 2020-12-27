package main.java.com.byteshift.calculatorjfx.calculatorjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.byteshift.calculatorjfx.util.Utility;

/**
 *
 * @author Nacer Salah Eddine
 */
public class CalculatorJFX extends Application {

    public static final String IMAGE_LOC = "/main/java/com/byteshift/calculatorjfx/res/Calculator-icon.png";
    public static final String STYLE_SHEET_LOCATION = "/main/java/com/byteshift/calculatorjfx/1/calc.css";
    public static final String GUI = "/main/java/com/byteshift/calculatorjfx/1/calculator.fxml";
    public static FXMLLoader fxmlLoader;
    public static final int WIND_WIDTH = 518;
    public static final int WIND_HEIGHT = 646;
    private Font oswaldFont;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader = new FXMLLoader(getClass().getResource(GUI));
        root = fxmlLoader.load();
        String cssSheet = this.getClass().getResource(STYLE_SHEET_LOCATION).toExternalForm();
        CalculatorController calculatorController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssSheet);
        stage.setOnCloseRequest(e -> {

            Utility.loadWindow(getClass().getResource("/main/java/com/byteshift/calculatorjfx/exit_alert/exit_alert.fxml"),
                    "Sure you want to exit?", StageStyle.UTILITY,
                    null, stage,
                    "/main/java/com/byteshift/calculatorjfx/exit_alert/exit_alert.css");

            e.consume();

        });

        calculatorController.setStage(stage);
        stage.setResizable(true);
        stage.setScene(scene);

        stage.setWidth(WIND_WIDTH);
        stage.setHeight(WIND_HEIGHT);
        stage.initStyle(StageStyle.UNDECORATED);
        Utility.setStageIcon(stage);
        //setResizable(root, stage);
        stage.show();

    }

    /*
    This methode is used as a base code for making undecorated window 
    resisable
     */
 /*
    private Boolean resizebottom = false;
    private double dx;
    private double dy;
    private double xOffset;
    private double yOffset;
    private double padd = 2;
    
     */
 /*
    void setResizable(Parent root, Stage stage) {

        stage.getScene().setOnMousePressed((MouseEvent event) -> {
            if (event.getX() > stage.getWidth() - 10
                    && event.getX() < stage.getWidth() + 10
                    && event.getY() > stage.getHeight() - 10
                    && event.getY() < stage.getHeight() + 10) {
                resizebottom = true;
                dx = stage.getWidth() - event.getX();
                dy = stage.getHeight() - event.getY();
            } else {
                resizebottom = false;
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }

            //  dx = stage.getWidth() - event.getX();
            //  dy = stage.getHeight() - event.getY();
        });
        stage.getScene().setOnMouseDragged((MouseEvent event) -> {

            /// stage.setWidth(event.getX() + dx);
            //  stage.setHeight(event.getY() + dy);
            if (resizebottom == false) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            } else {
                stage.setWidth(event.getX() + dx);
                stage.setHeight(event.getY() + dy);
            }
        });
        //this code shows the appropriate cursor on the corners and in sides
        stage.getScene().setOnMouseMoved((MouseEvent event) -> {
            if (!event.isPrimaryButtonDown()) {
                if ((event.getX() <= padd) && (event.getY() <= padd)
                        || ((event.getX() >= (stage.getWidth() - padd)) && (event.getY() >= (stage.getHeight() - padd)))) {

                    root.setCursor(Cursor.SE_RESIZE);
                } else if ((event.getX() <= padd) && (event.getY() >= (stage.getHeight() - padd))
                        || ((event.getX() >= (stage.getWidth() - padd)) && (event.getY() <= padd))) {

                    root.setCursor(Cursor.SW_RESIZE);
                } else if ((event.getX() <= padd) || (event.getX() >= (stage.getWidth() - padd))) {

                    root.setCursor(Cursor.E_RESIZE);
                } else if ((event.getY() <= padd) || (event.getY() >= (stage.getHeight() - padd))) {

                    root.setCursor(Cursor.N_RESIZE);
                } else {
                    root.setCursor(Cursor.DEFAULT);
                }

            }

        });
        stage.getScene().setOnMouseExited((MouseEvent event) -> {
            if (!event.isPrimaryButtonDown()) {
                root.setCursor(Cursor.DEFAULT);
            }

        });
    }
     */
    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
