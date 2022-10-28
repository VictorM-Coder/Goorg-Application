package com.goorg.goorgjava.model.atividade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
public class PriorityTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    private Long id;

    //@Column(unique = true) unicidade removida pois os valores serão constantes
    private String name;

    public PriorityTag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDePrioridade{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }

    public PriorityTag() {

    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }
}
