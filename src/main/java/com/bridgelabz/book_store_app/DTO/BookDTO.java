package com.bridgelabz.book_store_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int bookPrice;
    private int quantity;

}
