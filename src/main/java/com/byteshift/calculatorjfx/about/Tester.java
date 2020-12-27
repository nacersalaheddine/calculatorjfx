package main.java.com.byteshift.calculatorjfx.about;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.byteshift.calculatorjfx.util.Utility;

public class Tester extends Application {

    public static final String STYLE_SHEET_LOCATION = "/main/java/com/byteshift/calculatorjfx/about/styles/styles.css";

    /*
    public static String styleSheet = Tester.class.getResource(STYLE_SHEET_LOCATION)
            .toExternalForm();
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // String styleSheet = this.getClass().getResource(STYLE_SHEET_LOCATION).toExternalForm();
        String styleSheet = this.getClass().getResource(STYLE_SHEET_LOCATION).toExternalForm();
        System.out.println(STYLE_SHEET_LOCATION);
        /* 
      Code for JavaFX application. 
      (Stage, scene, scene graph) 
         */
        //creating a Group object 
        Group group = new Group();

        //Creating a Scene by passing the group object, height and width   
        Scene scene = new Scene(group, 600, 300);

        //setting color to the scene 
        scene.setFill(Color.BROWN);

        //Setting the title to Stage. 
        primaryStage.setTitle("Sample Application");

        //Adding the scene to Stage 
        primaryStage.setScene(scene);

        //Displaying the contents of the stage 
        primaryStage.show();
        Platform.runLater(() -> {
            // regularExpressionsLibraryController = (RegularExpressionsLibraryController) 

            Utility.loadWindow(
                    getClass().getResource("/main/java/com/byteshift/calculatorjfx/about/about.fxml"), "about", StageStyle.UNDECORATED, null, null,
                    styleSheet);
            // regularExpressionsLibraryController.setMainContrllr(regularExpressionInputTF);
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
