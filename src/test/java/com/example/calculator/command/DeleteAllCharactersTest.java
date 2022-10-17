package com.example.calculator.command;

import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAllCharactersTest {


    private String expected;
    private String container = "";
    private Receiver receiver;
    private String actual;

    private void init(){
        this.receiver = new StringReceiver(container);

    }


    @Test
    public void deleteAllCharacters() throws NoSuchFieldException, IllegalAccessException {
        String sequence = "12 + 2 + 3";
        container = sequence;
        init();
        DeleteAllCharacters deleteAllCharacters = new DeleteAllCharacters(this.receiver);
        deleteAllCharacters.execute();

        actual = this.getReceiverContent();
        expected = "";
        Assertions.assertEquals(expected,actual);
    }



    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }

}