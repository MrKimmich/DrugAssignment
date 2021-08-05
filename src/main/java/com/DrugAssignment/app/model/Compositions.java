package com.DrugAssignment.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Compositions")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Compositions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "comp_name", nullable = false, unique = true)
    private String name;

    public Compositions(String compositionName) {
        this.name = compositionName;
    }
}
