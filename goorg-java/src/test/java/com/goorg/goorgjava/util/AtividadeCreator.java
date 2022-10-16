package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.model.atividade.TagDePrioridade;
import com.goorg.goorgjava.model.workspace.Workspace;

import java.util.ArrayList;
import java.util.List;

public class AtividadeCreator {
    public static Atividade criarAtividadeValida(){
        return new Atividade(1L, "atividade", "descrição", null, null, null, new TagDePrioridade("tag"), new Workspace());
    }

    public static List<Atividade> criarListaDeAtividadesValidas(){
        List<Atividade> list = new ArrayList<Atividade>();
        list.add(new Atividade(1L, "atividade", "descrição", null, null, null, null, new Workspace()));
        list.add(new Atividade(2L, "atividade2", "descrição", null, null, null, null, new Workspace()));
        list.add(new Atividade(3L, "atividade3", "descrição", null, null, null, null, new Workspace()));

        return list;
    }
}
