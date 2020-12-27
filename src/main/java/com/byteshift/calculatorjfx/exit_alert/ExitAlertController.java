package main.java.com.byteshift.calculatorjfx.exit_alert;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Nacer Salah Eddine
 */
public class ExitAlertController implements Initializable {

    @FXML
    private VBox root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCloseEvent(WindowEvent e) {

    }

    @FXML
    private void closeOnActionHandler() throws Exception {

        System.exit(0);

    }

    @FXML
    private void cancelCloseOnActionHandler() {
        ((Stage) root.getScene().getWindow()).close();
        System.out.println("not closing");

    }
}
