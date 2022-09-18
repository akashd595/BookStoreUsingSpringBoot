package com.bridgelabz.book_store_app.DTO;

import com.bridgelabz.book_store_app.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private LocalDate date;
    private float price;
    private int quantity;                               //UserInput     //2
    private String address;                             //UserInput     //1,2
    private int userId;                                 //UserInput     //1,2
    private List<Integer> bookIdForOrder;               //UserInput     //2
    private boolean cancel;
//    private Cart cart;
    private int cartId;

}
