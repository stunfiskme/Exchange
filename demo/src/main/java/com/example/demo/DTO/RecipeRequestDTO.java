package com.example.demo.DTO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestDTO {
    private Long id;

     @Size(min =1, max =25)
     @NotBlank(message = "Recipe name is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String recipeName;

     @Size(min =10, max =280)
     @NotBlank(message = "Description is required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String description;

     @Size(min =10, max =512)
     @NotBlank(message = "Instructions are required")
     @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Only letters, numbers, and spaces allowed")
     private String instructions;

     @NotNull(message = "File is required")
     private MultipartFile file;
}
