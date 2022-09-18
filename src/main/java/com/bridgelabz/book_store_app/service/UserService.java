package com.bridgelabz.book_store_app.service;

import com.bridgelabz.book_store_app.DTO.LoginDTO;
import com.bridgelabz.book_store_app.DTO.UserDTO;
import com.bridgelabz.book_store_app.exception.CustomException;
import com.bridgelabz.book_store_app.model.UserData;
import com.bridgelabz.book_store_app.repository.UserRepository;
import com.bridgelabz.book_store_app.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    com.bridgelabz.book_store_app.email.EmailService emailService;

    @Autowired
    TokenUtility tokenUtility;

    public String addUser(UserDTO userDTO){
        UserData userData = new UserData(userDTO);    //contactData is entity
//        if(userData.isLogin()) {
            userRepository.save(userData);
            String token = tokenUtility.createToken(userData.getUserId());
//            emailService.sendEmail(userDTO.getEmail(), "User Registered Successfully", "Hello " + userDTO.getFirstName() + " your Registeration is Successfully and your\n" +
//                    "token is " + token);
            return token;
//        }else {
//            throw new CustomException("Please login to continue performing operations");
//        }
    }

    /**
     * If you create any method with token as argument
     * we need to come to this method to get  the userData object
     */
    public UserData getUserID(String token){
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User id"+id+" is not found")) ;
        if(userData.isLogin()){
            return userData;
        }else {
            throw new CustomException("Please Login to perform this action ");
        }
    }
    public Optional<List<UserData>> getUsersList(String token){

        if( getUserID(token).isAdmin() == true){

            return Optional.of(userRepository.findAll());
        }else {
            throw new CustomException("Either No User in the List or You are not an Admin");
        }
    }
    public List<UserData> getUserByEmail( String token, String email ){

        if( getUserID(token).isAdmin() == true  ){
            if(userRepository.getUserDataByEmail(email).isEmpty()){
                throw new CustomException(email+" is not found in the List");
            }else {
                return userRepository.getUserDataByEmail(email);
            }
        }else {
            throw new CustomException("Either No User in the List or You are not an Admin");
        }
    }
    public String tologin(String token, String password ){
        LoginDTO loginDTO = new LoginDTO();
        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User id"+id+" is not found")) ;
        loginDTO.setId(id);
        loginDTO.setPassword(userData.getPassword());
        if(loginDTO.getPassword().equals(password)){
            userData.setLogin(true);
            userRepository.save(userData);
        }
        return "Login status "+userData.isLogin();
    }
    public String tologout(String token){

        int id = tokenUtility.decodeToken(token);
        UserData userData = userRepository.findById(id).orElseThrow(()-> new CustomException("User id"+id+" is not found")) ;
        if(userData.isLogin() == true){
            userData.setLogin(false);
            userRepository.save(userData);
        }
        return "Login status "+userData.isLogin();
    }

//    public Optional<UserData> findUserID(int id){
//        if(bookRepository.findById(id).isPresent()){
//            return bookRepository.findById(id);
//        }else {
//            throw new CustomException("Contact with "+id+" id is not found in the Address Book");
//        }
//    }
//getUserByEmail( token, email )
//
//    public UserData updateUserID(UserDTO contactDTO, int id){
//        if(bookRepository.findById(id).isPresent()) {
//            UserData contactData = bookRepository.findById(id).get();
//            contactData.setFirstName(contactDTO.getFirstName());
//            contactData.setAddress(contactDTO.getAddress());
//            return bookRepository.save(contactData);
//        }else{
//            throw new CustomException("Contact with "+id+" id is not found");
//        }
//    }
//    public Integer deleteUserID(int id) {
//        if (bookRepository.findById(id).isPresent()) {
//            bookRepository.deleteById(id);
//            return id;
//        }else{
//            throw new CustomException("Contact with "+id+" id is not found");
//        }
//    }

}
