package com.bridgelabz.book_store_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Pattern(regexp = "^[A-Z][a-z]{2,20}$", message = "Not allowed ")
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dob;
    private String password;
    private boolean admin;
}
