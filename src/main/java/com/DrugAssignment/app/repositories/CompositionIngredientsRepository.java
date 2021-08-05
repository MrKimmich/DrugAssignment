package com.DrugAssignment.app.repositories;

import com.DrugAssignment.app.model.CompositionIngredients;
import com.DrugAssignment.app.model.Compositions;
import com.DrugAssignment.app.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionIngredientsRepository extends JpaRepository<CompositionIngredients, Integer> {

    public List<CompositionIngredients> findByCompositions(Compositions compositions);
    public List<CompositionIngredients> findByIngredientsAndStrengthAndUnit(Ingredients ingredients, float strength, String unit);
}
