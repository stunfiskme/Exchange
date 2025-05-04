package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsRequestDTO {
    private Long id;
    private String ingredientName;
    private String unitName;
    private String amount;
}
