package com.bridgelabz.book_store_app.model;

import com.bridgelabz.book_store_app.DTO.CartDTO;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ElementCollection
    @CollectionTable(name = "cart_book", joinColumns = @JoinColumn(name = "cart_id"))
    private List<Integer> book;

    @ElementCollection
    @CollectionTable(name = "cart_book_quantities", joinColumns = @JoinColumn(name = "cart_id"))
    private List<Integer> quantity;

    public Cart() {
    }

    public Cart(CartDTO cartDTO) {
        this.book = cartDTO.getBookId();
        this.quantity = cartDTO.getQuantity();
    }


    public Cart(Integer cartId, UserData userData, List<Integer> book, List<Integer> quantity) {
        this.cartId = cartId;
        this.userData = userData;
        this.book = book;
        this.quantity = quantity;
    }
    public Cart(UserData userData, List<Integer> bookId, List<Integer> quantity) {
        this.userData = userData;
        this.book = bookId;
        this.quantity = quantity;
    }


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<Integer> getBook() {
        return book;
    }

    public void setBook(List<Integer> book) {
        this.book = book;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

}
