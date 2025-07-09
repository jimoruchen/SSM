package org.example.calculator.impl;

import org.example.calculator.MathCalculator;

public class MathCalculatorImpl implements MathCalculator {

    @Override
    public int add(int a, int b) {
        // System.out.println("日志：Adding " + a + " and " + b);
        int result = a + b;
        // System.out.println("日志：结果 " + result);
        return result;
    }

    @Override
    public int subtract(int a, int b) {
        int result = a - b;
        return result;
    }

    @Override
    public int multiply(int a, int b) {
        int result = a * b;
        return result;
    }

    @Override
    public int divide(int a, int b) {
        int result = a / b;
        return result;
    }
}
