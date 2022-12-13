package br.com.devdojo.awesome.devdojospringboot.error;

public class PasswordIncorretException extends RuntimeException{

    public PasswordIncorretException(String message) {
        super(message);
    }
}
