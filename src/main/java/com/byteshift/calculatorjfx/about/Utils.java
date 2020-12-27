package main.java.com.byteshift.calculatorjfx.about;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.awt.Desktop;

public class Utils {

    public static void openBrowserLink(String url) throws Exception {

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URL(url).toURI());

        } else {
            System.err.println("Desktop is not Supported sorry.");
            Runtime runtime = Runtime.getRuntime();

            String operatingSystemStr = System.getProperty("os.name").toLowerCase();
            if (operatingSystemStr.indexOf("win") >= 0) {
                System.err.println("Windows is not Supported sorry.");
            } else if (operatingSystemStr.indexOf("mac") >= 0) {
                System.err.println("Mac is not Supported sorry.");
            } else if (operatingSystemStr.indexOf("nix") >= 0 || operatingSystemStr.indexOf("nux") >= 0) {
                //For Linux / runtime.exec("xdg-open " + url);
                runtime.exec("xdg-open " + url);
                //System.out.println("Linux is not Supported sorry.");
            }

        }
    }

    public static void copyToClipboardText(String s) {

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(s);
        clipboard.setContent(content);

    }

    public static void copyToClipboardImage(Label lbl) {

        WritableImage snapshot = lbl.snapshot(new SnapshotParameters(), null);
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putImage(snapshot);
        clipboard.setContent(content);

    }

    public static void copyToClipboardImageFromFile(String path) {

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putImage(Utils.getImage(path));
        clipboard.setContent(content);

    }

    public static Image getImage(String path) {

        InputStream is = Utils.class.getResourceAsStream(path);
        return new Image(is);
    }

    public static ImageView setIcon(String path) {

        InputStream is = Utils.class.getResourceAsStream(path);
        ImageView iv = new ImageView(new Image(is));

        iv.setFitWidth(100);
        iv.setFitHeight(100);
        return iv;
    }

    public static void ExceptionLogger(String className, String msg, Exception ex) {
        //className parameter => className.class.getName()
        Logger.getLogger(className).log(Level.SEVERE, msg, ex);
    }

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ResourcesConstants.IMAGE_LOC));
    }

}
