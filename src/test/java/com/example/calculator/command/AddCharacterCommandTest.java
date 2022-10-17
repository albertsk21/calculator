package com.example.calculator.command;

import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

class AddCharacterCommandTest {


    private String expected;
    private String container = "";
    private Receiver receiver;
    private String actual;
    @BeforeEach
    public void init(){
        this.receiver = new StringReceiver(container);

    }


    @Test
    public void addOneNumber_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testNumber = "1";
        this.add(testNumber);
        expected = testNumber;
        String actual = this.getReceiverContent();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void addTwoNumbers_ShouldReturnTwoNumbers() throws NoSuchFieldException, IllegalAccessException {
        String testNumberOne = "1";
        String testNumberTwo = "2";
        this.add(testNumberOne);
        actual = this.getReceiverContent();
        expected = testNumberOne;
        Assertions.assertEquals(expected,actual);

        this.add(testNumberTwo);
        actual = this.getReceiverContent();
        expected = testNumberOne + testNumberTwo;
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addNumberWithAnMathOperation_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testNumberOne = "1";
        String testNumberTwo = "2";
        String operatorTest = "*";
        this.add(testNumberOne);
        this.add(testNumberTwo);
        this.add(operatorTest);

        actual = this.getReceiverContent();
        expected = "12 *";

        Assertions.assertEquals(expected,actual);
    }


    @Test
    public void addZeroReplaceWithTwo_ShouldReturnCorrectNumber() throws NoSuchFieldException, IllegalAccessException {
        String zeroTest = "0";
        String numberTest = "2";

        this.add(zeroTest);
        this.add(numberTest);
        actual = this.getReceiverContent();
        expected = numberTest;
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addZeroReplaceWithNumberAndDot_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String numberTest = "4";
        String dotTest = ".";
        String multiplyOperator = "*";
        String zeroTest = "0";
        this.add(zeroTest);
        this.add(numberTest);
        this.add(multiplyOperator);
        this.add(dotTest);
        actual = this.getReceiverContent();
        expected = "4 * 0.";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addDot_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {

        String dotTest = ".";
        this.add(dotTest);
        actual = this.getReceiverContent();

        expected = "0.";
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("should return -1 - 2")
    public void addMinusAndNumberAndMinusOperatorAndNumber_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String minusOperator = "-";
        String numberTestOne = "1";
        String numberTestTwo = "2";


        this.add(minusOperator);
        this.add(numberTestOne);
        this.add(minusOperator);
        this.add(numberTestTwo);

        actual = this.getReceiverContent();
        expected = "-1 - 2";
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("should return -1 + -1")
    public void addNegativeNumberPlusNegativeNumber() throws NoSuchFieldException, IllegalAccessException {
        String minusOperator = "-";
        String plusOperator = "+";
        String numberTest = "1";

        this.add(minusOperator);
        this.add(numberTest);
        this.add(plusOperator);
        this.add(minusOperator);
        this.add(numberTest);

        actual = this.getReceiverContent();
        expected = "-1 + -1";


        Assertions.assertEquals(expected,actual);
    }

    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }
    private void add(String character){
        AddCharacterCommand addCharacterCommandOne = new AddCharacterCommand(character,receiver);
        addCharacterCommandOne.execute();
    }

}