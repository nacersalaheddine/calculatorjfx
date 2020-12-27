package main.java.com.byteshift.calculatorjfx.exit_alert;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.byteshift.calculatorjfx.util.Utility;

public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

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
            Utility.loadWindow(getClass().getResource("/main/java/com/byteshift/calculatorjfx/exit_alert/exit_alert.fxml"),
                    "Sure you want to exit?", StageStyle.UTILITY,
                    null, primaryStage,
                    "/main/java/com/byteshift/calculatorjfx/exit_alert/exit_alert.css");

            // regularExpressionsLibraryController.setMainContrllr(regularExpressionInputTF);
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
