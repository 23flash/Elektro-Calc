package com.example.elektrocalc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField U;

    @FXML
    private TextField I;

    @FXML
    private TextField V;

    @FXML
    private Button Calc;

    @FXML
    private void handleCalcButtonAction() {
        String uValue = U.getText();
        String iValue = I.getText();
        String vValue = V.getText();

        // Add your logic here to handle the values
        System.out.println("U: " + uValue);
        System.out.println("I: " + iValue);
        System.out.println("V: " + vValue);

        // Example: convert to double and calculate something
        try {
            double u = Double.parseDouble(uValue);
            double i = Double.parseDouble(iValue);
            double v = Double.parseDouble(vValue);

            // Example calculation
            double result = u + i + v;
            System.out.println("Result: " + result);

        } catch (NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}
