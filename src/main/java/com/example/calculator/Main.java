package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    double x, y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calculator-view.fxml"));
        Scene root = new Scene(fxmlLoader.load());
        stage.setTitle("Simple Calculator");
        stage.initStyle(StageStyle.UNDECORATED);


        root.setOnMousePressed(e ->{
            x = e.getSceneX();
            y = e.getSceneY();
        });


        root.setOnMouseDragged(e->{
            stage.setX(e.getScreenX() - x);
            stage.setY(e.getScreenY() - y);
        });
        stage.setScene(root);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}