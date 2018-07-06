package com.example.bookstore.bookstoredemo.author;

import com.example.bookstore.bookstoredemo.book.Book;
import com.example.bookstore.bookstoredemo.common.Mapper;
import com.example.bookstore.bookstoredemo.fasade.AuthorDto;

import java.util.function.Function;
import java.util.stream.Collectors;

public class AuthorMapper implements Mapper<Author, AuthorDto> {

    @Override
    public AuthorDto map(Author author) {
        String books = author.getBooks()
                .stream()
                .map(BookToString.INSTANCE)
                .collect(Collectors.joining(", "));
        return new AuthorDto(author.getName(), author.getLastName(), books);
    }

    private enum BookToString implements Function<Book, String> {
        INSTANCE;


        @Override
        public String apply(Book book) {
            return book.getTitle();
        }
    }
}
