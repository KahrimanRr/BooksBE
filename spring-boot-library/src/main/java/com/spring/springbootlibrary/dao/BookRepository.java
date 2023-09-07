package com.spring.springbootlibrary.dao;

import com.spring.springbootlibrary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);
    @Query("SELECT o FROM Book o WHERE o.id IN :book_ids")
    List<Book>findBooksByIds(@Param("book_ids") List<Long> bookId);
/* selecting objects or elements from Book objects where  objects.id are in bookids
* this is selection by checking if the id of each book is in the list of the bookids
* provided as book_ids parameter*/
}
