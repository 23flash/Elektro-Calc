package com.example.elektrocalc;
import javafx.scene.control.TextField;
/**
 * Utility class for processing data between TextField and double representations.
 */
public  class DataProcessor {
    /**
     * Converts the text content of a TextField to a double.
     *
     * @param Data the TextField containing the text to be converted
     * @return the parsed double value, or 0 if the input is invalid
     */
    public static double TextFieldToDouble(TextField Data) {
        double parsed = 0;
        try {
            parsed = Double.parseDouble(Data.getText());
        } catch (NumberFormatException e) {
            Data.setText("Invalid input");
        }
        return parsed;
    }
    /**
     * Converts a double value to its string representation.
     *
     * @param Data the double value to be converted
     * @return the string representation of the double value, or "Invalid input" if conversion fails
     */
    public static String DoubleToString(double Data) {
        String parsed = "";
        try {
            parsed = String.format("%.3f", Data);
        }catch (NumberFormatException e) {
            parsed = "Invalid input";
        }
        return parsed;
    }
    public static String FormatStringToAnalyse(String data) {
        // Deletes double ==
        data = data.replace("==", "=");

        // Converts the string to lower case to handle case-insensitive replacements
        data = data.toLowerCase();

        // Array of constants and special keys to be filtered out
        String[] keys = {"sqrt", "cos", "pi", "sin", "tan"};

        // Loop through the array and replace occurrences
        for (String key : keys) {
            data = data.replace(key, "");
        }

        return data;
    }
}
