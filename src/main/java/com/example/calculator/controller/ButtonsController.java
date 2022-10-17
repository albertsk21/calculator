package com.example.calculator.controller;

import com.example.calculator.calculate.ICalculator;
import com.example.calculator.calculate.MathCalculator;
import com.example.calculator.command.*;
import com.example.calculator.command.interfaces.Command;
import com.example.calculator.receiver.Receiver;
import com.example.calculator.receiver.StringReceiver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ButtonsController {
    @FXML
    private Button closeButton;
    @FXML
    private Button hideWindowButton;
    @FXML
    private TextField textField;

    private Stage window;

    public void closeWindow(){
        this.window = (Stage) closeButton.getScene().getWindow();
        window.close();
    }
    public void hideWindow(){
        this.window = (Stage) hideWindowButton.getScene().getWindow();
        window.setIconified(true);
    }

    public void pressButton1(){
        this.addCharacter("1");
    }
    public void pressButton2(){
        this.addCharacter("2");
    }
    public void pressButton3(){
        this.addCharacter("3");
    }
    public void pressButton4(){
        this.addCharacter("4");
    }
    public void pressButton5(){
        this.addCharacter("5");
    }
    public void pressButton6(){
        this.addCharacter("6");
    }
    public void pressButton7(){
        this.addCharacter("7");
    }
    public void pressButton8(){
        this.addCharacter("8");
    }
    public void pressButton9(){
        this.addCharacter("9");
    }
    public void pressButton0(){
        this.addCharacter("0");
    }
    public void add(){
        this.addCharacter("+");
    }
    public void minus(){
        this.addCharacter("-");
    }
    public void divide(){
        this.addCharacter(":");
    }
    public void multiply(){
        this.addCharacter("*");
    }
    public void equal(){
        Receiver receiver = new StringReceiver(this.textField.getText());
        ICalculator calculator = new MathCalculator();
        Command calculateCommand = new CalculateCommand(calculator, receiver);
        calculateCommand.execute();
        this.textField.setText(receiver.getContent());

    }
    public void deleteAll(){
        Receiver receiver = new StringReceiver(this.textField.getText());
        Command deleteAllCharacters = new DeleteAllCharacters(receiver);
        deleteAllCharacters.execute();
        this.textField.setText(receiver.getContent());
    }
    public void addDot(){
        this.addCharacter(".");
    }

    public void deleteChar(){
        Receiver receiver = new StringReceiver(this.textField.getText());
        Command deleteCharCommand = new DeleteCharCommand(receiver);
        deleteCharCommand.execute();
        this.textField.setText(receiver.getContent());
    }


    public void addCharacter(String character){

        Receiver receiver = new StringReceiver(this.textField.getText());
        AddCharacterCommand addCharacterCommand = new AddCharacterCommand(character, receiver);
        addCharacterCommand.execute();
        this.textField.setText(receiver.getContent());
    }

    public void deleteRecentDigits(){

        Receiver receiver = new StringReceiver(this.textField.getText());
        Command command = new DeleteRecentDigits(receiver);
        command.execute();
        this.textField.setText(receiver.getContent());
    }


}