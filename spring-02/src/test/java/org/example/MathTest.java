package org.example;

import org.example.calculator.MathCalculator;
import org.example.calculator.impl.MathCalculatorImpl;
import org.example.proxy.statics.CalculatorStaticProxy;
import org.junit.jupiter.api.Test;

public class MathTest {

    @Test
    void test01() {
        MathCalculator target = new MathCalculatorImpl();
        int result1 = target.add(1, 2);
        System.out.println(result1);
        System.out.println("===================");

        CalculatorStaticProxy proxy = new CalculatorStaticProxy(target);
        int result2 = proxy.add(1, 2);
        System.out.println(result2);
    }
}
