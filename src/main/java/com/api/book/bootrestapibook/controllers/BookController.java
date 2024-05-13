package com.api.book.bootrestapibook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.book.bootrestapibook.model.Book;
import com.api.book.bootrestapibook.services.BookService;


@RestController
public class BookController {
    
    

    @Autowired
    private BookService bookService;

    //get all book handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> books = bookService.getAllBooks();
        if(books.size()==0)
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(books);
    }

    //get a single book by id handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
    Book book = bookService.getBookById(id);
    if (book == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(book);
}

    //new book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
      Book b = null;
      try {
       b = bookService.addBook(book);
       System.out.println(b);
       return ResponseEntity.status(HttpStatus.CREATED).body(b);
      } catch (Exception e) {
         e.printStackTrace();
      }
       
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
    {
      try {
         bookService.deleteBook(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } catch (Exception e) {
         e.printStackTrace();
      }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //update book handler
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id)
    {
      try{
       bookService.updateBook(book,id);
       return ResponseEntity.ok().body(book);
      }catch(Exception e){
         e.printStackTrace();
      }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
