package com.example.demo.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsRequestDTO {
   
     @Size(min =1, max=25)
     @NotBlank(message = "Ingredient name is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String ingredientName;

     @Size(min =1, max=20)
     @NotBlank(message = "Unit name is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String unitName;

     @Size(min =1, max =4)
     @NotBlank(message = "Amount is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String amount;
}
