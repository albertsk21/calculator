package com.example.calculator.receiver;

public interface Receiver {
    String action(String str);
    String getContent();
    void clean();
}
