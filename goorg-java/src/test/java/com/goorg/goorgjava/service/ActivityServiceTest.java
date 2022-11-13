package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import com.goorg.goorgjava.util.creator.creators.ActivityCreator;
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

    private final ActivityCreator creator = new ActivityCreator();

    @MockBean
    private ActivityRepository repository;

    @MockBean
    private PriorityTagRepository priorityTagRepository;

    @Override
    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Mockito.when(this.repository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());

        Activity activityCreated = this.activityService.save(this.creator.createValidItem());

        Assertions.assertNotNull(activityCreated);
        Assertions.assertEquals(activityCreated, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        Long expectedID = 1L;
        Optional<Activity> atividade = this.repository.findById(expectedID);

        Assertions.assertFalse(atividade.isEmpty());
        Assertions.assertEquals(atividade.get(), creator.createValidItem());
        Assertions.assertEquals(atividade.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de atividades válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        Mockito.when(this.repository.findAll()).thenReturn(this.creator.createValidItemsList());

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
    @DisplayName("Retorna uma atividade atualizada e válida quando executado com sucesso")
    public void update_ReturnAUpdatedItem_When_Sucess() {
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.repository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());

        Activity activity = this.creator.createValidItem();
        Activity activitySaved = this.activityService.save(activity);
        String newTitle = "novo titulo";
        activitySaved.setTitle(newTitle);
        Mockito.when(this.repository.save(activitySaved)).thenReturn(activitySaved);
        Activity activityUpdated = this.activityService.update(activitySaved.getId(), activitySaved);

        Assertions.assertEquals(activityUpdated.getId(), activitySaved.getId());
        Assertions.assertEquals(activitySaved, activityUpdated);
        Assertions.assertEquals(activityUpdated.getTitle(), newTitle);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Activity activity = this.creator.createValidItem();
        Mockito.when(this.repository.findById(activity.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.activityService.update(activity.getId(), activity));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        this.activityService.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(this.creator.createValidItem());
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.activityService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de Atividades quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Mockito.when(this.repository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());

        List<Activity> activityList = creator.createValidItemsList();
        List<Activity> activitiesSaved = (List<Activity>) this.activityService.saveAll(activityList);

        Assertions.assertNotNull(activitiesSaved);
        Assertions.assertEquals(activitiesSaved.size(), activityList.size());

        for (int cont = 0; cont < activitiesSaved.size(); cont++){
            Assertions.assertEquals(activitiesSaved.get(cont), activityList.get(cont));
        }
    }

    @Test
    void changePriorityTag_When_Success(){
        Activity validActivity = this.creator.createValidItem();
        PriorityTag newPriorityTag = new PriorityTag(1L,"nova tag");
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(validActivity));
        Mockito.when(this.repository.save(ArgumentMatchers.any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        Mockito.when(this.priorityTagRepository.findById(1L)).thenReturn(Optional.of(newPriorityTag));

        Activity updatedActivity = this.activityService.changePriorityTag(1L, 1L);

        Assertions.assertEquals(validActivity, updatedActivity);
        Assertions.assertEquals(updatedActivity.getPriorityTag(), newPriorityTag);
    }
}