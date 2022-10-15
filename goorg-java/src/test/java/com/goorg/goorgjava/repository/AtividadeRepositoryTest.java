package com.goorg.goorgjava.repository;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.repositories.AtividadeRepository;
import com.goorg.goorgjava.util.AtividadeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AtividadeRepositoryTest {

    private Atividade atividadeValida;

    @Autowired
    private AtividadeRepository repository;

    @BeforeEach
    private void setUp(){
        this.atividadeValida = AtividadeCreator.criarAtividadeValida();
    }

    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_AtividadePersistida_Quando_sucesso(){
        System.out.println(atividadeValida.getTitulo());
        this.repository.save(atividadeValida);
        Optional<Atividade> atividadeSalva = this.repository.findById(this.atividadeValida.getId());

        Assertions.assertTrue(atividadeSalva.isPresent());
        Assertions.assertEquals(this.atividadeValida, atividadeSalva.get());
    }

    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    void findById_ReturnaUmaListaDeAtividade_Quando_Successo(){
        Atividade atividadeSaved = this.repository.save(this.atividadeValida);

        Long id = atividadeSaved.getId();

        Optional<Atividade> atividades = this.repository.findById(id);

        Assertions.assertTrue(atividades.isPresent());
        Assertions.assertFalse(atividades.isEmpty());
        Assertions.assertTrue(atividades.get().equals(this.atividadeValida));
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
            Assertions.assertTrue(atividadeSaved.get().equals(atividadesValidas.get(cont)));
        }
    }
}
