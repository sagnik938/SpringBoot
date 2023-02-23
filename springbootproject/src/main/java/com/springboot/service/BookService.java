package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.dao.BookRepository;
import com.springboot.model.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	/*
	 * private static List<Book> books = new ArrayList<>(); static { books.add(new
	 * Book(12300,"book1","xcv")); books.add(new Book(12301,"book2","xdf"));
	 * books.add(new Book(12302,"book3","xer")); } public List<Book> getAllBooks(){
	 * System.out.println(books); return books; }
	 * 
	 * public Book getBookById(int id) { return
	 * books.stream().filter(e->e.getId()==id).findFirst().get(); } public Book
	 * addBook(Book bk) { BookService.books.add(bk); return(bk); } public Book
	 * deleteBook(int id) { Book bk = getBookById(id); System.out.println(bk);
	 * BookService.books.remove(bk); return(bk); } public Book updateBook(int
	 * id,Book bk) { try { deleteBook(id); } catch (Exception e) { } Book b =
	 * addBook(bk); return(b); }
	 */

	public List<Book> getAllBooks() {
		List<Book> books = (List<Book>) this.bookRepository.findAll();
		return (books);
	}

	// get book by id

	public Book getBookById(int id) {
		Book book = null;
		// book= list.stream().filter(e->e.getId()==id).findFirst().get();
		try {
			book = (Book) this.bookRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	// post
	public Book addBook(Book bk) {
		// list.add(bk);
		Book result = this.bookRepository.save(bk);
		return result;
	}

	// deleteBook
	public void deleteBook(int bookId) {

		// list =
		// list.stream().filter(b->b.getId()!=bookId).collect(Collectors.toList());
		bookRepository.deleteById(bookId);
	}

	// updateBook
	public void updateBook(Book book, int bookId) {

		book.setId(bookId);
		bookRepository.save(book);

	}
}
