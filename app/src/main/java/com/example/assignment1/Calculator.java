package com.example.assignment1;

import java.lang.Math;

import java.util.ArrayList;
import java.util.ListIterator;

public class Calculator {
    private ArrayList<Character> operation;
    private int result;
    //set the unique error result code to Integer.MIN_VALUE (-2^31 = -2147483648).
    public static final int ERROR = Integer.MIN_VALUE;
    //the Calculator is ready for next calculation or not.
    private boolean state;

    public Calculator() {
        this.operation = new ArrayList<>();
        this.result = 0;
        state = true;
    }

    public void push(String value) {
        for (char temp : value.toCharArray()) {
            operation.add(temp);
        }
    }

    public int calculate() {
        char operator;
        int temp;

        //invalid size return the error code.
        if ((operation.size() % 2) == 0) {
            this.result = ERROR;
            return ERROR;
        }

        try {
            ListIterator<Character> it = operation.listIterator();

            while (it.hasNext()) {
                //first iteration will remove 3 elements from the list
                if (result == 0 && !it.hasPrevious()) {
                    char firstElement = it.next();
                    char firstOperator = it.next();
                    char secondNumber = it.next();
                    if (validNumber(firstElement) && validNumber(secondNumber) && validOperator(firstOperator)) {
                        //extract the first element as the result.
                        result = calculation(firstOperator, Character.getNumericValue(firstElement), Character.getNumericValue(secondNumber));
                    } else {
                        throw new Exception("error");
                    }
                } else {
                    //remove 2 elements from the list
                    operator = it.next();

                    char num = it.next();

                    //unexpected input trigger the exception
                    if (!validOperator(operator) || !validNumber(num)) {
                        throw new Exception("error");
                    }

                    temp = Character.getNumericValue(num);
                    result = calculation(operator, result, temp);
                }
            }
            return result;
        } catch (Exception ex) {
            result = ERROR;
            return ERROR;
        }
    }

    public int getResult() {
        return this.result;
    }


    //clear the arraylist.
    public void clear() {
        this.operation.clear();
        this.result = 0;
    }

    public void setStateToFalse() {
        this.state = false;
    }

    public void setStateToTrue() {
        this.state = true;
    }

    public boolean getState() {
        return this.state;
    }

    public boolean validOperator(char c) {
        // '>' stand for maximum , '<' stand for minimum
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '<' || c == '>' || c == '%';
    }

    public boolean validNumber(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    public int calculation(char operator, int result, int num) {
        if (operator == '+') {
            result += num;
        } else if (operator == '-') {
            result -= num;
        } else if (operator == '*') {
            result = result * num;
        } else if (operator == '/') {
            result = result / num;
        }//advanced buttons
        else if (operator == '>') {
            result = Math.max(result, num);
        } else if (operator == '<') {
            result = Math.min(result, num);
        } else if (operator == '^') {
            result = (int) (Math.pow(Double.valueOf(result), Double.valueOf(num)));
        } else if (operator == '%') {
            result = result % num;
        }
        return result;
    }
}
