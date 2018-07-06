package com.example.bookstore.bookstoredemo.book;

import com.example.bookstore.bookstoredemo.author.Author;
import com.example.bookstore.bookstoredemo.common.Mapper;
import com.example.bookstore.bookstoredemo.fasade.BookDto;

import java.util.function.Function;
import java.util.stream.Collectors;

//implementacja kodu do zamiany obiekt domenowy Book na BookDTO.

public class BookMapper  implements Mapper<Book, BookDto> {

    @Override
    public BookDto map(Book book) {
        String authors = book.getAuthors()
                .stream()
                .map(AuthorToNameAndSurname.INSTANCE)
                .collect(Collectors.joining(", "));
        return new BookDto(book.getIsbn(), book.getTitle(), authors, String.valueOf(book.getPrice()));

    }

    private enum AuthorToNameAndSurname implements Function<Author,String> {
        INSTANCE;

        @Override
        public String apply(Author author) {
            return author.getName() + " " + author.getLastName();
        }
    }
}
