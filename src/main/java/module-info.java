module com.isep.jeu_rpg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.isep.jeu_rpg to javafx.fxml;
    exports com.isep.jeu_rpg;
}