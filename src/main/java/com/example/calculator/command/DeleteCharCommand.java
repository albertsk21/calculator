package com.example.calculator.command;

import com.example.calculator.GlobalConstants;
import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteCharCommand extends ConcreteCommand{

    public DeleteCharCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {

        String inputText = this.getReceiver().getContent();

        if(inputText.length() == 0 || inputText.equals(GlobalConstants.ERROR)){
            this.getReceiver().clean();
            return;
        }
        if (inputText.length() == 1){
            this.getReceiver().clean();
            return;
        }

        List<String> input = Arrays.stream(inputText.split(""))
                .collect(Collectors.toList());

        input.remove(input.size() - 1);

        if (input.size() == 1 || input.size() == 0){
            this.getReceiver().clean();
            this.getReceiver().action(this.addAll(input));
            return;
        }

        if (input.get(input.size() - 1).equals(" ")){
            input.remove(input.size() - 1);
        }

        String output = this.addAll(input);
        this.getReceiver().clean();

        this.getReceiver().action(output);
    }
    private String addAll(List<String> list){
        StringBuilder output = new StringBuilder();
        list.forEach(output::append);

        return output.toString();
    }



}
