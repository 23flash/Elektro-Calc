
package com.example.elektrocalc;
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
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

    private static IExpr permuteEquation(ExprEvaluator util, String equation, String variable) {
        // Symja-Ausdruck für die Gleichung
        IExpr equationExpr = util.eval(equation);

        // Symbol für die Variable, nach der umgestellt werden soll
        IExpr variableSymbol = util.eval(variable);

        // Gleichung umstellen

        return util.eval("Solve(" + equation + ", " + variable + ")");
    }

    private static String formatString(IExpr expr){

        String input = expr.toString();
        input = input.replace("->", "==");
        input = input.replace("{", "");
        input = input.replace("}", "");
        StringBuilder output = new StringBuilder();
        for (Character ch : input.toCharArray()) {

            if (ch == ',') {
                break;
            }else {
                output.append(ch);
            }
        }
        return output.toString();
    }

}
