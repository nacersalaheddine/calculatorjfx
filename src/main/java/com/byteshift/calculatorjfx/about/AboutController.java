package main.java.com.byteshift.calculatorjfx.about;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.com.byteshift.calculatorjfx.util.EffectUtilities;

public class AboutController implements Initializable {

    @FXML
    private StackPane root;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        Platform.runLater(() -> {
            EffectUtilities.makeSimpleDraggable(((Stage) root.getScene().getWindow()), root);

        });
    }

    @FXML
    private void byteShiftBtnOnAction(ActionEvent event) {

        String url = WebLinks.PERSONAL_SITE.getKey();
        showPrompt(url);
    }

    @FXML
    private void facebookBtnOnAction(ActionEvent event) {

        String url = WebLinks.FACEBOOK_LINK.getKey();
        showPrompt(url);
    }

    @FXML
    private void dribbleBtnOnAction(ActionEvent event) {
        String url = WebLinks.DRIBBLE_LINK.getKey();
        showPrompt(url);
    }

    @FXML
    private void githubBtnOnAction(ActionEvent event) {
        String url = WebLinks.GITHUB_LINK.getKey();
        showPrompt(url);
    }

    @FXML
    private void linkedinBtnOnAction(ActionEvent event) {

        String url = WebLinks.LINKEDIN_LINK.getKey();

        showPrompt(url);
    }

    @FXML
    private void sourceCodeLocationLinkOnAction(ActionEvent event) {
        String url = WebLinks.REPO_GITHUB_LINK.getKey();
        showPrompt(url);
    }

    @FXML
    private void logoImgSiteLinkOnAction(ActionEvent event) {

        String url = WebLinks.PERSONAL_SITE.getKey();//www.byteshift-tech.com
        showPrompt(url);
    }

    void showPrompt(String url) {

        try {

            if (AlertMaker.showConfirmationAlert("Visit Link", "Visit " + url, "", (Stage) root.getScene().getWindow())) {
                Utils.openBrowserLink(url);

            }
        } catch (Exception ex) {
            Utils.ExceptionLogger(AboutController.class.getName(), "<Method:logoImgSiteLinkOnAction>", ex);
        }
    }

    @FXML
    private void mailLinkOnAction(ActionEvent event) {
        String mailStr = ((Hyperlink) event.getSource()).getText();
        //Copy to clipboard
        Utils.copyToClipboardText(mailStr);
        AlertMaker.showSimpleAlert("Clipboard Copy", "Email \"" + mailStr + "\" Copied to Clipboard!", (Stage) root.getScene().getWindow());
    }

    @FXML
    private void closeMouseClickHandler(MouseEvent event) {
        Platform.runLater(() -> {
            ((Stage) root.getScene().getWindow()).close();
        });
    }

}
