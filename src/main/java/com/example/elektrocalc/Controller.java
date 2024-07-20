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
    private Map<RadioButton, UIElementMap<TextField, Text, RadioButton>> uiElementMap;

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
        //Makes the history field non-editable
        ResultHistory.setEditable(false);
        //delete the pesky error when no Equation is selected
        globalEquation= "";
        //automatically fills the combo box with values
        for (String key : Json.equations.keySet()) {
            EquationSelect.getItems().add(key);
        }
        //Map UI ElementsTogether
        uiElementMap = new HashMap<>();
        uiElementMap.put(OneRadio, new UIElementMap<>(OneTextField, OneText, OneRadio));
        uiElementMap.put(TwoRadio, new UIElementMap<>(TwoTextField, TwoText, TwoRadio));
        uiElementMap.put(ThreeRadio, new  UIElementMap<>(ThreeTextField, ThreeText, ThreeRadio));
        uiElementMap.put(FourRadio, new UIElementMap<>(FourTextField, FourText, FourRadio));
        uiElementMap.put(FiveRadio, new UIElementMap<>(FiveTextField, FiveText, FiveRadio));
        uiElementMap.put(SixRadio, new UIElementMap<>(SixTextField, SixText, SixRadio));
        uiElementMap.put(SevenRadio, new UIElementMap<>(SevenTextField, SevenText, SevenRadio));

        //turns radiobutton into one group
        CalcSelect = new ToggleGroup();
        addToToggleGroup();
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
        if (whichTextField >= 0 && whichTextField < uiElementMap.size()) {
            RadioButton[] radioButtons = {OneRadio, TwoRadio, ThreeRadio, FourRadio, FiveRadio, SixRadio, SevenRadio};
            UIElementMap<TextField, Text, RadioButton> elements = uiElementMap.get(radioButtons[whichTextField]);

            if (elements != null) {
                TextField textField = elements.getFirst();
                Text text = elements.getSecond();
                RadioButton radioButton = elements.getThird();

                text.setText(toSetSymbol);
                text.setVisible(true);
                textField.setVisible(true);
                radioButton.setVisible(true);
                radioButton.setDisable(false);
            }
        }
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
        for (UIElementMap<TextField, Text, RadioButton> elements : uiElementMap.values()) {
            TextField textField = elements.getFirst();
            Text text = elements.getSecond();
            RadioButton radioButton = elements.getThird();

            text.setVisible(false);
            textField.setVisible(false);
            radioButton.setVisible(false);
            radioButton.setDisable(true);
        }
    }

    private void addToToggleGroup(){
        for (UIElementMap<TextField, Text, RadioButton> elements : uiElementMap.values()) {
            RadioButton radioButton = elements.getThird();
            radioButton.setToggleGroup(CalcSelect);
        }
    }
}
