package com.example.calculator.receiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class StringReceiverTest {

    private String container;

    private StringReceiver receiver;
    private String expected;
    private final String EMPTY_STRING = "";

    @BeforeEach
    public void init(){
        this.container = "";
        this.receiver = new StringReceiver(container);
    }

    @Test
    public void AddOneCharacter_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testSymbol = "#";
        this.receiver.action(testSymbol);
        expected = testSymbol;
        String actual = this.getReceiverContent();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void AddMultipleCharacters_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testSymbol = "this is a test";
        this.receiver.action(testSymbol);
        expected = testSymbol;
        String actual = this.getReceiverContent();
        Assertions.assertEquals(expected,actual);
    }


    @Test
    public void getContent_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testString = "test";
        Field field = this.receiver.getClass().getDeclaredField("container");
        field.setAccessible(true);
        field.set(this.receiver,testString);


        String actual = this.receiver.getContent();
        expected = testString;
        Assertions.assertEquals(expected,actual);
    }
   @Test
    public void clean_ShouldReturnCorrectOutput() throws NoSuchFieldException, IllegalAccessException {
        String testString = "test method";
        Field field = this.receiver.getClass().getDeclaredField("container");
        field.setAccessible(true);
        field.set(this.receiver,testString);

        String actual = this.receiver.getContent();
        expected = testString;
        Assertions.assertEquals(expected,actual);

        this.receiver.clean();
        actual = this.getReceiverContent();
        Assertions.assertEquals(this.EMPTY_STRING,actual);
    }

    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }


}