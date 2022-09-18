package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.BookDTO;
import com.bridgelabz.book_store_app.exception.CustomException;
import com.bridgelabz.book_store_app.model.Book;
import com.bridgelabz.book_store_app.model.UserData;
import com.bridgelabz.book_store_app.repository.BookRepository;
import com.bridgelabz.book_store_app.repository.UserRepository;
import com.bridgelabz.book_store_app.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService implements IBookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    public Book addBook(BookDTO bookDTO, String token) {

        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
        if (userData.isAdmin()) {
            Book bookData = new Book(bookDTO);
            return bookRepository.save(bookData);

        } else
            throw new CustomException("user is not an Admin");
    }

    public List<Book> getBookByBookName(String token, String book_name) {
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
        if (userData.isAdmin()) {
            if (!bookRepository.getBookByBookName(book_name).isEmpty()) {
                return bookRepository.getBookByBookName(book_name);
            } else {
                throw new CustomException("No Book  Found with the name: " + book_name);
            }
        } else {
            throw new CustomException("user is not an Admin");
        }
    }

    public Book getBookByID(String token, int book_id) {
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
        if (userData.isAdmin()) {
            if (bookRepository.findById(book_id).isPresent()) {
                return bookRepository.findById(book_id).orElseThrow(() -> new CustomException("User not found"));
            } else {
                throw new CustomException("No Book  Found with the book id: " + book_id);
            }
        } else {
            throw new CustomException("user is not an Admin");
        }
    }

    public Book getBookId(int book_id) {
        if (bookRepository.findById(book_id).isPresent()) {
            return bookRepository.findById(book_id).orElseThrow(() -> new CustomException("User not found"));
        } else {
            throw new CustomException("No Book  Found with the book id: " + book_id);
        }
    }

    public Book updateBookByID(BookDTO bookDTO, String token, int book_id){
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User not found")) ;
        if(userData.isAdmin()) {
            if(bookRepository.findById(book_id).isPresent()){
                Book bookData = new Book(bookDTO);
                bookData.setBookId(book_id);
                return bookRepository.save(bookData);
            }else{
            throw new CustomException("Book Id is not present");
            }
        }else{
            throw new CustomException("User is not admin");
        }
    }
    public Book updateBookQuantity( String token, int book_id, int bookQuantity){
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User not found")) ;
        if(userData.isAdmin()) {
            if(bookRepository.findById(book_id).isPresent()){
                Book book= bookRepository.getBookById(book_id);
//                book.setBookId(book_id);
                book.setQuantity(bookQuantity);
                return bookRepository.save(book);
            }else{
                throw new CustomException("Book Id is not present");
            }
        }else{
            throw new CustomException("User is not admin");
        }
    }
    public String deleteBook( String token, int book_id){
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User not found")) ;
        if(userData.isAdmin()) {
            if(bookRepository.findById(book_id).isPresent()){
                bookRepository.deleteById(book_id);
                return "Book deleted Successfully";
            }else{
                throw new CustomException("Book Id is not present");
            }
        }else{
            throw new CustomException("User is not admin");
        }
    }
}
