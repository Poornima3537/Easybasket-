package com.codespace.easybasket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest{

    @NotBlank(message="name is required")
    @Size(max=20, message="name should not be greater than 20")
    private String name;
   
    @NotBlank(message="email should not be empty")
    @Email(message="Invalid email format")
    private String email;
    
   
    @NotBlank(message="Password is required")
    @Size(min=8, max=20, message="Password should be of length netween 8 to 20")
    @Pattern(
        regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message="password must contain uppercase, lowercase,number, special character"
    )
    private String password;
}