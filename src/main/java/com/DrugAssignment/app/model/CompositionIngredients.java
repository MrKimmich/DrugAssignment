package com.DrugAssignment.app.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "composition_ingredients")
public class CompositionIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "comp_id")
    private Compositions compositions;
    @ManyToOne
    @JoinColumn(name = "idt_id")
    private Ingredients ingredients;

    @Column(name = "unit", nullable = false)
    private String unit;
    @Column(name = "strength", nullable = false)
    private float strength;
}
