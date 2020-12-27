package main.java.com.byteshift.calculatorjfx.util;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.com.byteshift.calculatorjfx.calculatorjfx.CalculatorJFX;

/**
 *
 * @author Nacer Salah Eddine
 */
/**
 * Various utilities for applying different effects to nodes.
 */
public class EffectUtilities {

    /**
     * makes a stage draggable using a given node
     *
     * @param stage
     * @param byNode
     */
    private static Label maximizeBtn;

    private static void maximizeWind(Stage stage, Rectangle2D bounds) {
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        if (maximizeBtn != null) {
            Platform.runLater(() -> {
                maximizeBtn.getTooltip().setText("Restore Down...");

            });
        }
    }

    private static void minimizeWind(Stage stage, Rectangle2D bounds) {
        stage.setWidth(CalculatorJFX.WIND_WIDTH);
        stage.setHeight(CalculatorJFX.WIND_HEIGHT);
        stage.centerOnScreen();
        if (maximizeBtn != null) {
            Platform.runLater(() -> {
                maximizeBtn.getTooltip().setText("Maximize...");

            });
        }
    }

    public static void makeDraggable(final Stage stage, final Node byNode, Label maxBtn) {
        final Delta dragDelta = new Delta();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        maximizeBtn = maxBtn;
        byNode.setOnMouseClicked((MouseEvent mouseEvent) -> {

            if (mouseEvent.getClickCount() == 2) {
                if (bounds.getWidth() == stage.getWidth()
                        && bounds.getHeight() == stage.getHeight()) {
                    minimizeWind(stage, bounds);
                } else {

                    maximizeWind(stage, bounds);
                }

            }
        });
        byNode.setOnMousePressed((MouseEvent mouseEvent) -> {

            if (bounds.getWidth() == stage.getWidth()
                    && bounds.getHeight() == stage.getHeight()) {

                double xn = (CalculatorJFX.WIND_WIDTH) / (bounds.getWidth() / mouseEvent.getSceneX());
                double yn = (CalculatorJFX.WIND_HEIGHT) / (bounds.getHeight() / mouseEvent.getSceneY());
                dragDelta.x = stage.getX() - xn;
                dragDelta.y = stage.getY() - yn;
            } else {

                dragDelta.x = stage.getX() - mouseEvent.getScreenX();
                dragDelta.y = stage.getY() - mouseEvent.getScreenY();

            }

            byNode.setCursor(Cursor.MOVE);

        });
        byNode.setOnMouseReleased((MouseEvent mouseEvent) -> {
            byNode.setCursor(Cursor.HAND);
            stage.setOpacity(1.0f);
        });
        byNode.setOnMouseDragged((MouseEvent mouseEvent) -> {

            if (bounds.getWidth() == stage.getWidth()
                    && bounds.getHeight() == stage.getHeight()) {
                minimizeWind(stage, bounds);
            }
            stage.setOpacity(0.88f);
            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
        byNode.setOnMouseEntered((MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                byNode.setCursor(Cursor.HAND);
            }
        });
        byNode.setOnMouseExited((MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                byNode.setCursor(Cursor.DEFAULT);
            }
        });

    }

    public static void makeSimpleDraggable(final Stage stage, final Node byNode) {
        final Delta dragDelta = new Delta();

        byNode.setOnMousePressed((MouseEvent mouseEvent) -> {

            dragDelta.x = stage.getX() - mouseEvent.getScreenX();
            dragDelta.y = stage.getY() - mouseEvent.getScreenY();

            byNode.setCursor(Cursor.MOVE);

        });
        byNode.setOnMouseReleased((MouseEvent mouseEvent) -> {
            byNode.setCursor(Cursor.HAND);
            stage.setOpacity(1.0f);

        });
        byNode.setOnMouseDragged((MouseEvent mouseEvent) -> {
            stage.setOpacity(0.88f);
            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
        byNode.setOnMouseEntered((MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                byNode.setCursor(Cursor.HAND);
            }
        });
        byNode.setOnMouseExited((MouseEvent mouseEvent) -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                byNode.setCursor(Cursor.DEFAULT);
            }
        });

    }

    /**
     * records relative x and y co-ordinates.
     */
    private static class Delta {

        double x, y;
    }
}
