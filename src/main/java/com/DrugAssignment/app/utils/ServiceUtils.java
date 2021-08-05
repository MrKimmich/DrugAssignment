package com.DrugAssignment.app.utils;

import com.DrugAssignment.app.dto.IngredientDto;

import java.util.List;

public class ServiceUtils {

    public String getCompositionName(List<IngredientDto> ingredientDtoList){
        StringBuilder name = new StringBuilder();
        for (IngredientDto ingredient: ingredientDtoList) {
            name.append(ingredient.getName()).append(" (").append(ingredient.getStrength()).append(" ").append(ingredient.getUnit()).append(") + ");
        }
        name.deleteCharAt(name.length()-1);
        name.deleteCharAt(name.length()-1);
        name.deleteCharAt(name.length()-1);
        return name.toString();
    }

    public String getMoleculeName(List<IngredientDto> ingredientDtoList){
        StringBuilder name = new StringBuilder();
        for (IngredientDto ingredient: ingredientDtoList) {
            name.append(ingredient.getName()).append(" + ");
        }
        name.deleteCharAt(name.length()-1);
        name.deleteCharAt(name.length()-1);
        name.deleteCharAt(name.length()-1);
        return name.toString();
    }

}
