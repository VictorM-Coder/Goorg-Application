package com.goorg.goorgjava.repository;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.repositories.AtividadeRepository;
import com.goorg.goorgjava.util.AtividadeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AtividadeRepositoryTest {
    @Autowired
    private AtividadeRepository repository;

    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_AtividadePersistida_Quando_sucesso(){
        Atividade atividadeValida = AtividadeCreator.criarAtividadeValida();
        Atividade atividadeSalva = this.repository.save(atividadeValida);
        Optional<Atividade> atividade = this.repository.findById(atividadeValida.getId());

        Assertions.assertTrue(atividade.isPresent());
        Assertions.assertEquals(atividadeSalva, atividade.get());
    }

    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    void findById_ReturnaUmaListaDeAtividade_Quando_Successo(){
        Atividade atividadeValida = AtividadeCreator.criarAtividadeValida();
        Atividade atividadeSaved = this.repository.save(atividadeValida);

        Long id = atividadeSaved.getId();

        Optional<Atividade> atividade = this.repository.findById(id);

        Assertions.assertTrue(atividade.isPresent());
        Assertions.assertFalse(atividade.isEmpty());
        Assertions.assertTrue(atividade.get().equals(atividadeSaved));
    }

    @Test
    @DisplayName("Salva uma lista de Atividades quando é executada corretamente")
    void save_AtividadePersistida_Quando_Successo(){
        List<Atividade> atividadesValidas = AtividadeCreator.criarListaDeAtividadesValidas();
        List<Atividade> atividadesSaved = (List<Atividade>) this.repository.saveAll(atividadesValidas);

        Assertions.assertNotNull(atividadesSaved);
        Assertions.assertFalse(atividadesSaved.isEmpty());
        Assertions.assertEquals(atividadesSaved.size(), AtividadeCreator.criarListaDeAtividadesValidas().size());

        for (int cont = 0; cont < atividadesSaved.size(); cont++){
            Long id = atividadesSaved.get(cont).getId();
            Optional<Atividade> atividadeSaved = this.repository.findById(id);

            Assertions.assertTrue(atividadeSaved.isPresent());
            Assertions.assertFalse(atividadeSaved.isEmpty());
            Assertions.assertTrue(atividadeSaved.get().equals(atividadesSaved.get(cont)));
        }
    }
}
