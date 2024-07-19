package com.example.elektrocalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



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



    @FXML
    public void initialize() {
        //turns radiobutton into one group
        CalcSelect = new ToggleGroup();
        //Makes the history field non-editable
        ResultHistory.setEditable(false);
        //delete the pesky error when no Equation is selected
        globalEquation= "";
        //automatically fills the combo box with values
        for (String key : Json.equations.keySet()) {
            EquationSelect.getItems().add(key);
        }
        hideAllInputs();
    }
    //Seek out the the wished Formula
    @FXML
    private void handleComboSelect(ActionEvent event) {
        //gets the equation out of the json object
        globalEquation = Json.getEquation(EquationSelect.getSelectionModel().getSelectedItem());
        //set the global Equation so it can be used when calc is triggert
        currentEquation.setText(globalEquation);
        //Breaks up the String and activates Gui Elements
        for (char ch : globalEquation.toCharArray()) {
            System.out.println(ch);
        }

    }

    //
    @FXML
    private void handleCalcButtonAction() {
        System.out.println(CalcSelect.getSelectedToggle());
    }

    private void hideAllInputs(){
        //hide the all unused GUI Elements
        //Text
        OneText.setVisible(false);
        TwoText.setVisible(false);
        ThreeText.setVisible(false);
        FourText.setVisible(false);
        FiveText.setVisible(false);
        SixText.setVisible(false);
        SevenText.setVisible(false);
        //TextField
        OneTextField.setVisible(false);
        TwoTextField.setVisible(false);
        ThreeTextField.setVisible(false);
        FourTextField.setVisible(false);
        FiveTextField.setVisible(false);
        SixTextField.setVisible(false);
        SevenTextField.setVisible(false);
        //Radio Buttons //why must java switch to true here im happy for the following bugs
        OneRadio.setDisable(true);
        TwoRadio.setDisable(true);
        ThreeRadio.setDisable(true);
        FourRadio.setDisable(true);
        FiveRadio.setDisable(true);
        SixRadio.setDisable(true);
        SevenRadio.setDisable(true);

    }
}
