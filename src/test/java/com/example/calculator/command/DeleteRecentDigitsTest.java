package com.example.calculator.command;

import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.reflect.Field;


class DeleteRecentDigitsTest {

    private String container = "";
    private Receiver receiver;

    private void init(){
        this.receiver = new StringReceiver(container);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/delete-recent-digits-command.csv", numLinesToSkip = 1)
    public void deleteRecentNumber_ShouldReturnCorrectOutput(String sequence, String expected) throws NoSuchFieldException, IllegalAccessException {

        container = sequence;
        init();
        DeleteRecentDigits deleteRecentDigits = new DeleteRecentDigits(this.receiver);
        deleteRecentDigits.execute();
        String actual = this.getReceiverContent();
        Assertions.assertEquals(expected,actual);
    }
    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }
}