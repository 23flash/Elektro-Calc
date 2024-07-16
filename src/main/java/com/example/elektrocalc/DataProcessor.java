package com.example.elektrocalc;

import javafx.scene.control.TextField;

public  class DataProcessor {
    public static double getDoubFromTextField(TextField Data) {
        double parsed = 0;
        try {
            parsed = Double.parseDouble(Data.getText());
        } catch (NumberFormatException e) {
            Data.setText("Invalid input");
        }
        return parsed;
    }
}
