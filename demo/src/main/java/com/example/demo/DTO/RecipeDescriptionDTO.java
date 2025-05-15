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
public class RecipeDescriptionDTO {

     @Size(min =10, max =280)
     @NotBlank(message = "Description is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String description;
    
}
