package com.example.elektrocalc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField I;

    @FXML
    private TextField U;

    @FXML
    private TextField V;

    @FXML
    private void handleCalcButtonAction() {
        System.out.println(I.getText());
        System.out.println(U.getText());
        System.out.println(V.getText());
        System.out.println("Screw You");
    }
}
