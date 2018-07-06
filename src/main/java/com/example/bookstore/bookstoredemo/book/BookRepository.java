package com.example.bookstore.bookstoredemo.book;

import com.example.bookstore.bookstoredemo.book.Book;
import com.example.bookstore.bookstoredemo.fasade.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//implementacja repozytorium do pobierania danych z bazy, natomiast w scenariuszu tego projektu nie ma implementacji. My korzystamy tylko z interfejsu tego tutaj.

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    String FIND_BY_ISBN = "SELECT * FROM book WHERE book.isbn = ?1";
    @Async
    @Query(value = FIND_BY_ISBN, nativeQuery = true)
    List<Book> findByIsbn(String isbn); //bierze udzial rowniez w usuwaniu book.

    String FIND_BOOK_BY_TITLE = "SELECT * FROM book WHERE book.title LIKE (?1%)";
    @Async
    @Query(value = FIND_BOOK_BY_TITLE, nativeQuery = true)
    List<Book> findBookByTitle(String title);


    //problem z kluczami obcymi, usuwanie zrealizowane za pomocą metody z interfejsu JpaRepository
    String DELETE_BOOK_BY_ISBN = "DELETE FROM book WHERE book.isbn = ?1";
    @Async
    @Query(value = DELETE_BOOK_BY_ISBN, nativeQuery = true)
    Book deleteBook(String isbn);


    String UPDATE_BOOK_BY_ISBN = "UPDATE book SET price = (?2) WHERE isbn = (?1)";
    @Async
    @Query(value = UPDATE_BOOK_BY_ISBN, nativeQuery = true)
    Book updatePrice(String isbn, double price);



//    Optional<Book> find(String isbn);

//    Optional<Book> find(long id);
//
//    Book create(String isbn, String title, Set<Author> authors);
//
//    Book update(Book book);
//
//    void remove(String isbn);
//
//    void remove(long id);
}
