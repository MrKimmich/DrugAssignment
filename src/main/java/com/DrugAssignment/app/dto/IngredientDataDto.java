package com.DrugAssignment.app.dto;

import com.DrugAssignment.app.model.Ingredients;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class IngredientDataDto {
    private Ingredients ingredient;
    private float strength;
    private String unit;
}
