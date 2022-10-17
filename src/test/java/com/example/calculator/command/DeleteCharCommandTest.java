package com.example.calculator.command;

import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


class DeleteCharCommandTest {


    private String expected;
    private String container = "";
    private Receiver receiver;
    private String actual;

    private void init(){
        this.receiver = new StringReceiver(container);

    }


    @Test
    public void deleteOneCharacter() throws NoSuchFieldException, IllegalAccessException {
        String character = "a";
        container = character;
        init();
        this.deleteChar();
        actual = this.getReceiverContent();
        expected = "";
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteMoreNumbers() throws NoSuchFieldException, IllegalAccessException {
        String numbers = "123";
        container = numbers;
        init();
        this.deleteChar();
        this.deleteChar();
        actual = this.getReceiverContent();
        expected = "1";
        Assertions.assertEquals(expected,actual);
        this.deleteChar();
        expected = "";
        actual = this.getReceiverContent();
        Assertions.assertEquals(expected,actual);

    }


    @Test
    @DisplayName("input = '12 +' should return '12'")
    public void deleteNumberWithSpaceAndPlusOperator() throws NoSuchFieldException, IllegalAccessException {
        String numbers = "12 +";
        container = numbers;
        init();
        this.deleteChar();
        expected = "12";
        actual = this.getReceiverContent();

        Assertions.assertEquals(expected,actual);
    }

    private void deleteChar(){
        DeleteCharCommand deleteCharCommand = new DeleteCharCommand(this.receiver);
        deleteCharCommand.execute();
    }

    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }
}