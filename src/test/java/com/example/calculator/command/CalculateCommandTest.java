package com.example.calculator.command;

import com.example.calculator.calculate.ICalculator;
import com.example.calculator.calculate.MathCalculator;
import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.reflect.Field;


class CalculateCommandTest {

    private ICalculator iCalculator;
    private Receiver receiver;
    private String actual;
    private String container = "";


    public void init() {

        this.receiver = new StringReceiver(container);
        this.iCalculator = new MathCalculator();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/calculate-command.csv", numLinesToSkip = 1)
    public void calculateOperations(String sequence, String expected) throws NoSuchFieldException, IllegalAccessException {

        container = sequence;
        init();
        CalculateCommand calculateCommand = new CalculateCommand(iCalculator, receiver);
        calculateCommand.execute();

        actual = this.getReceiverContent();
        Assertions.assertEquals(expected, actual);
    }



    private String getReceiverContent() throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = this.receiver.getClass();
        Field field = clazz.getDeclaredField("container");
        field.setAccessible(true);
        return (String) field.get(this.receiver);
    }

}