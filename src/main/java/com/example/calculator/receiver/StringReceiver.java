package com.example.calculator.receiver;

public class StringReceiver implements Receiver{

    private String container;

    public StringReceiver(String container) {
        this.container = container;
    }

    @Override
    public String action(String str) {
      return this.container += str;
    }

    @Override
    public String getContent() {
        return container;
    }

    @Override
    public void clean() {
        this.container = "";
    }


}
