package com.example.elektrocalc;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import static java.lang.String.format;

/**
 * Utility class for processing data between various formats.
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
            parsed = format("%.3f", Data);
        }catch (NumberFormatException e) {
            parsed = "Invalid input";
        }
        return parsed;
    }

    /**
     * Filter the variables out of a String
     *
     * @param data the String value to be formatted
     * @return the string data a String
     */
    public static String FormatStringToAnalyse(String data) {
        // Deletes double ==
        data = data.replace("==", "=");

        // Converts the string to lower case to handle case-insensitive replacements
        data = data.toLowerCase();

        // Array of constants and special keys to be filtered out
        String[] keys = {"sqrt", "cos", "pi", "sin", "tan","Sqrt", "Cos", "Pi", "Sin", "Tan","ArcSin","ArcCos","ArcTan","Csc","Sec"};

        // Loop through the array and replace occurrences
        for (String key : keys) {
            data = data.replace(key, "");
        }

        return data;
    }
    /**
     * Formats a given equation string by encapsulating variable names with '#' and preserving certain keywords.
     * Ensures that for Example u is not replaced in variable ut
     * @param equation the input equation string to format
     * @return the formatted equation string with variables encapsulated and keywords preserved
     */
    public static String FormatStringToReplace(String equation) {
        // StringBuilder to construct the new formatted equation
        StringBuilder newEquation = new StringBuilder();
        // String to accumulate variable names
        String toRepSymbol = "";
        // Array of keywords to preserve in the equation
        String[] keysArray = {"sqrt", "cos", "pi", "sin", "tan","Sqrt", "Cos", "Pi", "Sin", "Tan","ArcSin","ArcCos","ArcTan","Csc","Sec"};
        // Convert the array to a HashSet for efficient lookup
        Set<String> keys = new HashSet<>(Arrays.asList(keysArray));
        // Iterate through each character in the equation string
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);

            // Check if a keyword starts at the current position
            boolean isKey = false;
            for (String key : keys) {
                if (equation.startsWith(key, i)) {
                    // Add any accumulated variable encapsulated in #
                    if (!toRepSymbol.isEmpty()) {
                        newEquation.append("#").append(toRepSymbol).append("#");
                        toRepSymbol = "";
                    }
                    // Append the keyword
                    newEquation.append(key);
                    // Move the index forward by the length of the keyword
                    i += key.length() - 1;
                    isKey = true;
                    break;
                }
            }
            // Continue to the next character if a keyword was found
            if (isKey) {
                continue;
            }
            // Accumulate variable names or append special characters
            if (Character.isLetter(ch)) {
                toRepSymbol += ch;
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=' || ch == '^' || Character.isWhitespace(ch) || ch == '(' || ch == ')') {
                if (!toRepSymbol.isEmpty()) {
                    newEquation.append("#").append(toRepSymbol).append("#");
                    toRepSymbol = "";
                }
                newEquation.append(ch);
            } else {
                newEquation.append(ch);
            }
        }
        // Append the last variable if there is one
        if (!toRepSymbol.isEmpty()) {
            newEquation.append("#").append(toRepSymbol).append("#");
        }
        // Return the newly formatted equation
        return newEquation.toString();
    }
}
