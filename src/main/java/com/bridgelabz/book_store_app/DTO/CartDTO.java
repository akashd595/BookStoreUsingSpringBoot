package com.bridgelabz.book_store_app.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CartDTO {
    public int userId;
    public List<Integer> bookId;
    public List<Integer> quantity;
}
/**
 * {
// *     "userId": 2,
// *     "bookId":[],
// *     "quantity":[]
 * }
 */