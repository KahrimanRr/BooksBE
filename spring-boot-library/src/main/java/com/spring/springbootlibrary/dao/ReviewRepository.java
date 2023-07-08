package com.spring.springbootlibrary.dao;

import com.spring.springbootlibrary.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review,Long>{
}
