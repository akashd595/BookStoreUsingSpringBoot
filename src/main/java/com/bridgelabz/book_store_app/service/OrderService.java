package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.OrderDTO;
import com.bridgelabz.book_store_app.exception.CustomException;
import com.bridgelabz.book_store_app.model.Book;
import com.bridgelabz.book_store_app.model.Cart;
import com.bridgelabz.book_store_app.model.OrderCart;
import com.bridgelabz.book_store_app.model.UserData;
import com.bridgelabz.book_store_app.repository.BookRepository;
import com.bridgelabz.book_store_app.repository.CartRepository;
import com.bridgelabz.book_store_app.repository.OrderRepository;
import com.bridgelabz.book_store_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;

    public OrderCart addOrder(OrderDTO orderDTO) {

        float price = 0;
        Cart orderCart = cartRepository.findCartByUserId(orderDTO.getUserId());   //taking cart from db
        orderDTO.setDate(LocalDate.now());
        orderDTO.setBookIdForOrder(orderCart.getBook());
        List<Integer> quantityNumber = orderCart.getQuantity();
        orderDTO.setQuantity(getTotalQuantity(quantityNumber));
        orderDTO.setCartId(orderCart.getCartId());

        for(int i=0; i < orderDTO.getBookIdForOrder().size(); i++) {
            Book book = bookRepository.getBookById(orderDTO.getBookIdForOrder().get(i));
            int newQuantity = book.getQuantity() - orderCart.getQuantity().get(i);
            price += (book.getBookPrice() * orderDTO.getQuantity());
            book.setQuantity(newQuantity);
            bookRepository.save(book);
        }
        OrderCart order = new OrderCart(orderDTO);
        order.setPrice(price);
        order.setCart(orderCart);
        return orderRepository.save(order);
    }

    public int getTotalQuantity(List<Integer> quantityList) {

        int sum = 0;
        for (int i = 0; i < quantityList.size(); i++)
            sum += quantityList.get(i);

        return sum;
    }

    public OrderCart createOrderDirectly( OrderDTO orderDTO ){
        UserData userData = userRepository.getUserDataById(orderDTO.getUserId());
        if(userData != null){
            for( int i=0 ; i<orderDTO.getBookIdForOrder().size() ; i++ ){
                Book book = bookRepository.getBookById( orderDTO.getBookIdForOrder().get(i) );
                if( book.getQuantity() >= orderDTO.getQuantity() ){
                    orderDTO.setPrice( book.getBookPrice() * orderDTO.getQuantity() );
                    orderDTO.setDate(LocalDate.now());
                    int newQuantity = book.getQuantity() - orderDTO.getQuantity();
                    book.setQuantity(newQuantity);
                    bookRepository.save(book);
                }else {
                    throw new CustomException("Quantity exceeds Limit ");
                }
            }
            OrderCart orderCart = new OrderCart(orderDTO);

            return orderRepository.save(orderCart);
        }else {
            throw new CustomException("User not exist");
        }
    }
}
