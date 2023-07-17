package com.spring.springbootlibrary.service;

import com.spring.springbootlibrary.dao.BookRepository;
import com.spring.springbootlibrary.dao.CheckoutRepository;
import com.spring.springbootlibrary.entity.Book;
import com.spring.springbootlibrary.entity.Checkout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;

    private CheckoutRepository checkoutRepository;

    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkoutBook(String userEmail, Long bookId) throws Exception {

        Optional<Book> book = bookRepository.findById(bookId);

        var validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book does not exist or already checked out by User");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);
        return book.get();
    }

    public Boolean checkedBookByUser(String userEmail, Long bookId){
        var validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        if(validateCheckout!=null){
            return true;
        }else {
            return false;
        }
    }

    public int currentLoanCount(String userEmail){
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }

}
