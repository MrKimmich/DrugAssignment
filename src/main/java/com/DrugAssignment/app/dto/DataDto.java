package com.DrugAssignment.app.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class DataDto {
    private List<IngredientDto> ingredientDto;
    private boolean rxRequired;
}
