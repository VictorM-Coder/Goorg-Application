package com.goorg.goorgjava.repository;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.util.ActivityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository repository;

    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_AtividadePersistida_Quando_sucesso(){
        Activity activityValida = ActivityCreator.createValidActivity();
        Activity activitySalva = this.repository.save(activityValida);
        Optional<Activity> atividade = this.repository.findById(activityValida.getId());

        Assertions.assertTrue(atividade.isPresent());
        Assertions.assertEquals(activitySalva, atividade.get());
    }

    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    void findById_ReturnaUmaListaDeAtividade_Quando_Successo(){
        Activity activityValida = ActivityCreator.createValidActivity();
        Activity activitySaved = this.repository.save(activityValida);

        Long id = activitySaved.getId();

        Optional<Activity> atividade = this.repository.findById(id);

        Assertions.assertTrue(atividade.isPresent());
        Assertions.assertFalse(atividade.isEmpty());
        Assertions.assertTrue(atividade.get().equals(activitySaved));
    }

    @Test
    @DisplayName("Salva uma lista de Atividades quando é executada corretamente")
    void save_AtividadePersistida_Quando_Successo(){
        List<Activity> atividadesValidas = ActivityCreator.createValidActivitiesList();
        List<Activity> atividadesSaved = (List<Activity>) this.repository.saveAll(atividadesValidas);

        Assertions.assertNotNull(atividadesSaved);
        Assertions.assertFalse(atividadesSaved.isEmpty());
        Assertions.assertEquals(atividadesSaved.size(), ActivityCreator.createValidActivitiesList().size());

        for (int cont = 0; cont < atividadesSaved.size(); cont++){
            Long id = atividadesSaved.get(cont).getId();
            Optional<Activity> atividadeSaved = this.repository.findById(id);

            Assertions.assertTrue(atividadeSaved.isPresent());
            Assertions.assertFalse(atividadeSaved.isEmpty());
            Assertions.assertTrue(atividadeSaved.get().equals(atividadesSaved.get(cont)));
        }
    }
}
