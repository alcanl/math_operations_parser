package token_enum;

public enum TokenType {
    PLUS('+'), MINUS('-'), TIMES('*'), DIVIDE('/'), NONE('\0');
    private final char value;
    TokenType(char ch)
    {
        value = ch;
    }
    public double doOperationPlus(double value1, double value2)
    {
        return value1 + value2;
    }
    public double doOperationMinus(double value1, double value2)
    {
        return value1 - value2;
    }
    public double doOperationTimes(double value1, double value2)
    {
        return value1 * value2;
    }
    public double doOperationDivide(double value1, double value2)
    {
        return value1 / value2;
    }
    public char getValue()
    {
        return this.value;
    }
    @Override
    public String toString()
    {
        return String.format(String.valueOf(getValue()));
    }
}
