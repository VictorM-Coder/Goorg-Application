package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Atividade;

public class AtividadeCreator {
    public static Atividade criarAtividadeValida(){
        return new Atividade(1L, "atividade", "descrição", null, null, null, null, "workspace");
    }
}
