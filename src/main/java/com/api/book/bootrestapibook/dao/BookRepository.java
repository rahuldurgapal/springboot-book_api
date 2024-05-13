package com.api.book.bootrestapibook.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.book.bootrestapibook.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

      public Book findById(int id);
}