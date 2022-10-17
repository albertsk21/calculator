package com.example.calculator.calculate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MathCalculatorTest {

    ICalculator calculator;

    @BeforeEach
    public void init(){
        this.calculator = new MathCalculator();

    }

    @Test
    public void addIncorrectOperation(){
        String test = "9 : 9 + 1 +";
        String actual = this.calculator.calculate(test);
        String expected = "Error";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void addOperationShouldReturnCorrect(){
        String test = "1 + 1 + 1";
        String actual = this.calculator.calculate(test);
        String expected = "3";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void addMultipleOperationShouldReturnCorrect(){
        String test = "12 : 5 * 1.2";
        String actual = this.calculator.calculate(test);
        String expected = "2";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void addDivideOperationShouldReturnNegativeNumber(){
        String test = "-19 - 9";
        String actual = this.calculator.calculate(test);
        String expected = "-28";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void addOperationShouldReturnNegativeNumber(){
        String test = "-19 + -9";
        String actual = this.calculator.calculate(test);
        String expected = "-28";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void addOperationWithNumberDotShouldReturnCorrectNumber(){
        String test = "10 + 0.";
        String actual = this.calculator.calculate(test);
        String expected = "10";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void calculateLeftToRightAddOperation_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateLeftToRight",String.class);
        method.setAccessible(true);

        String test = "1 + 2 - 1";
        String  actual = (String) method.invoke(this.calculator,test);
        String expected = "2";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void calculateLeftToRightAllOperations_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateLeftToRight",String.class);
        method.setAccessible(true);

        String test = "2 : 1 * 2 + 1";
        String  actual = (String) method.invoke(this.calculator,test);
        String expected = "5";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void calculateLeftToRightOperations_ShouldReturnErrorMessage() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateLeftToRight",String.class);
        method.setAccessible(true);

        String test = "2 + 1 + 1 *";
        String  actual = (String) method.invoke(this.calculator,test);
        String expected = "Error";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void calculateTwoNumbersAddOperator_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateTwoNumbers",String.class,String.class,String.class);
        method.setAccessible(true);

        String firstNumber = "1";
        String operator = "+";
        String secondNumber = "1";
        String  actual = (String) method.invoke(this.calculator,firstNumber,operator,secondNumber);
        String expected = "2";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void calculateTwoNumbersDivideOperator_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateTwoNumbers",String.class,String.class,String.class);
        method.setAccessible(true);

        String firstNumber = "1";
        String operator = ":";
        String secondNumber = "1";
        String  actual = (String) method.invoke(this.calculator,firstNumber,operator,secondNumber);
        String expected = "1";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void calculateTwoNumbersMultiplyOperator_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateTwoNumbers",String.class,String.class,String.class);
        method.setAccessible(true);

        String firstNumber = "2";
        String operator = "*";
        String secondNumber = "2";
        String  actual = (String) method.invoke(this.calculator,firstNumber,operator,secondNumber);
        String expected = "4";
        Assertions.assertEquals(actual,expected);
    }
    @Test
    public void calculateTwoNumbersMinusOperator_ShouldReturnCorrectNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateTwoNumbers",String.class,String.class,String.class);
        method.setAccessible(true);


        String firstNumber = "2";
        String operator = "-";
        String secondNumber = "2";
        String  actual = (String) method.invoke(this.calculator,firstNumber,operator,secondNumber);
        String expected = "0";
        Assertions.assertEquals(actual,expected);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void calculateByAnOperatorSymbol_ShouldReturnCorrectNumber(String sequence, String operator, String expected) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> mathCalculatorClass = this.calculator.getClass();
        Method method = mathCalculatorClass.getDeclaredMethod("calculateByAnOperatorSymbol",String.class,String.class);
        method.setAccessible(true);


  
        String  actual = (String) method.invoke(this.calculator,sequence,operator);
        Assertions.assertEquals(actual,expected);
    }










}