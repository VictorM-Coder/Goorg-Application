package com.goorg.goorgjava.model.workspace;

import com.goorg.goorgjava.model.atividade.Atividade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "workspace")
    private List<Atividade> atividades;

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
