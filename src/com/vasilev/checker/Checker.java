package com.vasilev.checker;

import com.vasilev.calculator.Calculator;


import static com.vasilev.calculator.Calculator.digits;
import static com.vasilev.calculator.Calculator.operators;
import static com.vasilev.calculator.Calculator.operatorsList;
import static com.vasilev.calculator.Calculator.MAX_DIGIT;

public class Checker {

    public void generalCheck(char[] charArr) {

        int strLength = charArr.length;
        boolean containDigit = false;

        if (strLength < 1) {
            System.err.println("No input entered.");
            System.exit(1);
        }

        for (char c : charArr) {
            if (operatorsList.indexOf(c) == -1 & !Character.isDigit(c)
                    & !Character.isWhitespace(c)) {
                System.err.println("Wrong character(s) was entered.");
                System.exit(1);
            }
        }

        for (char c : charArr) {
            if (Character.isDigit(c)) {
                containDigit = true;
                break;
            }
        }
        if (!containDigit) {
            System.err.println("No digit entered.");
            System.exit(1);
        }
    }


    public void detailedCheck(char[] charArr) {

        for (String s: digits.values()) {
            try {
                if (Integer.parseInt(s) > MAX_DIGIT) {
                    System.err.println("One of the digits is out from 0 to " + MAX_DIGIT + " range.");
                    System.exit(1);
                }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            }
        }

        if (digits.size() == 1) {
            System.out.println(digits.values().toArray()[0]);
            System.exit(0);
        }

        if (operators.size() == 0) {
            System.err.println("No operators was entered.");
            System.exit(1);
        }

        if (digits.size() - 1 != operators.size()) {
            System.err.println("Digits and operators counts mismatch.");
            System.exit(1);
        }

        for (int i = 0; i < (digits.size() - 1); i++){
            String iterDig = (String) digits.values().toArray()[i];
            int iterDigPosition = Calculator.getKey(digits, iterDig);
            int nextCellAfterIterDig = iterDigPosition + iterDig.length();
            for (int j = nextCellAfterIterDig; operatorsList.indexOf(charArr[j]) == -1; j++) {
                if (Character.isDigit(charArr[j])) {
                    System.err.println("Multiple digits or operators in a row");
                    System.exit(1);
                }
            }
        }
    }


}
