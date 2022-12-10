package com.goorg.goorgjava.dto.activity;

import com.goorg.goorgjava.dto.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriorityTagDto extends BaseEntityDto {
    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 6, max = 6)
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityTagDto that = (PriorityTagDto) o;
        return Objects.equals(this.getId(), that.getId()) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
