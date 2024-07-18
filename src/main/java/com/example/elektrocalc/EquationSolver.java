package com.example.elektrocalc;
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.parser.ExprParser;
import org.matheclipse.parser.client.Parser;


import java.util.Map;

public class EquationSolver {

    public static String permute(String equation, String variableToSolveFor) {
        ExprEvaluator util = new ExprEvaluator();
        IExpr solvedEquation = permuteEquation(util, equation, variableToSolveFor);
        return formatString(solvedEquation);
    }



    public static Double solve(String equation, Map<String,Double> variableAssignments) {
        // Create an expression parser
        EvalUtilities util = new EvalUtilities(false, true);

        // Substitute values into the equation
        for (Map.Entry<String, Double> entry : variableAssignments.entrySet()) {
            equation = equation.replace(entry.getKey(), entry.getValue().toString());
        }

        equation = equation.replace("==", "=");

        // Parse and evaluate the equation
        try {
            IExpr result = util.evaluate(equation);
            return result.toDoubleDefault();

        } catch (Exception e) {
            e.printStackTrace();
            return 0D;
        }
    }

    public static Double permuteAndSolve(String equation, String variableToSolveFor,Map<String,Double> variableAssignments) {
       return solve(permute(equation, variableToSolveFor), variableAssignments);
    }


    private static IExpr permuteEquation(ExprEvaluator util, String equation, String variable) {
        // Symja-Ausdruck für die Gleichung
        IExpr equationExpr = util.evaluate(equation);

        // Symbol für die Variable, nach der umgestellt werden soll
        IExpr variableSymbol = util.evaluate(variable);

        // Gleichung umstellen
        IExpr solved = util.evaluate("Solve(" + equation + ", " + variable + ")");

        return solved;
    }

    private static String formatString(IExpr expr){
        String input = String.valueOf(expr);
        String trimmedInput = input.substring(2, input.length() - 2);

        String[] parts = trimmedInput.split("->");
        String leftPart = parts[0].trim();
        String rightPart = parts[1].trim();

        String output = leftPart + " == " + rightPart;
        return output;
    }

}
