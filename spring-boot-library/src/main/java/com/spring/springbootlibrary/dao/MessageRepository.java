package com.spring.springbootlibrary.dao;

import com.spring.springbootlibrary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
