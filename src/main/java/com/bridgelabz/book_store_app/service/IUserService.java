package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.UserDTO;
import com.bridgelabz.book_store_app.model.UserData;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public String addUser(UserDTO userDTO);
    public UserData getUserID(String token);
    public Optional<List<UserData>> getUsersList(String token);
    public List<UserData> getUserByEmail( String token, String email );

    public String tologin(String token, String password);
    public String tologout(String token);


//    public UserData updateUserID(UserDTO userDTO, int id);
//
//    public Optional<UserData> findUserID(int id);
//    public List<UserData> findUserbyName(String name);
//    public Integer deleteUserID(int id);
//
//    public List<UserData> findAllUsers();

}
