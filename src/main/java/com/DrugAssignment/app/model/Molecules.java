package com.DrugAssignment.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Molecules")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Molecules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "mol_name", nullable = false, unique = true)
    private String name;
    @Column(name = "rx_required", nullable = false, unique = true)
    private boolean rxRequired;

    public Molecules(String moleculeName, boolean rxRequired) {
        this.name = moleculeName;
        this.rxRequired = rxRequired;
    }
}
