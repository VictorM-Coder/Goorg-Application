package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.PriorityTag;
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

@SpringBootTest
public class PriorityTagServiceTest implements ServiceTest{
    @Autowired
    private PriorityTagService priorityTagService;

    private final PriorityTagCreator creator = new PriorityTagCreator();

    @MockBean
    private PriorityTagRepository priorityTagRepository;

    @Override
    @Test
    @DisplayName("Uma tag é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Mockito.when(this.priorityTagRepository.save(this.creator.createValidItem())).thenReturn(this.creator.createValidItem());
        PriorityTag tagCreated = this.priorityTagService.save(this.creator.createValidItem());

        System.out.println(this.creator.createValidItem());
        Assertions.assertNotNull(tagCreated);
        Assertions.assertEquals(tagCreated, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma tag retorna uma tag quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Mockito.when(this.priorityTagRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Long expectedID = 1L;
        Optional<PriorityTag> tag = this.priorityTagRepository.findById(expectedID);

        Assertions.assertFalse(tag.isEmpty());
        Assertions.assertEquals(tag.get(), creator.createValidItem());
        Assertions.assertEquals(tag.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tags válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        Mockito.when(this.priorityTagRepository.findAll()).thenReturn(this.creator.createValidItemsList());
        List<PriorityTag> tags = this.creator.createValidItemsList();
        List<PriorityTag> tagsGetteds = (List<PriorityTag>) this.priorityTagService.getAll();

        Assertions.assertNotNull(tagsGetteds);
        Assertions.assertEquals(tagsGetteds.size(), tags.size());

        for (int cont = 0; cont < tags.size(); cont++){
            Assertions.assertEquals(tagsGetteds.get(cont), tags.get(cont));
        }
    }

    @Override
    @Test
    @DisplayName("Retorna uma tag atualizada e válida quando executado com sucesso")
    public void update_ReturnAUpdatedItem_When_Sucess() {
        Mockito.when(this.priorityTagRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.priorityTagRepository.save(this.creator.createValidItem())).thenReturn(this.creator.createValidItem());

        PriorityTag tag = this.creator.createValidItem();
        PriorityTag tagSaved = this.priorityTagService.save(tag);
        String newName = "novo titulo";
        tagSaved.setName(newName);
        Mockito.when(this.priorityTagRepository.save(tagSaved)).thenReturn(tagSaved);
        PriorityTag TagUpdated = this.priorityTagService.update(tagSaved.getId(), tagSaved);

        Assertions.assertEquals(TagUpdated.getId(), tagSaved.getId());
        Assertions.assertEquals(tagSaved, TagUpdated);
        Assertions.assertEquals(TagUpdated.getName(), newName);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        PriorityTag tag = this.creator.createValidItem();
        Mockito.when(this.priorityTagRepository.findById(tag.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.priorityTagService.update(tag.getId(), tag));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Mockito.when(this.priorityTagRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        this.priorityTagService.delete(1L);
        Mockito.verify(priorityTagRepository, Mockito.times(1)).delete(this.creator.createValidItem());
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        Mockito.when(this.priorityTagRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.priorityTagService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de tags quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Mockito.when(this.priorityTagRepository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());

        List<PriorityTag> tags = creator.createValidItemsList();
        List<PriorityTag> tagsSaved = (List<PriorityTag>) this.priorityTagService.saveAll(tags);

        Assertions.assertNotNull(tagsSaved);
        Assertions.assertEquals(tagsSaved.size(), tags.size());

        for (int cont = 0; cont < tagsSaved.size(); cont++){
            Assertions.assertEquals(tagsSaved.get(cont), tags.get(cont));
        }
    }
}
