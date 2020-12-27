package main.java.com.byteshift.calculatorjfx.watch;

import com.jfoenix.controls.JFXButton;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import main.java.com.byteshift.calculatorjfx.util.Utility;

/**
 *
 * @author Nacer Salah Eddine
 */
public class DigitalWatch implements Runnable {

    Thread t = null;
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";
    JFXButton btn;

    public DigitalWatch(JFXButton b) {
    
      
        this.btn = b;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        try {
            while (true) {

                Calendar cal = Calendar.getInstance();
                hours = cal.get(Calendar.HOUR_OF_DAY);
                if (hours > 12) {
                    hours -= 12;
                }
                minutes = cal.get(Calendar.MINUTE);
                seconds = cal.get(Calendar.SECOND);

                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                Date date = cal.getTime();
                timeString = formatter.format(date);

                printTime();

                t.sleep(1000);  // interval given in milliseconds  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTime() {
        Platform.runLater(() -> {
            this.btn.setText(timeString);
        });

    }
}
