package com.api.book.bootrestapibook.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestapibook.model.Book;
import com.api.book.bootrestapibook.dao.BookRepository;


@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //Get All Books
    public List<Book> getAllBooks()
    {
        List<Book> books = (List<Book>)bookRepository.findAll();  
        return books; 
    }

    //Get Book By Id
    public Book getBookById(int id)
    {
        Book book = null;
        try {
            book = bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
        
    }

    //Create or Insert New Book
    public Book addBook(Book b)
    {
        Book result = bookRepository.save(b);
        return result;
    }

    //Delete the Book By Id
    public void deleteBook(int id)
    {
        bookRepository.deleteById(id);
    }

    //update the book by Id
    public void updateBook(Book b, int id)
    {
        b.setId(id);
        bookRepository.save(b);
    }


}
