package com.bridgelabz.book_store_app.controller;

import com.bridgelabz.book_store_app.DTO.BookDTO;
import com.bridgelabz.book_store_app.DTO.ResponseDTO;
import com.bridgelabz.book_store_app.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller  class
 */

@RestController
@RequestMapping("/BookController")
public class BookController {
    /**
     * Auto wired with Service class
     */
    @Autowired
    IBookService iBookService;

    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> createBook(@RequestBody BookDTO bookDTO, @RequestParam(value = "token") String token){
        ResponseDTO responseDTO = new ResponseDTO("Book Added in the list", iBookService.addBook(bookDTO, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getBookByName/{book_name}")
    public ResponseEntity<ResponseDTO> findBookByName(@RequestParam(value = "token") String token, @PathVariable String book_name){
        ResponseDTO responseDTO = new ResponseDTO("The Book is Present in the Book  Store", iBookService.getBookByBookName(token, book_name));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getBookByID/{book_id}")
    public ResponseEntity<ResponseDTO> findBookByID(@RequestParam(value = "token") String token, @PathVariable int book_id){
        ResponseDTO responseDTO = new ResponseDTO("The Book is Present in the Book Store", iBookService.getBookByID(token, book_id));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/updateBookByID/{book_id}")
    public ResponseEntity<ResponseDTO> updateBookByID(@RequestParam(value = "token") String token, @PathVariable int book_id, @RequestBody BookDTO bookDTO){
        ResponseDTO responseDTO = new ResponseDTO("The Book is Updated", iBookService.updateBookByID(bookDTO, token, book_id));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/updateBookQuantity/{book_id}/{bookQuantity}")
    public ResponseEntity<ResponseDTO> updateBookByID(@RequestParam(value = "token") String token, @PathVariable int book_id, @PathVariable Integer bookQuantity){
        ResponseDTO responseDTO = new ResponseDTO("The Book Quantity updated", iBookService.updateBookQuantity(token, book_id, bookQuantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteBookQuantity/{book_id}")
    public ResponseEntity<ResponseDTO> deleteBookByID(@RequestParam(value = "token") String token, @PathVariable int book_id){
        ResponseDTO responseDTO = new ResponseDTO("Book  deletion Done", iBookService.deleteBook(token, book_id));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
