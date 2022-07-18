package com.j4c08.calcu;

public enum EArithmeticOperation {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    PERCENT("%"),
    POWER("x^y"),
    BLANK("");

    private String label;

    EArithmeticOperation(String s) { this.label = s;}

    public String getLabel() { return this.label;}
}
