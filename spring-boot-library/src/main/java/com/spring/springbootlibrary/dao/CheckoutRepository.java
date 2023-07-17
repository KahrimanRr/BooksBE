package com.spring.springbootlibrary.dao;

import com.spring.springbootlibrary.entity.Checkout;
import com.spring.springbootlibrary.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Review,Long> {
    Checkout findByUserEmailAndBookId(String userEmail,Long bookId);

}
