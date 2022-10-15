package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.model.workspace.Workspace;

public class AtividadeCreator {
    public static Atividade criarAtividadeValida(){
        return new Atividade(1L, "atividade", "descrição", null, null, null, null, new Workspace());
    }
}
