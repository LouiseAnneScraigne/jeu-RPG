package com.isep.jeu_rpg;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {

    @FXML
    private VBox rootVBox;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        System.out.println(MainApplication.class.getResource("game-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader
                (MainApplication.class.getResource("initPerso-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainApplication.stage.setScene(scene);
        MainApplication.stage.show();
    }

    @FXML
    protected void onExitButton(){
        Stage stage = (Stage) rootVBox.getScene().getWindow();
        stage.close();
    }

}