package com.bridgelabz.book_store_app.controller;

import com.bridgelabz.book_store_app.DTO.ResponseDTO;
import com.bridgelabz.book_store_app.DTO.UserDTO;
import com.bridgelabz.book_store_app.model.UserData;
import com.bridgelabz.book_store_app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userController")

public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO ){
        ResponseDTO responseDTO = new ResponseDTO("Contact Added Successfully", iUserService.addUser ( userDTO ));
        return new ResponseEntity<>( responseDTO, HttpStatus.CREATED );
    }

    @GetMapping("/findUserByID/{token}")
    public ResponseEntity<ResponseDTO> findUser( @PathVariable String token ){
        ResponseDTO responseDTO = new ResponseDTO("ID found", iUserService.getUserID( token ));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK) ;
    }

    @GetMapping("/findAllUser/{token}")
    public Optional<List<UserData>> getUsersList(@PathVariable String token ){
        return iUserService.getUsersList(token);
    }
    /**
     * ("/put/{firstName}")
     * @PathVariable String firstName, @RequestParam(value = "lastName") String lastName
     */
    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> findUserByEmail( @RequestParam(value = "token") String token, @PathVariable String email ){
//        String email = "akashd595@gmail.com";
        ResponseDTO responseDTO = new ResponseDTO("User Found with email: "+email, iUserService.getUserByEmail( token, email ));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK) ;
    }
    @PostMapping("/tologin/{password}")
    public String toLoginUser( @RequestParam(value = "token") String token , @PathVariable String password){
        return iUserService.tologin(token, password);
    }

    @PostMapping("/tologout")
    public String toLogoutUser( @RequestParam(value = "token") String token  ){
        return iUserService.tologout(token);
    }

//    @DeleteMapping("deleteUser/{id}")
//    public ResponseEntity<ResponseDTO> deleteContact(@PathVariable int id){
//        ResponseDTO responseDTO = new ResponseDTO("Succfully deleted", iAddressBookService.deleteEmployeeID( id ));
//        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//    }

//    /**
//     * Updating Employee Using its ID
//     * */
//    @PutMapping("updateUser/{id}")
//    public ResponseEntity<ResponseDTO> UpdateContact(@Valid @RequestBody UserDTO employeeDTO, @PathVariable int id ){
//        ResponseDTO responseDTO = new ResponseDTO("Updated", iAddressBookService.updateContactID( employeeDTO, id ));
//        if(iAddressBookService.updateContactID( employeeDTO, id ) != null){
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK) ;
//        }

//        responseDTO.setMessage("ID not found");
//        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
//    }

}
