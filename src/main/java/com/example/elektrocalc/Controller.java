package com.example.elektrocalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.*;
import java.io.IOException;

public class Controller {
    // Global Variabel
    private String globalEquation;
    private String toCalcEquation;
    private TextField lastTextField = null;
    private String currentHistoryRes;

    // Maps RadioButtons to UIElementMap objects
    private Map<RadioButton, UIElementMap<TextField, Text, RadioButton,Text>> uiElementMap;

    // UI elements
    @FXML
    private Label Result;
    @FXML
    private Label currentEquation;
    @FXML
    private TextArea ResultHistory;
    @FXML
    private TextArea Definition;
    @FXML
    private ComboBox<String> EquationSelect;
    @FXML
    private ImageView drawing;

    
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

    //TextE
    @FXML
    private Text OneTextE;
    @FXML
    private Text TwoTextE;
    @FXML
    private Text ThreeTextE;
    @FXML
    private Text FourTextE;
    @FXML
    private Text FiveTextE;
    @FXML
    private Text SixTextE;
    @FXML
    private Text SevenTextE;

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

    /**
     * Initializes the controller.
     * Sets up UI components and fills the equation selection combo box.
     */

    @FXML
    public void initialize() {
        //Makes the history field non-editable
        ResultHistory.setEditable(false);
        Definition.setEditable(false);
        Definition.setWrapText(true);
        //delete the pesky error when no Equation is selected
        globalEquation= "";
        //automatically fills the combo box with values as to first put them into a array List to sort the elements
        // Populate the equation selection combo box with sorted equation names
        ArrayList<String> equationNames = new ArrayList<>(Json.equations.keySet());
        equationNames.sort(Comparator.naturalOrder());
        EquationSelect.getItems().addAll(equationNames);

        //Map UI ElementsTogether
        uiElementMap = new HashMap<>();
        uiElementMap.put(OneRadio, new UIElementMap<>(OneTextField, OneText, OneRadio,OneTextE));
        uiElementMap.put(TwoRadio, new UIElementMap<>(TwoTextField, TwoText, TwoRadio,TwoTextE));
        uiElementMap.put(ThreeRadio, new  UIElementMap<>(ThreeTextField, ThreeText, ThreeRadio,ThreeTextE));
        uiElementMap.put(FourRadio, new UIElementMap<>(FourTextField, FourText, FourRadio,FourTextE));
        uiElementMap.put(FiveRadio, new UIElementMap<>(FiveTextField, FiveText, FiveRadio,FiveTextE));
        uiElementMap.put(SixRadio, new UIElementMap<>(SixTextField, SixText, SixRadio,SixTextE));
        uiElementMap.put(SevenRadio, new UIElementMap<>(SevenTextField, SevenText, SevenRadio,SevenTextE));

        // Add radio buttons to the toggle group
        CalcSelect = new ToggleGroup();
        addToToggleGroup();
        //hide Inputs
        hideAllInputs();
        //Bigger Font for Labels
        int fontsizse = 20;
        Result.setFont(new Font(fontsizse));
        currentEquation.setFont(new Font(fontsizse));

    }
    /**
     * Handles the selection of an equation from the combo box.
     * Sets up the UI elements based on the selected equation.
     *
     * @param event the action event
     */
    @FXML
    private void handleComboSelect(ActionEvent event) {
        hideAllInputs();
        String toSetSymbol = "";
        // Retrieve and set the selected equation
        String selectedEquation = EquationSelect.getSelectionModel().getSelectedItem();
        globalEquation = Json.getEquation(selectedEquation);
        toCalcEquation = Json.getEquation(selectedEquation);

        //Sets Definition Text
        //Definition.setText(Json.getDefinition(EquationSelect.getSelectionModel().getSelectedItem()));
        enableDefinitions(selectedEquation);
        //Makes Local Equation for GUI
        String localEquation = globalEquation;
        //hashmap to find duplicats
        Set<String> printedStrings = new HashSet<>();
        currentEquation.setText(localEquation.replace("==","="));
        localEquation  = DataProcessor.FormatStringToAnalyse(localEquation);

        //Breaks up the String and activates Gui Elements
        int whichTextField = 0;
        for (Character ch : localEquation.toCharArray()) {
            if(Character.isLetter(ch)){
                toSetSymbol += ch;
            }else if(ch == '+' || ch == '-'|| ch == '*'|| ch == '/' || ch == '='|| ch == '^' ){
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

        //new Equation reset input
        if (lastTextField!=null) lastTextField.setDisable(false);

    }
    /**
     * Sets the definition and image for the selected equation.
     *
     * @param selectedEquation the selected equation
     */
    private void enableDefinitions(String selectedEquation){
        try {
            Definition.setText(Json.getDefinition(selectedEquation));
            Image image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream(Json.getImage(selectedEquation))));
            drawing.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Enables the appropriate text field, label, and radio button based on the specified index.
     *
     * @param whichTextField the index of the text field to enable
     * @param toSetSymbol the symbol to set for the label
     */
    private void enableRightFields(int whichTextField, String toSetSymbol ) {
        if (whichTextField >= 0 && whichTextField < uiElementMap.size()) {
            RadioButton[] radioButtons = {OneRadio, TwoRadio, ThreeRadio, FourRadio, FiveRadio, SixRadio, SevenRadio};
            UIElementMap<TextField, Text, RadioButton,Text> elements = uiElementMap.get(radioButtons[whichTextField]);

            if (elements != null) {
                TextField textField = elements.getFirst();
                Text text = elements.getSecond();
                RadioButton radioButton = elements.getThird();
                Text textE = elements.getForth();

                text.setText(toSetSymbol);
                text.setVisible(true);
                textField.setVisible(true);
                radioButton.setVisible(true);
                radioButton.setDisable(false);
                textE.setVisible(true);
                try {
                    textE.setText(Json.getUnit(toSetSymbol));
                }catch (Exception e){
                    // Log the exception or handle it appropriately
                    System.err.println("Error setting unit for: " + toSetSymbol);
                }
            }
        }
    }
    /**
     * Handles the calculation action when the Calculate button is pressed.
     * Evaluates the equation with the provided input values and displays the result.
     */
    @FXML
    private void handleCalcButtonAction() {
        //map the variables to the right strings to calc
        Map<String, Double> variableAssignments = new HashMap<>();
        for (UIElementMap<TextField, Text, RadioButton,Text> elements : uiElementMap.values()) {
            TextField textField = elements.getFirst();
            Text text = elements.getSecond();
            if (textField.isVisible()== true&&textField.isDisabled()==false){
            variableAssignments.put(text.getText() ,DataProcessor.TextFieldToDouble(textField));
            }
        }
        // Solve the equation and display the result
        String solved = EquationSolver.solve(toCalcEquation, variableAssignments);
        Result.setText(currentHistoryRes+ " = " + solved);
        ResultHistory.appendText( toCalcEquation.replace("==", " = ") + " = " + solved + "\n");
    }
    /**
     * Handles the selection of a calculation variable via radio buttons.
     * Updates the equation to solve for the selected variable.
     */
    @FXML
    private void handleCalcSelect() {
        if (lastTextField != null){
            lastTextField.setDisable(false);
        }
        Toggle selectedToggle = CalcSelect.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            UIElementMap<TextField, Text, RadioButton,Text> elements = uiElementMap.get(selectedRadioButton);
                TextField textField = elements.getFirst();
                Text associatedText = elements.getSecond();
                textField.setDisable(true);
                currentHistoryRes = associatedText.getText();
                lastTextField = textField;
                toCalcEquation = EquationSolver.permute(globalEquation,associatedText.getText());
                currentEquation.setText(toCalcEquation.replace("==" ,"="));
        } else {
            System.out.println("No RadioButton selected");
        }
    }
    /**
     * Hides all input fields, labels, and radio buttons.
     */
    private void hideAllInputs(){
        //hide the all unused GUI Elements
        for (UIElementMap<TextField, Text, RadioButton,Text> elements : uiElementMap.values()) {
            TextField textField = elements.getFirst();
            Text text = elements.getSecond();
            RadioButton radioButton = elements.getThird();
            Text textE = elements.getForth();
            text.setVisible(false);
            text.setText("");
            textE.setVisible(false);
            textE.setText("");
            textField.setVisible(false);
            textField.setText("");
            radioButton.setVisible(false);
            radioButton.setSelected(false);
            radioButton.setDisable(true);
        }

    }
    /**
     * Adds all radio buttons to the toggle group.
     */
    private void addToToggleGroup(){
        for (UIElementMap<TextField, Text, RadioButton,Text> elements : uiElementMap.values()) {
            RadioButton radioButton = elements.getThird();
            radioButton.setToggleGroup(CalcSelect);
        }
    }
    /**
     * Clears the result history text area.
     *
     * @param event the action event
     */
    @FXML
    private void handleClearHistory(ActionEvent event) {
        ResultHistory.setText("");
    }
}
