package parser;

import exception.IllegalExpressionException;
import exception.IllegalNumberException;
import token_enum.TokenType;
import java.util.Scanner;

public class Automata {
    private static final Scanner kb = new Scanner(System.in);
    private char[] lineStr;
    private TokenType type;
    private double value1;
    private double value2;

    private double doOperation()
    {
        return switch (type) {
            case PLUS -> type.doOperationPlus(value1, value2);
            case MINUS -> type.doOperationMinus(value1, value2);
            case TIMES -> type.doOperationTimes(value1, value2);
            default -> type.doOperationDivide(value1, value2);
        };
    }
    private void checkExpression()
    {
        int countForOpt = 0, countForPoint= 0, countForSpace = 0;

        for (var i = 0; i < lineStr.length; ++i) {
            if (!Character.isDigit(lineStr[i])) {
                if (lineStr[i] == ',') {
                    lineStr[i] = '.';
                    ++countForPoint;
                }

                if (lineStr[i] == '*' || lineStr[i] == '+' || lineStr[i] == '-' || lineStr[i] == '/')
                    ++countForOpt;

                if (lineStr[i] == '.')
                    ++countForPoint;

                if (Character.isWhitespace(lineStr[i]))
                    ++countForSpace;

                if (Character.isLetter(lineStr[i]))
                    throw new IllegalExpressionException("Incorrect Representation Of An Expression");

            }
        }

        if (countForOpt != 1 || countForSpace > 2 || countForPoint > 2)
            throw new IllegalNumberException("Incorrect Representation Of A Number");
    }
    private void getExpression()
    {
        System.out.print("Input a valid Math Expression : ");
        lineStr = kb.nextLine().strip().toCharArray();
    }
    private void getNumbers(String str1, String str2)
    {
        value1 = parseValue(str1);
        value2 = parseValue(str2);
    }
    private void getOperation(char ch)
    {
        type = switch (ch) {
            case '+' -> TokenType.PLUS;
            case '-' -> TokenType.MINUS;
            case '*' -> TokenType.TIMES;
            default -> TokenType.DIVIDE;
        };
    }
    private void getTokens()
    {
        String str = String.valueOf(lineStr);

        if (str.contains("+")) {
            getTokens(str, "+");
        }
        else if (str.contains("-")) {
            getTokens(str, "-");
        }
        else if (str.contains("*")) {
            getTokens(str, "*");
        }
        else {
            getTokens(str, "/");
        }
    }
    private void getTokens(String str, String operator)
    {
        getNumbers(str.substring(0, str.indexOf(operator)).strip(),
                str.substring(str.indexOf(operator) + 1).strip());
        getOperation(str.charAt(str.indexOf(operator)));
    }
    private double parseValue(String str)
    {
        return Double.parseDouble(str.strip());
    }
    public Automata() {
        type = TokenType.NONE;
        value1 = Double.NaN;
        value2 = Double.NaN;
        lineStr = null;
    }
    public void run()
    {
        while (true) {
        getExpression();
        try {
            checkExpression();
        }
        catch (IllegalNumberException | IllegalExpressionException ex) {
            System.out.println(ex.getMessage());
            continue;
        }
        getTokens();
        System.out.printf("%f %s %f = %f\n", value1, type, value2, doOperation());
        }
    }

}
