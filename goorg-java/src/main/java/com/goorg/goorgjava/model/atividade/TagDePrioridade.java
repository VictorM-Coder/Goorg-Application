package com.goorg.goorgjava.model.atividade;

import javax.persistence.*;

@Entity
public class TagDePrioridade {
    @Id
    private Long id;

    @Column(unique = true)
    private String nome;

    public TagDePrioridade(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TagDePrioridade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
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
