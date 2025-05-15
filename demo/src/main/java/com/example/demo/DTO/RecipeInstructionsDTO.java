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
public class RecipeInstructionsDTO {

     @Size(min =10, max =512)
     @NotBlank(message = "Instructions are required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String instructions;
}
