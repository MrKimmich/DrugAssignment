package com.DrugAssignment.app.dto;

import com.DrugAssignment.app.model.Molecules;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CompositionDto {
    private String compositionName;
    private List<IngredientDto> ingredientDtoList;
    private Molecules molecule;
    private String rxRequired;
}
