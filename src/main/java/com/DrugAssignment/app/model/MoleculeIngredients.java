package com.DrugAssignment.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "molecule_ingredients")
public class MoleculeIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mol_id")
    private Molecules molecules;
    @ManyToOne
    @JoinColumn(name = "idt_id")
    private Ingredients ingredients;
}
