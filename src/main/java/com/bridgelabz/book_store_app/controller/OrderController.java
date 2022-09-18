package com.bridgelabz.book_store_app.controller;

import com.bridgelabz.book_store_app.DTO.CartDTO;
import com.bridgelabz.book_store_app.DTO.OrderDTO;
import com.bridgelabz.book_store_app.DTO.ResponseDTO;
import com.bridgelabz.book_store_app.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderController")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/addOrder")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO("Order added Successfully", iOrderService.addOrder ( orderDTO ).toString());
        return new ResponseEntity<>( responseDTO, HttpStatus.CREATED );
    }
    @PostMapping("/createOrder")
    public ResponseEntity<ResponseDTO> createDirectOrder(@RequestBody OrderDTO orderDTO){
        ResponseDTO responseDTO = new ResponseDTO("Direct Order added Successfully", iOrderService.createOrderDirectly ( orderDTO ).toString());
        return new ResponseEntity<>( responseDTO, HttpStatus.CREATED );
    }
}
