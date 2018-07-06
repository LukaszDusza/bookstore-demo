package com.example.bookstore.bookstoredemo.book;


import com.example.bookstore.bookstoredemo.author.AuthorRepository;
import com.example.bookstore.bookstoredemo.fasade.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

//    @GetMapping("books")
//    public Collection<BookDto> getBooks() {
//
//        List<Book> books = bookRepository.findAll();
//        ModelMapper mapper = new ModelMapper();
//        List<BookDto> bookDtos = new ArrayList<>();
//
//        for (Book b :  books) {
//            BookDto bookDto = new BookDto();
//            mapper.map(b, bookDto);
//            bookDtos.add(bookDto);
//        }
//        return bookDtos;
//    }

    @GetMapping("books")
    public Collection<BookDto> getBooks() {

        List<Book> books = bookRepository.findAll();
        BookMapper bookMapper = new BookMapper(); //mapujemy book na book dto.
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book b :  books) {
            BookDto bookDto = bookMapper.map(b);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @GetMapping("books/{title}")
    public Collection<BookDto> getBookByAuthorName(@PathVariable String title) {
        List<Book> books = bookRepository.findBookByTitle(title);
        BookMapper bookMapper = new BookMapper();
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book b :  books) {
            BookDto bookDto = bookMapper.map(b);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }


    @RequestMapping(value = "books", method = RequestMethod.POST)
    public ResponseEntity<Book> add(@Valid @RequestBody Book book) {

        String isbn = book.getIsbn();
        List<Book> books = bookRepository.findByIsbn(isbn);
        if(books.isEmpty()) {
            bookRepository.save(book);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }

//        List<Author> authorsFromNewBook = new ArrayList<>();
//        authorsFromNewBook.addAll(book.getAuthors());  //zamiana Set na List
//
//        List<Author> existingAuthors = authorRepository.findAll();
//        for(Author a: authorsFromNewBook) {
//            existingAuthors
//                    .stream()
//                    .filter(e -> (!e.getName().equalsIgnoreCase(a.getName()) ))
//                    .filter(e -> (!e.getLastName().equalsIgnoreCase(a.getLastName()) ))
//                    .map(e -> authorRepository.save(e));
//            if(!existingAuthors.contains(a)) {
//                authorRepository.save(a);
//            }
//        }
        return new ResponseEntity<>(book,HttpStatus.CONFLICT);
    }

    //@RequestMapping(value = "books/{isbn}", method = RequestMethod.DELETE)
    @DeleteMapping("books/{isbn}")
    public  ResponseEntity<Book> deleteBook(@PathVariable String isbn) {
        List<Book> books = bookRepository.findByIsbn(isbn);
        for(Book b: books) {
            bookRepository.delete(b);
        }
    //    bookRepository.deleteBook(isbn); //nie wykorzystana metoda
        return new ResponseEntity<>(HttpStatus.OK);

    }

//    @RequestMapping(value = "books", method = RequestMethod.PUT)
//    public  @ResponseBody ResponseEntity<Book> updatePrice(@RequestBody String price) {
//
//    }

    @PutMapping("books/{isbn}")
    @ResponseBody
    public ResponseEntity<Book> updatePrice(@PathVariable String isbn, @RequestParam("price") String price) {
        List<Book> books = bookRepository.findByIsbn(isbn);

//        List<Book> updatedBooks = books.stream()
//                .map(b -> new Book(b.getIsbn(), b.getTitle(), b.getAuthors(), Double.parseDouble(price)))
//                .collect(Collectors.toList());

        for(Book b: books) {
            Book book = new Book(b.getIsbn(), b.getTitle(), b.getAuthors(), Double.parseDouble(price));
            bookRepository.updatePrice(b.getIsbn(), Double.parseDouble(price));

            System.out.println(b);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
