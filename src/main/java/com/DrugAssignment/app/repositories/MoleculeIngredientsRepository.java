package com.DrugAssignment.app.repositories;

import com.DrugAssignment.app.model.Ingredients;
import com.DrugAssignment.app.model.MoleculeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoleculeIngredientsRepository extends JpaRepository<MoleculeIngredients, Integer> {

    public List<MoleculeIngredients> findByIngredients(Ingredients ingredient);
}
