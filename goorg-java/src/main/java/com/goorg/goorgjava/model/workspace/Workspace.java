package com.goorg.goorgjava.model.workspace;

import com.goorg.goorgjava.model.atividade.Atividade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.PERSIST)
    private List<Atividade> atividades;

    public Workspace() {
    }

    public Workspace(Long id, String nome, String descricao, List<Atividade> atividades) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", atividades=" + atividades +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidadeAtividades(){
        return 0;
    }
}
