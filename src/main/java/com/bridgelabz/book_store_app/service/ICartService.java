package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.CartDTO;
import com.bridgelabz.book_store_app.model.Cart;

public interface ICartService {
    public Cart addToCart(CartDTO cartDTO, String token);
}
