package com.vasilev.calculator;

import com.vasilev.parser.Parser;
import com.vasilev.checker.Checker;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;



public class Calculator {
    public static final int MAX_DIGIT = 10;
    public static TreeMap<Integer, String> digits = new TreeMap<>();
    public static TreeMap<Integer, String> operators = new TreeMap<>();
    public static final String operatorsList = "/*-+";


    public void start() {
        Scanner scanner = new Scanner(System.in);
        Checker checker = new Checker();
        Parser parser = new Parser();
        String inputStr;
        char[] charArr;

        System.out.print("Input an expression: ");
        inputStr = scanner.nextLine();
        charArr = new char[inputStr.length()];
        inputStr.getChars(0, inputStr.length(), charArr, 0);
        checker.generalCheck(charArr);
        parser.parsing(charArr);
        checker.detailedCheck(charArr);
        calculate();

        String result = digits.entrySet().iterator().next().getValue();
        try {
            System.out.println(Math.abs(Integer.parseInt(result)));
        }
        catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private void calculate() {
        while (operators.values().toString().contains("*") | operators.values().toString().contains("/")) {
            if (operators.values().toString().contains("*") & operators.values().toString().contains("/")) {
                int multiPosition = getKey(operators, "*");
                int divPosition = getKey(operators, "/");

                if (multiPosition < divPosition) mathOperation("*");
                else mathOperation("/");
            }
            else if (operators.values().toString().contains("*") & !operators.values().toString().contains("/")) {
                mathOperation("*");
            }
            else mathOperation("/");
        }
        while (operators.values().toString().contains("+") | operators.values().toString().contains("-")) {
            if (operators.values().toString().contains("+") & operators.values().toString().contains("-")) {
                int addPosition = getKey(operators, "+");
                int subsPosition = getKey(operators, "-");

                if (addPosition < subsPosition) mathOperation("+");
                else mathOperation("-");
            }
            else if (operators.values().toString().contains("+") & !operators.values().toString().contains("-")) {
                mathOperation("+");
            }
            else mathOperation("-");
        }
    }


    private void mathOperation(String sign) {
        int signPosition;

        if (getKey(operators, sign) != null) {
            signPosition = getKey(operators, sign);
            List<Integer> digitsKeys = new ArrayList<>(digits.keySet());
            for (int i = 0; i < digitsKeys.size(); i++) {
                if (signPosition > digitsKeys.get(i) & signPosition < digitsKeys.get(i + 1)) {
                    int res = -1;
                    if (sign == "*") {
                        try {
                            res = Integer.parseInt(digits.get(digitsKeys.get(i))) *
                                Integer.parseInt(digits.get(digitsKeys.get(i + 1)));
                            }
                        catch (Exception e) {
                            System.err.println(e);
                            System.exit(1);
                        }
                    }
                    else if (sign == "/") {
                        try {
                            res = Integer.parseInt(digits.get(digitsKeys.get(i))) /
                                    Integer.parseInt(digits.get(digitsKeys.get(i + 1)));
                        }
                        catch (Exception e) {
                            System.err.println(e);
                            System.exit(1);
                        }
                    }
                    else if (sign == "+") {
                        try {
                            res = Integer.parseInt(digits.get(digitsKeys.get(i))) +
                                    Integer.parseInt(digits.get(digitsKeys.get(i + 1)));
                        }
                        catch (Exception e) {
                            System.err.println(e);
                            System.exit(1);
                        }
                    }
                    else if (sign == "-") {
                        try {
                            res = Integer.parseInt(digits.get(digitsKeys.get(i))) -
                                    Integer.parseInt(digits.get(digitsKeys.get(i + 1)));
                        }
                        catch (Exception e) {
                            System.err.println(e);
                            System.exit(1);
                        }
                    }
                    else {
                        System.err.println("Wrong operator entered.");
                    }
                    operators.remove(signPosition);
                    digits.remove((digitsKeys.get(i)));
                    digits.remove((digitsKeys.get(i + 1)));
                    digits.put(signPosition, Integer.toString(res));
                    break;
                }
            }
        }
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

