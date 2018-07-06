package com.example.bookstore.bookstoredemo.common;

public interface Mapper<F,T> {

    T map(F from);

}
