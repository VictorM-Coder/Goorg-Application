package com.goorg.goorgjava.service;

public interface  ServiceTest{
    public void save_PersistItem_When_Sucess();

    void save_PersistItemsList_When_Success();

    void findById_ReturnAItem_When_Success();

    void findAll_ReturnItemList_When_Success();

    void update_ReturnAUpdatedItem_When_Sucess();

    void update_ThrowsBadRequestException_When_IdNotExists();

    void delete_executeDelete_When_Success();

    void delete_ThrowsBadRequestException_When_IdNotExists();

}