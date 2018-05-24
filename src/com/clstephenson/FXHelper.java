package com.clstephenson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXHelper {
    public static Stage getStageFromActionEvent(ActionEvent event) {
        Node srcNode = (Node)event.getSource();
        return (Stage)srcNode.getScene().getWindow();
    }

    public static Stage getStageFromKeyEvent(KeyEvent event) {
        Node srcNode = (Node)event.getSource();
        return (Stage)srcNode.getScene().getWindow();
    }

    public static Stage getStageFromNode(Node node) {
        return (Stage)node.getScene().getWindow();
    }

    public static void exitApplication() {
        System.out.println("close application requested...");
        Platform.exit();
        System.exit(0);
    }
}
