package com.goorg.goorgjava.model.atividade;

import javax.persistence.*;

@Entity
public class PriorityTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
