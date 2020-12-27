package main.java.com.byteshift.calculatorjfx.calculator_engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nacer Salah Eddine
 */
public class CalculatorEngine {
    
    public static String getCalc(String expression) {
        List<Character> opt = getOperators(expression);
        List<Double> numbers = getNumbers(expression);
        
        
        
        return "160";
    }

    private static List<Character> getOperators(String expression) {
        List<String> data = Arrays.asList(expression.split("[0-9\\.]+"));

        List<Character> opt = new LinkedList<>();
        data.forEach(e -> {
            if (!e.trim().isEmpty()) {
                opt.add(e.charAt(0));
            }
        });

        return opt;
    }

    private static List<Double> getNumbers(String expression) {
        List<Double> numbers = new LinkedList<>();
        String val = "";
        for (char c : expression.toCharArray()) {
            if ("+-*/%".contains(String.valueOf(c))) {
                numbers.add(Double.parseDouble(val));
                val = "";
            } else {
                val += c;
            }
        }


        return numbers;
    }

    public static double makeStandardCalc(Double num1, Double num2, String opt) {
        switch(opt) {
            case "+": return num1 + num2; 
            case "-": return num1 - num2; 
            case "*": 
            case "×":
                return num1 * num2; 
            case "/": 
            case "÷":
                return (num2 == 0.0)? 0.0 : num1 / num2; 
            case "%": return (num2 == 0.0)? 0.0 : num1 % num2; 
        }
        return 0.0;
    }
    
}
