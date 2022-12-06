package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.ActivityMapper;
import com.goorg.goorgjava.model.activity.Activity;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import com.goorg.goorgjava.util.creator.creators.ActivityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ActivityServiceTest implements ServiceTest{
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    private final ActivityCreator creator = new ActivityCreator();

    @MockBean
    private ActivityRepository repository;

    @MockBean
    private PriorityTagRepository priorityTagRepository;

    @Override
    @Test
    @DisplayName("Uma atividade é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Activity activity = creator.createValidItem();
        ActivityDto activityDto = activityMapper.toDto(activity);

        when(repository.save(activity)).thenReturn(activity);
        ActivityDto activityDtoSaved = activityService.save(activityDto);

        Assertions.assertNotNull(activityDtoSaved);
        Assertions.assertEquals(activityDtoSaved, activityDto);
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma atividade retorna uma atividade quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Activity activity = creator.createValidItem();
        when(repository.findById(1L)).thenReturn(Optional.of(activity));

        Optional<ActivityDto> foundActivityDto = activityService.getById(1L);

        Assertions.assertTrue(foundActivityDto.isPresent());
        Assertions.assertEquals(activityMapper.toDto(activity), foundActivityDto.get());
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de atividades válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        List<Activity> activityList = creator.createValidItemsList();

        when(repository.findAll()).thenReturn(activityList);
        List<ActivityDto> foundActivitiesDto = (List<ActivityDto>) activityService.getAll();

        Assertions.assertNotNull(foundActivitiesDto);
        Assertions.assertEquals(foundActivitiesDto.size(), activityList.size());

        for (int cont  = 0; cont  < activityList.size(); cont++){
            Assertions.assertEquals(foundActivitiesDto.get(cont), activityMapper.toDto(activityList.get(cont)));
        }
    }

    @Override
    @Test
    @DisplayName("Resulta uma atividade atualizada e válida quando executado com sucesso")
    public void update_executesSaveOnce_When_Sucess() {
        Activity activity = creator.createValidItem();

        when(repository.findById(1L)).thenReturn(Optional.of(activity));
        when(repository.save(activity)).thenReturn(activity);

        activityService.update(activityMapper.toDto(activity));

        Mockito.verify(repository, Mockito.times(1)).save(activity);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Activity activity = (creator.createValidItem());

        when(repository.findById(activity.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> activityService.update(activityMapper.toDto(activity)));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Activity activity = this.creator.createValidItem();
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(activity));

        this.activityService.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(activity);
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, ()-> this.activityService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de Atividades quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Activity activity = creator.createValidItem();

        when(this.repository.saveAll(List.of(activity))).thenReturn(List.of(activity));

        List<ActivityDto> activitiesSaved = (List<ActivityDto>) this.activityService.saveAll(List.of(activityMapper.toDto(activity)));

        Assertions.assertNotNull(activitiesSaved);
        Assertions.assertEquals(activitiesSaved.size(), 1);
        Assertions.assertEquals(activitiesSaved.get(0), activityMapper.toDto(activity));
    }

    @Test
    void changePriorityTag_When_Success(){

    }

    @Test
    void changePhase_ChangeActivityPhase_When_Success(){

    }

    @Test
    void changePhase_ThrowsBadRequestException_When_OptionalEmpty(){

    }
}