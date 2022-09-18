package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.BookDTO;
import com.bridgelabz.book_store_app.model.Book;

import java.util.List;

public interface IBookService {
    public Book addBook(BookDTO bookDTO, String token);
    public List<Book> getBookByBookName(String token, String book_name);
    public Book getBookByID(String token, int book_id);
    public Book getBookId(int book_id);
    public Book updateBookByID(BookDTO bookDTO, String token, int book_id);
    public Book updateBookQuantity( String token, int book_id, int bookQuantity);
    public String deleteBook( String token, int book_id);
    }
