package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Book;
import com.springboot.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		// Book book=new Book(12500,"Java Spring boot","ABC");
		List<Book> result = bookService.getAllBooks();
		if (result.size() == 0) {
			return (ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		return (ResponseEntity.status(HttpStatus.ACCEPTED).body(result));
	}

	// getbookbyid
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		// Book b= this.bookService.addBook(book);
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// delete put

	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		// this.bookService.deleteBook(bookId);
		try {
			this.bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// PutMapping -> id -> update
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		// this.bookService.updateBook(book,bookId);
		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.status(HttpStatus.CREATED).body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
