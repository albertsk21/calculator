package com.example.calculator.command;

import com.example.calculator.GlobalConstants;
import com.example.calculator.receiver.Receiver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteRecentDigits extends ConcreteCommand {

    public DeleteRecentDigits(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
         List<String> inputList = Arrays
                 .stream(this
                         .getReceiver()
                         .getContent()
                         .split(" "))
                 .collect(Collectors.toList());

         if (inputList.size() == 0){
            return;
         }

         String recentSequence = inputList.get(inputList.size() - 1);
         if(isNotOperation(recentSequence)){
             inputList.remove(inputList.size()  - 1);
         }

         this.getReceiver().clean();
         this.getReceiver().action(String.join(" ",inputList));

    }
    private boolean isNotOperation(String character){
        return !character.equals(GlobalConstants.MULTIPLY_OPERATOR) &&
                !character.equals(GlobalConstants.ADD_OPERATOR) &&
                !character.equals(GlobalConstants.DIVIDE_OPERATOR) &&
                !character.equals(GlobalConstants.MINUS_OPERATOR);
    }
}
