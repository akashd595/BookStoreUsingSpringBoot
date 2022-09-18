package com.bridgelabz.book_store_app.repository;

import com.bridgelabz.book_store_app.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    @Query(value = "SELECT * FROM user_data e WHERE e.email = :email", nativeQuery = true)
    List<UserData> getUserDataByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user_data e WHERE e.user_id = :id", nativeQuery = true)
    UserData getUserDataById(@Param("id") int id);


    @Query(value = "SELECT * FROM book e WHERE e.book_name = :book_name", nativeQuery = true)
    List<UserData> getBookByBookName(@Param("book_name") String book_name);


}
