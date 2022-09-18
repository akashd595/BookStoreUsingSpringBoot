package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.OrderDTO;
import com.bridgelabz.book_store_app.model.OrderCart;

public interface IOrderService {
    public OrderCart addOrder(OrderDTO orderDTO);
    public OrderCart createOrderDirectly( OrderDTO orderDTO );

}
