package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.PriorityTagDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.PriorityTagMapper;
import com.goorg.goorgjava.model.activity.PriorityTag;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import com.goorg.goorgjava.util.creator.creators.PriorityTagCreator;
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
public class PriorityTagServiceTest implements ServiceTest{
    @Autowired
    private PriorityTagService priorityTagService;

    @Autowired
    private PriorityTagMapper priorityTagMapper;

    private final PriorityTagCreator creator = new PriorityTagCreator();

    @MockBean
    private PriorityTagRepository repository;

    @Override
    @Test
    @DisplayName("Uma tag é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        PriorityTag priorityTag = creator.createValidItem();
        PriorityTagDto priorityTagDto = priorityTagMapper.toDto(priorityTag);

        when(repository.save(priorityTag)).thenReturn(priorityTag);
        PriorityTagDto priorityTagDtoSaved = priorityTagService.save(priorityTagDto);

        Assertions.assertNotNull(priorityTagDtoSaved);
        Assertions.assertEquals(priorityTagDtoSaved, priorityTagDto);
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma tag retorna uma tag quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        PriorityTag priorityTag = creator.createValidItem();
        when(repository.findById(1L)).thenReturn(Optional.of(priorityTag));

        Optional<PriorityTagDto> foundPriorityTagDto = priorityTagService.getById(1L);

        Assertions.assertTrue(foundPriorityTagDto.isPresent());
        Assertions.assertEquals(priorityTagMapper.toDto(priorityTag), foundPriorityTagDto.get());
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tags válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        List<PriorityTag> priorityTagList = creator.createValidItemsList();

        when(repository.findAll()).thenReturn(priorityTagList);
        List<PriorityTagDto> foundPriorityTagDto = (List<PriorityTagDto>) priorityTagService.getAll();

        Assertions.assertNotNull(foundPriorityTagDto);
        Assertions.assertEquals(foundPriorityTagDto.size(), priorityTagList.size());

        for (int cont  = 0; cont  < priorityTagList.size(); cont++){
            Assertions.assertEquals(foundPriorityTagDto.get(cont), priorityTagMapper.toDto(priorityTagList.get(cont)));
        }
    }

    @Override
    @Test
    @DisplayName("Retorna uma tag atualizada e válida quando executado com sucesso")
    public void update_executesSaveOnce_When_Sucess() {
        PriorityTag priorityTag = creator.createValidItem();

        when(repository.findById(1L)).thenReturn(Optional.of(priorityTag));
        when(repository.save(priorityTag)).thenReturn(priorityTag);

        priorityTagService.update(priorityTagMapper.toDto(priorityTag));

        Mockito.verify(repository, Mockito.times(1)).save(priorityTag);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        PriorityTag priorityTag = (creator.createValidItem());

        when(repository.findById(priorityTag.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> priorityTagService.update(priorityTagMapper.toDto(priorityTag)));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        PriorityTag priorityTag = this.creator.createValidItem();
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(priorityTag));

        this.priorityTagService.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(priorityTag);
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, ()-> this.priorityTagService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de tags quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        PriorityTag priorityTag = creator.createValidItem();

        when(this.repository.saveAll(List.of(priorityTag))).thenReturn(List.of(priorityTag));

        List<PriorityTagDto> priorityTagDtoList = (List<PriorityTagDto>) this.priorityTagService.saveAll(List.of(priorityTagMapper.toDto(priorityTag)));

        Assertions.assertNotNull(priorityTagDtoList);
        Assertions.assertEquals(priorityTagDtoList.size(), 1);
        Assertions.assertEquals(priorityTagDtoList.get(0), priorityTagMapper.toDto(priorityTag));
    }
}
