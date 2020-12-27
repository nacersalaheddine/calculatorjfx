package main.java.com.byteshift.calculatorjfx.util;

import main.java.com.byteshift.calculatorjfx.calculatorjfx.CalculatorJFX;
import java.awt.Component;
import java.awt.Container;

import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nacer Salah Eddine
 */
public class Utility {

    private static final Logger LOG = Logger.getLogger(Utility.class.getName());

    public static Font loadFont(String loc) {
        Font font = null;

        // create the font to use. Specify the size!
        try {
            font = Font.loadFont(new FileInputStream(new File(loc)), 12);
        } catch (FileNotFoundException ex) {
            System.err.println("font not found");
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font;

    }

    public static float heightProp(float amount, float origW, float origH) {
        return (((origW - amount) * origH) / origW);
    }

    public static float widthProp(float amount, float origW, float origH) {
        return (((origH - amount) * origW) / origH);
    }

    public static String getFullDateTime() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        String weekDay = localDate.getDayOfWeek().toString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        return weekDay + "-" + month + "-" + day + "-" + year + " / " + hours + ":" + minutes + ":" + seconds;
    }

    

    public static Object loadWindow(URL loc, String title, StageStyle stageStyle, Stage parentStage,
            Stage owner, String styleSheet) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);

            Parent parent = loader.load();

            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;

            } else {
                stage = new Stage(stageStyle);
                stage.initOwner(owner);
            }
            stage.setTitle(title);
            Scene scene = new Scene(parent);

            stage.setScene(scene);

            if (styleSheet != null) {
                scene.getStylesheets().add(styleSheet);
            }
            Utility.setStageIcon(stage);

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return controller;
    }

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(CalculatorJFX.IMAGE_LOC));
    }

    public static void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
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

        content.putImage(Utility.getImage(path));
        clipboard.setContent(content);

    }

    public static javafx.scene.image.Image getImage(String path) {

        InputStream is = Utility.class.getResourceAsStream(path);
        return new javafx.scene.image.Image(is);
    }

    public static ImageView setIcon(String path) {

        InputStream is = Utility.class.getResourceAsStream(path);
        ImageView iv = new ImageView(new javafx.scene.image.Image(is));

        iv.setFitWidth(100);
        iv.setFitHeight(100);
        return iv;
    }

    public static void saveBinaryStreamAsFile(InputStream input, String path, String ext)
            throws FileNotFoundException, IOException {

        // write binary stream into file
        File file = new File(path + "." + ext);
        FileOutputStream fos = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        while (input.read(buffer) > 0) {
            fos.write(buffer);
        }

        fos.close();
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
