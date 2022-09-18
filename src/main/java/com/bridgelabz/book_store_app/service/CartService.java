package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.CartDTO;
import com.bridgelabz.book_store_app.exception.CustomException;
import com.bridgelabz.book_store_app.model.Book;
import com.bridgelabz.book_store_app.model.Cart;
import com.bridgelabz.book_store_app.model.UserData;
import com.bridgelabz.book_store_app.repository.CartRepository;
import com.bridgelabz.book_store_app.repository.UserRepository;
import com.bridgelabz.book_store_app.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    IBookService iBookService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    float totalPrice;
    public Cart addToCart(CartDTO cartDTO, String token){

        ArrayList<Book> bookList = new ArrayList<>();
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User id"+id+" is not found")) ;
        /**
         * Checking the User is valid or not using the token getting from the controller
         */
        int userId = userData.getUserId();
        int cartId = 0;
        Cart cart = new Cart();

        if(cartRepository.findCartByUserId(userId) == null){

            List<Integer> bookIdList = cartDTO.bookId;
            List<Integer> quantities = cartDTO.quantity;
//            float totalPrice = 0;

            for(int i=0; i< bookIdList.size(); i++){

                if(quantities.get(i) <= iBookService.getBookId(bookIdList.get(i)).getQuantity()){

                    bookList.add(iBookService.getBookId(bookIdList.get(i)));
                    totalPrice += (iBookService.getBookId(bookIdList.get(i)).getBookPrice() * quantities.get(i));

                }else {
                    throw new CustomException("Selected quantity exceeds Limit");
                }

                cart = new Cart(userData, cartDTO.getBookId(), cartDTO.getQuantity());

            }
        }else{

            cartId = cartRepository.findCartByUserId(userId).getCartId();
            cart = new Cart(cartId, userData, cartDTO.getBookId(), cartDTO.getQuantity());

        }
        setTotalprice(totalPrice);
        return cartRepository.save(cart);
    }
    public void setTotalprice(float totalprice){
        this.totalPrice = totalprice;
    }
    public float getTotalPrice(){
        return totalPrice;
    }
}
