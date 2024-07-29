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
    /**
     * The main entry point for all JavaFX applications.
     * This method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException if an input or output exception occurred
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Sets the user agent stylesheet to a dark theme
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        // Loads the FXML file for the main application interface
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        // Sets the application icon
        Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("cat.png")));
        stage.getIcons().add(icon);
        // Sets the title of the application window
        stage.setTitle("Electro-Calc");
        // Sets the scene for the stage
        stage.setScene(scene);
        // Ensures the application window is always on top of other windows
        stage.setAlwaysOnTop(true);
        // Displays the stage
        stage.show();
    }
    /**
     * The main method which serves as the entry point for the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}