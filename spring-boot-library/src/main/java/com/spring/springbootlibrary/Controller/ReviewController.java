package com.spring.springbootlibrary.Controller;

import com.spring.springbootlibrary.Utils.ExtractJWT;
import com.spring.springbootlibrary.requestmodels.ReviewRequest;
import com.spring.springbootlibrary.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        var userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if(userEmail == null){
            throw new Exception("user is missing");

        }
        reviewService.postReview(userEmail,reviewRequest);
    }
}
