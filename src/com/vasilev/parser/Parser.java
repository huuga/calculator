package com.vasilev.parser;

import static com.vasilev.calculator.Calculator.operatorsList;
import static com.vasilev.calculator.Calculator.digits;
import static com.vasilev.calculator.Calculator.operators;


public class Parser {

    private int strLength;

    public void parsing(char[] charArr) {
        this.strLength = charArr.length;
        findAllDigits(charArr);
        findAllOperators(charArr);
    }


    private void findAllDigits(char[] charArr) {
        int i = 0, j = 0;

        while (i < strLength) {
            if (Character.isDigit(charArr[i])) {
                int k = i;
                StringBuilder tempDigit = new StringBuilder();
                while ((i < strLength - 1) & Character.isDigit(charArr[i])) {
                    tempDigit.append(charArr[i]);
                    i++;
                }
                if (i == (strLength - 1) & Character.isDigit(charArr[i])) tempDigit.append(charArr[i]);
                digits.put(k, tempDigit.toString());
                j++;
            }
            i++;
        }
    }


    private void findAllOperators(char[] charArr) {

        for (int i = 0; i < strLength; i++) {
            if (operatorsList.indexOf(charArr[i]) != -1) {
                operators.put(i, "" + charArr[i]);
            }
        }
    }
}