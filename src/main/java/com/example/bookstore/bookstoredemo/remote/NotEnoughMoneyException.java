package com.example.bookstore.bookstoredemo.remote;

public class NotEnoughMoneyException extends Exception{

    //jesli platnosci sie nie powiedzie, wyrzucamy wyjatek.

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
