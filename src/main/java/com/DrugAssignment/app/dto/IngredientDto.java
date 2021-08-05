package com.DrugAssignment.app.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class IngredientDto {
    private String name;
    private float strength;
    private String unit;
}
