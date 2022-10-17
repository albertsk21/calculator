package com.example.calculator.command;

import com.example.calculator.receiver.Receiver;

public class DeleteAllCharacters extends ConcreteCommand{
    public DeleteAllCharacters(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {

        this.getReceiver().clean();
    }
}
