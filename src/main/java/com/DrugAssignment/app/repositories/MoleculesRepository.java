package com.DrugAssignment.app.repositories;

import com.DrugAssignment.app.model.Molecules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoleculesRepository extends JpaRepository<Molecules, Integer> {

    public Molecules findByName(String name);
}
