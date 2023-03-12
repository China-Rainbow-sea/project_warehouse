package com.bjpowernode.client.util;

import com.bjpowernode.client.MainLauncher;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

/**
 *  鼠标拖拽界面的处理
 */
public class Drag {
    private double xOffset;
    private double yOffset;

    public void handleDrag(BorderPane borderPane) {

        borderPane.setOnMousePressed(event -> {
            xOffset = MainLauncher.getPrimaryStage().getX() - event.getScreenX();
            yOffset = MainLauncher.getPrimaryStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            MainLauncher.getPrimaryStage().setX(event.getScreenX() + xOffset);
            MainLauncher.getPrimaryStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
    }
}
