package com.DrugAssignment.app.repositories;

import com.DrugAssignment.app.model.Compositions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionsRepository extends JpaRepository<Compositions, Integer> {

    public Compositions findByName(String name);
}
