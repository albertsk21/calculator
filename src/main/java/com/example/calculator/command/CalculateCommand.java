package com.example.calculator.command;

import com.example.calculator.calculate.ICalculator;
import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;

public class CalculateCommand extends ConcreteCommand{
    ICalculator calculator;
    public CalculateCommand(ICalculator calculator, Receiver receiver) {
        super(receiver);
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        String result = this.calculator.calculate(this.getReceiver().getContent());
        this.getReceiver().clean();
        this.getReceiver().action(result);
    }
}
