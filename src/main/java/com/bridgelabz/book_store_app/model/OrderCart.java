package com.bridgelabz.book_store_app.model;

import com.bridgelabz.book_store_app.DTO.OrderDTO;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_cart")
@Data
public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private int order_id;
    private LocalDate date;
    private float price;
    private int quantity;
    private String address;
    private int userId;

//    private int cartId;
//    @ElementCollection
//    @CollectionTable(name = "order_book", joinColumns = @JoinColumn(name = "order_id"))

//    public int getCartId() {
////        cartId = cart.getCartId();
//        return cartId;
//    }

//    public void setCartId(int cartId) {
//        this.cartId = cartId;
//    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    boolean cancel;

//    private UserData userData ;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id")
//    private Book book ;

//    public OrderCart(int order_id, LocalDate date, float price, int quantity, String address, int userId, List<Integer> orderBookId, boolean cancel) {


    public OrderCart(OrderDTO orderDTO) {
        this.date = orderDTO.getDate();
        this.price = orderDTO.getPrice();
        this.quantity = orderDTO.getQuantity();
        this.address = orderDTO.getAddress();
        this.userId = orderDTO.getUserId();
    }

    public OrderCart(int order_id, LocalDate date,Cart cartId, float price, int quantity, String address, int userId, boolean cancel) {
        this.order_id = order_id;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.userId = userId;
        this.cart = cartId;
        this.cancel = cancel;
//        this.userData = userData;
//        this.book = book;
    }

    public OrderCart() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

//    public UserData getUserData() {
//        return userData;
//    }
//
//    public void setUserData(UserData userData) {
//        this.userData = userData;
//    }
//
//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
}

