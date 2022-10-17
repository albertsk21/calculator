package com.example.calculator.command;

import com.example.calculator.GlobalConstants;
import com.example.calculator.receiver.Receiver;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCharacterCommand extends ConcreteCommand{

    private String character;
    private String extract;

    public AddCharacterCommand(String character, Receiver receiver) {
        super(receiver);
        this.character = character;
    }

    @Override
    public void execute() {


        String output = this.getReceiver().getContent();

        if(output.equals(GlobalConstants.ERROR)){
            this.getReceiver().clean();
            this.extract = this.getReceiver().action(character);
            return;
        }
        Pattern isNumber = Pattern.compile("[0-9]");
        Matcher matcherChar = isNumber.matcher(character);

        String[] symbols =  output.split(" ");

        boolean isNotNegativeNumber = !Objects.equals(symbols[symbols.length - 1], "") &&
                !symbols[symbols.length - 1].equals(GlobalConstants.DIVIDE_OPERATOR)
                && isNotOperation(symbols[symbols.length - 1]);



        if(isNotNegativeNumber && character.equals(GlobalConstants.MINUS_OPERATOR)){
           this.extract = this.getReceiver().action( " " + GlobalConstants.MINUS_OPERATOR + " " );
            return;
        }

        if(output.length() == 0 && character.equals(GlobalConstants.MINUS_OPERATOR)){
            this.extract = this.getReceiver().action(GlobalConstants.MINUS_OPERATOR);
            return;
        }
        String[] arr = output.split("");


        if(arr[arr.length - 1].equals(GlobalConstants.MINUS_OPERATOR) &&
                !character.equals(GlobalConstants.MINUS_OPERATOR)){
            this.extract = this.getReceiver().action(character);
            return;
        }
        if (character.equals(".")){

            if(output.length() == 0){
                this.extract = this.getReceiver().action("0.");
                return;
            }
            output = addDot(output);
            this.getReceiver().clean();
            this.extract = this.getReceiver().action(output);
            return;
        }


        if(output.length() == 0){
            if(matcherChar.find()){
                output += character;
                this.extract = this.getReceiver().action(output);
            }
            return;
        }else if( arr[0].equals("0") && arr[output.length() - 1].equals("0") && character.equals("0")){
            return;
        }

        String number = output.split(" ")[output.split(" ").length - 1];
        String lastChar = number.split("")[number.length() - 1];
        boolean lastCharIsNumber =  this.isNotOperation(lastChar);
        boolean firstCharIsNumber =  matcherChar.find();


        if(lastChar.equals(".") && !this.isNotOperation(character)){
            return;
        }

        if(number.length() == 1 && number.charAt(0) == '0' && this.isNotOperation(character)){
            StringBuilder toDelete = new StringBuilder(output).deleteCharAt(output.length() - 1);
            toDelete.append(character);
            this.getReceiver().clean();
            this.extract = this.getReceiver().action(toDelete.toString());
            return;
        }

        if( !lastCharIsNumber && character.equals(GlobalConstants.MINUS_OPERATOR) &&
                !lastChar.equals(GlobalConstants.MINUS_OPERATOR)){
            this.extract = this.getReceiver().action(" " + GlobalConstants.MINUS_OPERATOR);
        }
        if(!firstCharIsNumber && !lastCharIsNumber){
            return;
        }
        if( ( lastCharIsNumber || lastChar.equals(".") ) && firstCharIsNumber ){
            output += character;

        }

        boolean isSpace = output.split("")[output.split("").length - 1].equals(" ");
        if (isSpace || lastChar.equals(".") || (isNotOperation(character) && isNotOperation(lastChar))){
            this.extract = this.getReceiver().action(character);
        }else{

            if (!isNotOperation(lastChar) || isNotOperation(lastChar)){
                this.extract = this.getReceiver().action(" " + character);
            }else {
                this.extract = this.getReceiver().action(character);
            }
        }
    }
    public boolean isNotOperation(String character){
        return !character.equals(GlobalConstants.MULTIPLY_OPERATOR) &&
                !character.equals(GlobalConstants.ADD_OPERATOR) &&
                !character.equals(GlobalConstants.DIVIDE_OPERATOR) &&
                !character.equals(GlobalConstants.MINUS_OPERATOR);
    }

    private String addDot(String sequence){
        if (sequence.length() == 0){
            return sequence;
        }

        if(!this.isNotOperation(String.valueOf(sequence.charAt(sequence.length() - 1)))){
            return sequence += " 0.";
        }
        String[] arr = sequence.split(" ");
        String lastNumber = arr[arr.length - 1];

        Pattern pattern = Pattern.compile("[.]");
        Matcher matcher = pattern.matcher(lastNumber);

        sequence = !matcher.find() ? sequence + "." : sequence;
        return sequence;
    }




    public String getExtract() {
        return extract;
    }
}
