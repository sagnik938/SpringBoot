package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

}
