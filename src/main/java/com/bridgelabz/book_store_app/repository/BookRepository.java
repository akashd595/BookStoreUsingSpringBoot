package com.bridgelabz.book_store_app.repository;

import com.bridgelabz.book_store_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM book e WHERE e.book_name = :book_name", nativeQuery = true)
    List<Book> getBookByBookName(@Param("book_name") String book_name);

    @Query(value = "SELECT * FROM book e WHERE e.book_id = :bookId", nativeQuery = true)
    Book getBookById(@Param("bookId") Integer bookId);

}