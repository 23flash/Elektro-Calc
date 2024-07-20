package com.example.elektrocalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


import java.util.*;


public class Controller {
    //other
    @FXML
    private Text Result;
    @FXML
    public TextArea ResultHistory;
    @FXML
    private ComboBox<String> EquationSelect;
    @FXML
    private Text currentEquation;


    private String globalEquation;
    // Textfields
    @FXML
    private TextField OneTextField;
    @FXML
    private TextField TwoTextField ;
    @FXML
    private TextField ThreeTextField;
    @FXML
    private TextField FourTextField;
    @FXML
    private TextField FiveTextField;
    @FXML
    private TextField SixTextField;
    @FXML
    private TextField SevenTextField;

    private TextField[] TextFieldArray;
    //Text
    @FXML
    private Text OneText;
    @FXML
    private Text TwoText;
    @FXML
    private Text ThreeText;
    @FXML
    private Text FourText;
    @FXML
    private Text FiveText;
    @FXML
    private Text SixText;
    @FXML
    private Text SevenText;

    private Text[] TextArray;
    //Radio Buttons
    @FXML
    private ToggleGroup CalcSelect;
    @FXML
    private RadioButton OneRadio;
    @FXML
    private RadioButton TwoRadio;
    @FXML
    private RadioButton ThreeRadio;
    @FXML
    private RadioButton FourRadio;
    @FXML
    private RadioButton FiveRadio;
    @FXML
    private RadioButton SixRadio;
    @FXML
    private RadioButton SevenRadio;

    private RadioButton[] RadioArray;


    @FXML
    public void initialize() {
        //Map Button Text Textfield Together

        //Makes the history field non-editable
        ResultHistory.setEditable(false);
        //delete the pesky error when no Equation is selected
        globalEquation= "";
        //automatically fills the combo box with values
        for (String key : Json.equations.keySet()) {
            EquationSelect.getItems().add(key);
        }
        //Array to make them visible and in visible
        TextArray = new Text[]{OneText,TwoText,ThreeText,FourText,FiveText,SixText,SevenText};
        TextFieldArray = new TextField[]{OneTextField,TwoTextField,ThreeTextField,FourTextField,FiveTextField,SixTextField,SevenTextField};
        RadioArray = new RadioButton[]{OneRadio,TwoRadio,ThreeRadio,FourRadio,FiveRadio,SixRadio,SevenRadio};
        //turns radiobutton into one group
        CalcSelect = new ToggleGroup();
        for (int i = 0; i < RadioArray.length; i++) {
            RadioArray[i].setToggleGroup(CalcSelect);
        }
        //hide Inputs
        hideAllInputs();

    }
    //Seek out the the wished Formula
    @FXML
    private void handleComboSelect(ActionEvent event) {
        hideAllInputs();
        String toSetSymbol = "";
        //gets the equation out of the json object
        globalEquation = Json.getEquation(EquationSelect.getSelectionModel().getSelectedItem());
        //Makes Local Equation for GUI
        String localEquation = globalEquation;
        //hashmap to find duplicats
        Set<String> printedStrings = new HashSet<>();
        //Deletes Constants and doubble ==
        localEquation = localEquation.replace("==","=");
        currentEquation.setText(localEquation);
        // modify the string to filter out constants and Special Keys Like SQRT etc
        if (localEquation.contains("Sqrt")){
            localEquation = localEquation.replace("Sqrt", "");
        }

        //Breaks up the String and activates Gui Elements
        int whichTextField = 0;
        for (Character ch : localEquation.toCharArray()) {
            if(Character.isLetter(ch)){
                toSetSymbol += ch;
            }else if(ch == '+' || ch == '-'|| ch == '*'|| ch == '/' || ch == '=' ){
                if (!printedStrings.contains(toSetSymbol) && !toSetSymbol.isEmpty()) {
                    enableRightFields(whichTextField, toSetSymbol);
                    whichTextField ++;
                    printedStrings.add(toSetSymbol);
                }
                toSetSymbol = "";
            }
        }
        if (!printedStrings.contains(toSetSymbol) && !toSetSymbol.isEmpty()) {
            enableRightFields(whichTextField, toSetSymbol);
        }
    }
    private void enableRightFields(int whichTextField, String toSetSymbol ) {
        TextArray[whichTextField].setText(toSetSymbol);
        TextArray[whichTextField].setVisible(true);
        TextFieldArray[whichTextField].setVisible(true);
        RadioArray[whichTextField].setVisible(true);
        RadioArray[whichTextField].setDisable(false);
    }

    //
    @FXML
    private void handleCalcButtonAction() {

    }
    @FXML
    private void handleCalcSelect() {
        printSelectedRadioButtonId();
    }
    private void printSelectedRadioButtonId() {
        Toggle selectedToggle = CalcSelect.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            System.out.println("Selected RadioButton ID: " + selectedRadioButton.getId());
        } else {
            System.out.println("No RadioButton selected");
        }
    }

    private void hideAllInputs(){
        //hide the all unused GUI Elements
        //Text
        for (int i = 0; i < TextArray.length; i++) {
            TextArray[i].setVisible(false);
        }
        //TextField
        for (int i = 0; i < TextFieldArray.length; i++) {
            TextFieldArray[i].setVisible(false);
        }
        //Radio Buttons
        for (int i = 0; i < RadioArray.length; i++) {
            RadioArray[i].setVisible(false);
        }
        for (int i = 0; i < RadioArray.length; i++) {
            RadioArray[i].setDisable(true);
        }
    }
}
