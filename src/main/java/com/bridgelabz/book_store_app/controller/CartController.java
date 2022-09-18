package com.bridgelabz.book_store_app.controller;

import com.bridgelabz.book_store_app.DTO.CartDTO;
import com.bridgelabz.book_store_app.DTO.ResponseDTO;
import com.bridgelabz.book_store_app.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartController")
public class CartController {
    @Autowired
    ICartService iCartService;

    @PostMapping("/addBookToCart")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody CartDTO cartDTO, @RequestParam(value = "token") String token){
        ResponseDTO responseDTO = new ResponseDTO("Book added to cart Successfully", iCartService.addToCart ( cartDTO, token ));
        return new ResponseEntity<>( responseDTO, HttpStatus.CREATED );
    }
}
