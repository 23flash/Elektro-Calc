package com.example.elektrocalc;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class Controller {
    @FXML
    private TextField I;

    @FXML
    private TextField U;

    @FXML
    private TextField V;

    @FXML
    private void handleCalcButtonAction() {

            double I_doub = DataProcessor.getDoubFromTextField(I);
            double U_doub = DataProcessor.getDoubFromTextField(U);
            double V_doub = DataProcessor.getDoubFromTextField(V);
            System.out.println(I_doub);
            System.out.println(U_doub);
            System.out.println(V_doub);
            System.out.println(V_doub + U_doub + I_doub);
            System.out.println("Screw You");

    }


}
