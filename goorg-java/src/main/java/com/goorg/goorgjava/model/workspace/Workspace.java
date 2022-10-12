package com.goorg.goorgjava.model.workspace;

public class Workspace {
    private String nome;
    private String descricao;
    private Kanban kanban;

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
