package com.spring.springbootlibrary.Controller;


import com.spring.springbootlibrary.entity.Book;
import com.spring.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/secure/ischeckout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId){
        String userEmail="testuser@gmail.com";
        return bookService.checkedBookByUser(userEmail,bookId);
    }
    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception{
        String userEmail ="er@gmail.com";
        return bookService.checkoutBook(userEmail,bookId);
    }

}