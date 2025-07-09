package org.example.proxy.statics;

import org.example.calculator.MathCalculator;

public class CalculatorStaticProxy {

    private MathCalculator target;

    public CalculatorStaticProxy(MathCalculator mathCalculator) {
        this.target = mathCalculator;
    }

    public int add(int a, int b) {
        System.out.println("日志：Adding " + a + " and " + b);
        int result = target.add(a, b);
        System.out.println("日志：结果 " + result);
        return result;
    }

    public int subtract(int a, int b) {
        System.out.println("日志：subtract " + a + " and " + b);
        int result = target.subtract(a, b);
        System.out.println("日志：结果 " + result);
        return result;
    }

    public int multiply(int a, int b) {
        System.out.println("日志：multiply " + a + " and " + b);
        int result = target.multiply(a, b);
        System.out.println("日志：结果 " + result);
        return result;
    }

    public int divide(int a, int b) {
        System.out.println("日志：divide " + a + " and " + b);
        int result = target.divide(a, b);
        System.out.println("日志：结果 " + result);
        return result;
    }
}
