package com.example.elektrocalc;

import javafx.scene.control.TextField;

public  class DataProcessor {
    //Input a Text Field and get its Content Converted to a Doubble
    public static double TextFieldToDouble(TextField Data) {
        double parsed = 0;
        try {
            parsed = Double.parseDouble(Data.getText());
        } catch (NumberFormatException e) {
            Data.setText("Invalid input");
        }
        return parsed;
    }
    public static String DoubleToString(double Data) {
        String parsed = "";
        try {
            parsed = Double.toString(Data);
        }catch (NumberFormatException e) {
            parsed = "Invalid input";
        }
        return parsed;
    }

}
