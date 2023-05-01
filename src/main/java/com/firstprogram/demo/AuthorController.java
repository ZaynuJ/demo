package com.firstprogram.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthorController {
    
    @Autowired
	AuthorRepository authorRepository;

    @GetMapping("/authors")
	public ResponseEntity<?> getAllAuthors() {
		try {
			List<Author> authors = new ArrayList<Author>();
			authorRepository.findAll().forEach(authors::add);
            return new ResponseEntity<>(authors, null);
            }
		 catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    }

    @PostMapping("/authors")
	public ResponseEntity<?> createAuthor(@RequestBody Author author) {
		try {
            Author author1 = authorRepository.save(new Author(author.getFirstName(), author.getSecondName(), author.getBirthDate()));
            return new ResponseEntity<>(new ResponseMessage((Author) author1, null), HttpStatus.CREATED);
     } 
     catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage((Author) author,new AppError(HttpStatus.BAD_REQUEST, "Какая-то ошибка")), HttpStatus.BAD_REQUEST);
		}
	}


  }
    

