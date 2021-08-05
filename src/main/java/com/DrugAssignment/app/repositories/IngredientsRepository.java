package com.DrugAssignment.app.repositories;

import com.DrugAssignment.app.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {

    public Ingredients findByName(String name);
}
