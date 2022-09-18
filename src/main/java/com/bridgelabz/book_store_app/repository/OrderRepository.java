package com.bridgelabz.book_store_app.repository;

import com.bridgelabz.book_store_app.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderCart, Integer> {

}
