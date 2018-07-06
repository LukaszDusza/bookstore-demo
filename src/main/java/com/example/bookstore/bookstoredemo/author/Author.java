package com.example.bookstore.bookstoredemo.author;


import com.example.bookstore.bookstoredemo.book.Book;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE},mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author() { }

    public Author(String name, String lastName, Set<Book> books) {
        this.name = name;
        this.lastName = lastName;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o ) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return (int) (id^ (id>>> 32));
    }
}
