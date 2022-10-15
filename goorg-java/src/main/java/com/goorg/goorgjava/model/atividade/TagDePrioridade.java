package com.goorg.goorgjava.model.atividade;

import javax.persistence.*;

@Entity
public class TagDePrioridade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;

    public TagDePrioridade(String nome) {
        this.nome = nome;
    }

    public TagDePrioridade() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
