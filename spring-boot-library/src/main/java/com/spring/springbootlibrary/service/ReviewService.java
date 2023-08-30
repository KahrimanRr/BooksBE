package com.spring.springbootlibrary.service;

import com.spring.springbootlibrary.dao.ReviewRepository;
import com.spring.springbootlibrary.entity.Review;
import com.spring.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService( ReviewRepository reviewRepository) {

        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        var validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            throw new Exception("Review already created");
        }
        var review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().get());
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);

    }

    public Boolean userReviewListed(String userEmail, Long bookId){
        var validateReview = reviewRepository.findByUserEmailAndBookId(userEmail,bookId);
        if (validateReview != null){
            return true;
        }else {
            return false;
        }
    }
}
