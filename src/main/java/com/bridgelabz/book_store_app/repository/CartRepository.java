package com.bridgelabz.book_store_app.repository;

import com.bridgelabz.book_store_app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM cart c WHERE c.user_id = :user_id", nativeQuery = true)
    Cart findCartByUserId(@Param("user_id") int user_id);

}
