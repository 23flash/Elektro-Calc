package com.example.elektrocalc;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class EquationSolver {
    public static String permute(String equation, String variableToSolveFor) {
        ExprEvaluator util = new ExprEvaluator();
        IExpr solvedEquation = solveEquation(util, equation, variableToSolveFor);
        return formatString(solvedEquation);
    }



    private static IExpr solveEquation(ExprEvaluator util, String equation, String variable) {
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
