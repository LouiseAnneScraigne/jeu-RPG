package com.isep.jeu_rpg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Initialise game
        FXMLLoader fxmlLoader = new FXMLLoader
                (MainApplication.class.getResource("hello1-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainApplication.stage = stage;
        System.out.println(stage);
        stage.setTitle("Mini RPG!");
        stage.setScene(scene);
        stage.show();
    }


}