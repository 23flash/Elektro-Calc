
package com.example.elektrocalc;
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import java.util.Map;
import java.io.IOException;
public class EquationSolver {
    /**
     * Permutes the given equation to solve for the specified variable.
     *
     * @param equation The equation to be permuted.
     * @param variableToSolveFor The variable to solve for.
     * @return A string representation of the permuted equation.
     */
    public static String permute(String equation, String variableToSolveFor) {
        ExprEvaluator util = new ExprEvaluator();
        IExpr solvedEquation = permuteEquation(util, equation, variableToSolveFor);
        return formatString(solvedEquation);
    }
    /**
     * Solves the given equation by substituting the provided variable assignments.
     *
     * @param equation The equation to be solved.
     * @param variableAssignments A map of variable names to their assigned values.
     * @return The numerical result of the solved equation.
     */

    public static String solve(String equation, Map<String,Double> variableAssignments) {
        // Create an expression parser
        EvalUtilities util = new EvalUtilities(false, true);

        //Make the Variabels Unique
        String newEquation = DataProcessor.FormatStringToReplace(equation);

        //Debug
        System.out.println(newEquation);
        ////

        for (Map.Entry<String, Double> entry : variableAssignments.entrySet()) {
            newEquation = newEquation.replace("#"+entry.getKey()+"#", entry.getValue().toString());
        }

        //Debug
        System.out.println(newEquation);
        ////


        // Replace '==' with '=' for evaluation
        newEquation = newEquation.replace("==", "=");

        // Parse and evaluate the equation
        try {
            IExpr result = util.evaluate(newEquation);
            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "cant solve this expression";
        }
    }
    /**
     * Permutes the equation to solve for the given variable using Symja.
     *
     * @param util The ExprEvaluator instance used for evaluation.
     * @param equation The equation to be permuted.
     * @param variable The variable to solve for.
     * @return The permuted equation as an IExpr.
     */
    private static IExpr permuteEquation(ExprEvaluator util, String equation, String variable) {
        // Parse the equation expression
        IExpr equationExpr = util.eval(equation);

        // Parse the variable symbol
        IExpr variableSymbol = util.eval(variable);

        // Permute the equation to solve for the variable

        return util.eval("Solve(" + equation + ", " + variable + ")");
    }
    /**
     * Formats the IExpr result into a readable string.
     *
     * @param expr The expression to be formatted.
     * @return The formatted string representation of the expression.
     */
    private static String formatString(IExpr expr){
        String input = expr.toString();
        input = input.replace("->", "==");
        input = input.replace("{", "");
        input = input.replace("}", "");
        StringBuilder output = new StringBuilder();
        //eliminates multiple possible solutions if sqrt or ^2 was in the input Equation
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
