package com.goorg.goorgjava.util.creator;

import com.goorg.goorgjava.dto.BaseEntityDto;

import java.util.List;

public interface Creator<E> {
    E createValidItem();
    List<E> createValidItemsList();
}
