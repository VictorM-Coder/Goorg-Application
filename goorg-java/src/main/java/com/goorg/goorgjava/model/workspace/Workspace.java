package com.goorg.goorgjava.model.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorg.goorgjava.model.BaseEntity;
import com.goorg.goorgjava.model.atividade.Activity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Workspace extends BaseEntity {
    @Column(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
    private List<Activity> activities;

    public Workspace() {
        this.activities = new ArrayList<>();
    }

    public Workspace(Long id, String name, String description) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", descricao='" + description + '\'' +
                ", atividades=" + activities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id.equals(workspace.id) && name.equals(workspace.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
