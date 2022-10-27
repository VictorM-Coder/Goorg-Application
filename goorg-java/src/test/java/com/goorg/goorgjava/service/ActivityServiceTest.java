package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.util.ActivityCreator;
import org.junit.jupiter.api.*;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ActivityServiceTest implements ServiceTest{
    @Autowired
    private ActivityService activityService;

    private ActivityCreator creator = new ActivityCreator();

    @MockBean
    private ActivityRepository repository;

    @BeforeEach
    public void setUp(){
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.repository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());
        Mockito.when(this.repository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());
        Mockito.when(this.repository.findAll()).thenReturn(this.creator.createValidItemsList());
    }

    @Override
    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Activity activityCreated = this.activityService.save(this.creator.createValidItem());

        Assertions.assertNotNull(activityCreated);
        Assertions.assertEquals(activityCreated, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Long expectedID = 1L;
        Optional<Activity> atividade = this.repository.findById(expectedID);

        Assertions.assertFalse(atividade.isEmpty());
        Assertions.assertTrue(atividade.get().equals(creator.createValidItem()));
        Assertions.assertEquals(atividade.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de atividades válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        List<Activity> activities = this.creator.createValidItemsList();
        List<Activity> activitiesGetteds = (List<Activity>) this.activityService.getAll();

        Assertions.assertNotNull(activitiesGetteds);
        Assertions.assertEquals(activitiesGetteds.size(), activities.size());

        for (int cont = 0; cont < activities.size(); cont++){
            Assertions.assertEquals(activitiesGetteds.get(cont), activities.get(cont));
        }
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de Atividades quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        List<Activity> activityList = creator.createValidItemsList();
        List<Activity> activitiesSaved = (List<Activity>) this.activityService.saveAll(activityList);

        Assertions.assertNotNull(activitiesSaved);
        Assertions.assertEquals(activitiesSaved.size(), activityList.size());

        for (int cont = 0; cont < activitiesSaved.size(); cont++){
            Assertions.assertEquals(activitiesSaved.get(cont), activityList.get(cont));
        }
    }
}
