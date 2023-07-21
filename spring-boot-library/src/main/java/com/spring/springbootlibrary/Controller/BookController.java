package com.spring.springbootlibrary.Controller;


import com.spring.springbootlibrary.Utils.ExtractJWT;
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

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token){
        String userEmail="testuser@gmail.com";
        return bookService.currentLoanCount(userEmail);
    }

    @GetMapping("/secure/ischeckout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,@RequestParam Long bookId){
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkedBookByUser(userEmail,bookId);
    }
    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestHeader(value = "Authorization") String token,@RequestParam Long bookId) throws Exception{
        String userEmail ="testuser@gmail.com";
        return bookService.checkoutBook(userEmail,bookId);
    }

}
