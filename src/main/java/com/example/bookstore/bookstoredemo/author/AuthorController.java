package com.example.bookstore.bookstoredemo.author;


import com.example.bookstore.bookstoredemo.fasade.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("authors")
    public Collection<AuthorDto> authors(){
        List<Author> authors = authorRepository.findAll();
        AuthorMapper mapper = new AuthorMapper();
        List<AuthorDto> dtos = new ArrayList<>();

        for(Author a: authors) {
            AuthorDto authorDto = mapper.map(a);
            dtos.add(authorDto);
        }
        return dtos;
    }
}
