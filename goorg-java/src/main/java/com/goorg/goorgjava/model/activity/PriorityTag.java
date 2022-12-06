package com.goorg.goorgjava.model.activity;

import com.goorg.goorgjava.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
public class PriorityTag extends BaseEntity {
    @Column(unique = true)
    @NotNull
    private String name;

    public PriorityTag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDePrioridade{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityTag that = (PriorityTag) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public PriorityTag() {

    }

    public PriorityTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
