package com.goorg.goorgjava.model.workspace;

import javax.persistence.*;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

//    @OneToOne(mappedBy = "workspace_id")
//    private Kanban kanban;

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
