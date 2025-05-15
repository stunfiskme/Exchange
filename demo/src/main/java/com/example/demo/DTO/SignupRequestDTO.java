package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {
    
    @Email
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
    private String email;
  
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", 
         message = "Password must contain at least one uppercase letter and one digit")
    private String password;
    
     @Size(min =1, max =20)
     @NotBlank(message = "First name is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String firstName;
    
     @Size(min =1, max =20)
     @NotBlank(message = "Last name is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String LastName;
    
     @Pattern(regexp = "^\\+?[0-9 .\\-]{7,15}$", message = "Invalid phone number")
     private String phoneNumber;
}
