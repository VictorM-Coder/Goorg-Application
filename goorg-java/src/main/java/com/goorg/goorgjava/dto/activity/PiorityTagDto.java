package com.goorg.goorgjava.dto.activity;

import com.goorg.goorgjava.dto.BaseEntityDto;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;

public class PiorityTagDto extends BaseEntityDto {
    @NotNull
    @UniqueElements
    private String name;
}
