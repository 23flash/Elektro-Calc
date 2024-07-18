package com.example.elektrocalc;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private RadioButton OneRadioButton;
    @FXML
    private RadioButton TwoRadioButton;
    @FXML
    private RadioButton ThreeRadioButton;
    @FXML
    private RadioButton FourRadioButton;
    @FXML
    private RadioButton FiveRadioButton;
    @FXML
    private RadioButton SixRadioButton;
    @FXML
    private RadioButton SevenRadioButton;



    @FXML
    public void initialize() {
        //turns radiobutton into one group
        CalcSelect = new ToggleGroup();
        //Makes the history field non-editable
        ResultHistory.setEditable(false);
        //automatically fills the combo box with values
        for (String key : Json.equations.keySet()) {
            EquationSelect.getItems().add(key);
        }


    }
    @FXML
    void handleComboSelect(ActionEvent event) {

    }

    //Classes
    @FXML
    private void handleCalcButtonAction() {
            /*
            double I_doub = DataProcessor.TextFieldToDouble(I);
            double U_doub = DataProcessor.TextFieldToDouble(U);
            double V_doub = DataProcessor.TextFieldToDouble(V);
            System.out.println(I_doub);
            System.out.println(U_doub);
            System.out.println(V_doub);
            System.out.println(V_doub + U_doub + I_doub);
            System.out.println("Screw You");
            Result.setText(DataProcessor.DoubleToString(I_doub));
            OneTextField.setEditable(false);


            double I_doub = DataProcessor.TextFieldToDouble(OneTextField);
            Result.setText(DataProcessor.DoubleToString(I_doub));
            ResultHistory.appendText(DataProcessor.DoubleToString(I_doub)+"\n");


            Map<String, Double> variableAssignments = new HashMap<>();
            variableAssignments.put("W", 3d);
            variableAssignments.put("X", 2.5d);
            variableAssignments.put("Y", 3.5d);
            variableAssignments.put("Z", 7.5d);
            out.println(EquationSolver.permute("X==W-Z*Y","X"));
            out.println(EquationSolver.solve(EquationSolver.permute("X==W-Z*Y","X"),variableAssignments));
            out.println(EquationSolver.permuteAndSolve("X==W-Z*Y","X",variableAssignments));
            out.println(EquationSolver.solve("X==W-Z*Y", variableAssignments));


         String Form= Json.getEquation("OhmsLAW");
        Map<String, Double> variableAssignments = new HashMap<>();
        variableAssignments.put("v", 3d);
        variableAssignments.put("i", 2.5d);
        variableAssignments.put("r", 3.5d);
        System.out.println(EquationSolver.permuteAndSolve(Form,"r",variableAssignments));
        ResultHistory.appendText(DataProcessor.DoubleToString(EquationSolver.permuteAndSolve(Form,"r",variableAssignments)) +"\n");




            */

    }

}
