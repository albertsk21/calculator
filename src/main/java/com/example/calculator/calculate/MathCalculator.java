package com.example.calculator.calculate;

import com.example.calculator.GlobalConstants;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MathCalculator implements ICalculator {

   private final DecimalFormat decimalFormat = new DecimalFormat("#.#############");
    @Override
    public String calculate(String sequence) {
        String res = "";


        if(sequence.length() == 0){
            return "";
        }

        try {

            res = this.calculateByAnOperatorSymbol(sequence,GlobalConstants.MULTIPLY_OPERATOR);
            res = this.calculateByAnOperatorSymbol(res,GlobalConstants.DIVIDE_OPERATOR);
            res = res.replace(",",".");
            res = this.calculateLeftToRight(res);
        }catch (IndexOutOfBoundsException e){
            return GlobalConstants.ERROR;
        }


        if(res.equals(GlobalConstants.ERROR)) return res;

        res = res.replace(",",".");
        res =  String.format("%s",decimalFormat.format(Double.parseDouble(res)));
        return res.replace(",",".");
    }


    private String calculateLeftToRight(String sequence){

        ArrayDeque<String> deque = Arrays
                .stream(sequence
                        .split(" "))
                .collect(Collectors.
                        toCollection(ArrayDeque::new));


        while(true){
            if (deque.size() == 1){
                return deque.pop();
            }
            String first = deque.pop();
            String operator = deque.pop();

            if(deque.isEmpty()){
                return GlobalConstants.ERROR;
            }
            String second = deque.pop();
            String res = this.calculateTwoNumbers(first, operator, second);
            deque.push(Objects.requireNonNull(res.replaceAll(",",".")));


        }
    }
    private String calculateTwoNumbers(String first, String operator, String second){

        double firstNumber;
        double secondNumber;
        try {
            firstNumber = Double.parseDouble(first);
            secondNumber = Double.parseDouble(second);
        }catch (NumberFormatException e){
            return  GlobalConstants.ERROR;
        }



        switch (operator){
            case "+":
                return String.format("%s",decimalFormat.format(firstNumber + secondNumber));
            case "-":
                return String.format("%s",decimalFormat.format(firstNumber - secondNumber));
            case ":":

                try {
                    return String.format("%s",decimalFormat.format(firstNumber /secondNumber));
                }catch (ArithmeticException e){
                    return GlobalConstants.ERROR;
                }
            case "*":
                return String.format("%s",decimalFormat.format(firstNumber * secondNumber));
        }

        return null;
    }
    private String calculateByAnOperatorSymbol(String sequence, String operator){
        List<String> storage = Arrays.stream(sequence.split(" ")).collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();


        for (int i = 0; i < storage.size() ; i++) {
            String currentOperator = storage.get(i);
            if(currentOperator.equals(operator)){


                String result = this.calculateTwoNumbers(storage.get(i - 1),currentOperator,storage.get( i + 1));
                storage.set(i + 1,result);

                resultList.remove(resultList.size() - 1);
                resultList.add(result);
                if(i + 2 >= storage.size()){
                    break;
                }

                i++;
            }else{
                resultList.add(storage.get(i));
            }
        }
        return String.join(" ",resultList);
    }



}
