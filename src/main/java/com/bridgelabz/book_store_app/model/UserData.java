package com.bridgelabz.book_store_app.model;

import com.bridgelabz.book_store_app.DTO.LoginDTO;
import com.bridgelabz.book_store_app.DTO.UserDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dob;
    private String password;
    private boolean admin;
    private boolean isLogin;

//    @ElementCollection
//    @CollectionTable(name = "phone_Number_list", joinColumns = @JoinColumn(name = "contact_id"))
//    public List<String> phoneNumber;


    public UserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
        this.admin = userDTO.isAdmin();
    }
    public UserData(LoginDTO loginDTO){

    }

    public UserData(int userId, String firstName, String lastName, String address, String email, LocalDate dob, String password, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.admin = admin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public UserData() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}