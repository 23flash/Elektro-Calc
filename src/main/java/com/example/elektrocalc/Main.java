package com.example.elektrocalc;


import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("cat.png")));
        stage.getIcons().add(icon);

        stage.setTitle("Electro-Calc");
        stage.setScene(scene);
        //cant be hidden under other programs
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}