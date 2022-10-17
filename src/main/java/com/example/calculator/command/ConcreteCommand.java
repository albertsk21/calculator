package com.example.calculator.command;

import com.example.calculator.command.interfaces.Command;
import com.example.calculator.receiver.Receiver;
import javafx.scene.control.TextField;

public abstract class ConcreteCommand implements Command {



    private Receiver receiver;
    public ConcreteCommand(Receiver receiver) {

        this.receiver = receiver;
    }




    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

}
